package yfcdb.files;

import com.cedarsoftware.util.io.JsonWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import yfcdb.events.*;
import yfcdb.member.PersonList;

import javax.swing.*;
import java.io.*;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by janaldoustorres on 04/06/15.
 */
public class XSLXReport {
    private final Sheet sheet;
    private final HSSFWorkbook wb;
    private Object[] activitesArr = {"Monthly Gathering", EventType.CHAPTER_ASSEMBLY, EventType.COLLECTIVE_HOUSEHOLD, EventType.KASSANGA_ASSEMBLY,
            EventType.CHAPTER_SERVICE_MEETING, EventType.CLUSTER_SERVICE_MEETING, EventType.PASTORAL_HOUSEHOLD,
            "", "Pastoral Formation",
            EventType.YOUTH_CAMP_TRAINING, EventType.WORSHIP_WORKSHOP, EventType.HLT,
            EventType.YOUTH_CAMP, EventType.FAMILY_CULTURE, EventType.COVENANT_ORIENTATION, EventType.YOUTH_POWER,
            EventType.DISCOVERY_CAMP, EventType.PARENTS_HONORING, EventType.HUNDRED_PERCENT_FREE, EventType.STAKE_FOR_THE_NATION,
            EventType.VOCATION_RECOLLECTION, EventType.BEST_WEEKEND, EventType.CHURCH_AND_SACRAMENT, EventType.YOUTH_ADVOCATE,
            "", "YFC Conferences",
            EventType.ILC, EventType.SECTOR_CONFERENCE, EventType.PROVINCIAL_CONFERENCE,
            EventType.REGIONAL_CONFERENCE};
    private int endMonth;
    private int startMonth;
    private int noOfMonths;
    private ArrayList<ArrayList> eventArr;
    private ArrayList<Event> parishLinkageArr, otherArr;
    private String title;
    private String footerStr;
    private Date start;
    private Date end;
    private Calendar cStart, cEnd;
    private static CellStyle borderStyle;
    private final CellStyle titleStyle, monthStyle, leftTitleBorder, leftBorder, header2Style;
    private int row = 7;
    private int startOfData = 7;
    private String[] monthStr;
    private ExternalResource externalResource;

    public XSLXReport(String title, String footerStr, Date start, Date end, 
    		ExternalResource externalResource) {
        this.title = title;
        this.footerStr = footerStr;
        this.start = start;
        this.end = end;
        this.externalResource = externalResource;
        wb = new HSSFWorkbook();
        sheet = wb.createSheet("Activity Report");
        PrintSetup ps = sheet.getPrintSetup();

        sheet.setAutobreaks(true);

        ps.setFitHeight((short)1);
        ps.setFitWidth((short)1);

        Footer footer = sheet.getFooter();
        footer.setCenter(footerStr);

        //bold style created
        CellStyle boldStyle = wb.createCellStyle();
        Font boldFont = wb.createFont();
        boldFont.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
        boldStyle.setFont(boldFont);
        boldStyle.setBorderTop(CellStyle.BORDER_DOUBLE);

        Font smallFont = wb.createFont();
        smallFont.setFontHeightInPoints((short) 6);

        borderStyle = wb.createCellStyle();
        borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
        borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
        borderStyle.setBorderRight(CellStyle.BORDER_THIN);
        borderStyle.setBorderTop(CellStyle.BORDER_THIN);

        titleStyle = wb.createCellStyle();
        org.apache.poi.ss.usermodel.Font bigFont = wb.createFont();
        bigFont.setFontHeightInPoints((short)18);
        titleStyle.setFont(bigFont);

        monthStyle = wb.createCellStyle();
        monthStyle.setBorderBottom(CellStyle.BORDER_THIN);
        monthStyle.setBorderLeft(CellStyle.BORDER_THIN);
        monthStyle.setBorderRight(CellStyle.BORDER_THIN);
        monthStyle.setBorderTop(CellStyle.BORDER_MEDIUM);

        leftBorder = wb.createCellStyle();
        leftBorder.setBorderBottom(CellStyle.BORDER_THIN);
        leftBorder.setBorderLeft(CellStyle.BORDER_MEDIUM);
        leftBorder.setBorderRight(CellStyle.BORDER_THIN);
        leftBorder.setBorderTop(CellStyle.BORDER_THIN);

        leftTitleBorder = wb.createCellStyle();
        leftTitleBorder.setBorderBottom(CellStyle.BORDER_THIN);
        leftTitleBorder.setBorderLeft(CellStyle.BORDER_MEDIUM);
        leftTitleBorder.setBorderRight(CellStyle.BORDER_THIN);
        leftTitleBorder.setBorderTop(CellStyle.BORDER_THIN);
        leftTitleBorder.setFont(boldFont);

        header2Style = wb.createCellStyle();
        header2Style.setBorderBottom(CellStyle.BORDER_THIN);
        header2Style.setBorderLeft(CellStyle.BORDER_THIN);
        header2Style.setBorderRight(CellStyle.BORDER_THIN);
        header2Style.setBorderTop(CellStyle.BORDER_THIN);
        header2Style.setFont(smallFont);

        setVariables();
        setTitle();
        setHeaders();
        putDataIntoArray();
        getReportData();

        getParishLinkage();
        getOthers();

        saveToFile();
    }

    private void putDataIntoArray() {
        int month = cStart.get(Calendar.MONTH);
        int year = cStart.get(Calendar.YEAR);
        eventArr = new ArrayList<ArrayList>();

        for (int i = 0; i < noOfMonths; i++) {
            ArrayList<yfcdb.events.Event> monthArr = EventList.getInstance().getEventsOn(
                    month, year);
            System.out.println(monthArr);
            eventArr.add(monthArr);
            if (month++ > 12) {
                month = 0;
                year++;
            }
        }

        parishLinkageArr = EventList.getInstance().getEventsOfType(EventType.PARISH_LINKAGE, start, end);
        otherArr = EventList.getInstance().getEventsOfType(EventType.OTHERS, start, end);
    }

    private void setTitle() {
        String yfcGroup1 = "CLUSTER: 2";
        String yfcGroup2 = "CHAPTER C: (Sta Rosa Village)";

        String chapterLeaders = "CHAPTER LEADERS: " + PersonList.getInstance().getChapterLeaders();
        String coordinators = "COORDINATORS: " + PersonList.getInstance().getCoordinators();

        int groupColumn = 0;
        int leadersColumn = 19;

        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellValue(title);
        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                0, //last row  (0-based)
                0, //first column (0-based)
                (noOfMonths*2)+1  //last column  (0-based)
        ));
        cell.setCellStyle(titleStyle);
        CellUtil.setAlignment(cell, wb, CellStyle.ALIGN_CENTER);

        Row row1 = sheet.createRow(2);
        row1.createCell(groupColumn).setCellValue(yfcGroup1);
        row1.createCell(leadersColumn).setCellValue(chapterLeaders);

        Row row2 = sheet.createRow(3);
        row2.createCell(groupColumn).setCellValue(yfcGroup2);
        row2.createCell(leadersColumn).setCellValue(coordinators);
    }

    private void setVariables() {
        DateFormatSymbols dfs = new DateFormatSymbols();
        monthStr = dfs.getMonths();

        cStart = Calendar.getInstance();
        cStart.setTime(start);
        startMonth = cStart.get(Calendar.MONTH);
        cEnd = Calendar.getInstance();
        cEnd.setTime(end);
        endMonth = cEnd.get(Calendar.MONTH);
        int yearDifference = cEnd.get(Calendar.YEAR) - cStart.get(Calendar.YEAR);
        endMonth += (yearDifference*12) +1;
        noOfMonths = endMonth - startMonth;

    }

    private void setHeaders() {
        String[] headerMonthArr = new String[(noOfMonths*2)+1];
        String[] headerArr2 = new String[(noOfMonths*2)+1];
        int monthCtr = startMonth;
        //month header
        headerMonthArr[0] = "Activities";
        headerArr2[0] = "";
        for (int i = 1; i < headerMonthArr.length && monthCtr < endMonth; i+=2, monthCtr++) {
            headerMonthArr[i] = monthStr[monthCtr];
            headerMonthArr[i+1] = "";
            headerArr2[i] = "DATE";
            headerArr2[i+1] = "# OF ATTENDEES";
            if (startMonth >= 12) monthCtr = 0;
        }

        int monthRow = 5;
        Row headerRow1 = sheet.createRow(monthRow);
        Row headerRow2 = sheet.createRow(6);

        for (int i = 0; i < headerMonthArr.length; i++) {
            headerRow1.createCell(i).setCellValue(headerMonthArr[i].toUpperCase());
            Cell cell = headerRow2.createCell(i);
            cell.setCellValue(headerArr2[i]);
            cell.setCellStyle(header2Style);
            CellUtil.setAlignment(cell, wb, CellStyle.ALIGN_CENTER);
        }

        //merge cells for months
        for (int i = 1; i < noOfMonths*2+1; i++) {
            sheet.addMergedRegion(new CellRangeAddress(
                    monthRow, //first row (0-based)
                    monthRow, //last row  (0-based)
                    i, //first column (0-based)
                    i+=1  //last column  (0-based)
            ));
            Cell cell1 = headerRow1.getCell(i);
            cell1.setCellStyle(monthStyle);
            Cell cell2 = headerRow1.getCell(i-1);
            cell2.setCellStyle(monthStyle);
            CellUtil.setAlignment(cell1, wb, CellStyle.ALIGN_CENTER);
        }

        //merge cells for Activity
        sheet.addMergedRegion(new CellRangeAddress(
                monthRow, //first row (0-based)
                monthRow + 1, //last row  (0-based)
                0, //first column (0-based)
                0  //last column  (0-based)
        ));
        headerRow1.getCell(0).setCellStyle(monthStyle);
    }

    private void getReportData() {
        row = startOfData;
        for (Object obj: activitesArr) {
            if (obj instanceof String) {
                Row r = sheet.createRow(row++);
                Cell cell = r.createCell(0);
                cell.setCellValue((String) obj);
                cell.setCellStyle(leftTitleBorder);
                CellUtil.setAlignment(cell, wb, CellStyle.ALIGN_CENTER);
            } else if (obj instanceof EventType) {
                Row r = sheet.createRow(row++);
                Cell cell = r.createCell(0);
                cell.setCellValue(String.valueOf((EventType) obj));
                cell.setCellStyle(leftBorder);
                setRowData(r);
            }

        }
        sheet.autoSizeColumn(0);
    }

    private void setRowData(Row row) {
        int column = 1;
        for (int i = 0; i < noOfMonths; i++) {
            setColumnData2(row, i, column);
            column+=2;
        }
    }

    private void setRowData(Row row, Event event) {

    }

    private void setColumnData(Row row, Event event, int column) {
        /*String date = "", attendees = "";
            if (event.getType().equals(type)) {
                date += event.getDay() + ",";
                attendees += event.getNoOfAttendees() + ",";
            }
        if (!date.isEmpty()) {
            date = date.substring(0, date.length() - 1);
            attendees = attendees.substring(0, attendees.length() - 1);
        }

        Cell cDate = row.createCell(column);
        cDate.setCellValue(date);
        Cell cAttendees = row.createCell(column+1);
        cAttendees.setCellValue(attendees);
        cDate.setCellStyle(borderStyle);
        cAttendees.setCellStyle(borderStyle);*/
    }

    private void setColumnData2(Row row, int index, int column) {
        EventType type;

        //get event type of row
        String activity = row.getCell(0).getStringCellValue();
        type = EventType.get(activity);

        String date = "", attendees = "";
        //find events of the same even type in eventArr
        for (yfcdb.events.Event event : (ArrayList<yfcdb.events.Event>) eventArr.get(index)) {
            if (event.getType().equals(type)) {
                date += event.getDay() + ",";
                attendees += externalResource.getNoOfEventAttendees(event) + ",";
            }
        }
        if (!date.isEmpty()) {
            date = date.substring(0, date.length() - 1);
            attendees = attendees.substring(0, attendees.length() - 1);
        }

        Cell cDate = row.createCell(column);
        cDate.setCellValue(date);
        Cell cAttendees = row.createCell(column+1);
        cAttendees.setCellValue(attendees);
        cDate.setCellStyle(borderStyle);
        cAttendees.setCellStyle(borderStyle);
    }

    private void getParishLinkage() {
        Row bufferRow = sheet.createRow(row++);
        bufferRow.createCell(0);

        Row titleRow = sheet.createRow(row++);
        Cell cellTitle = titleRow.createCell(0);
        cellTitle.setCellValue("Parish Linkage");
        cellTitle.setCellStyle(leftTitleBorder);
        CellUtil.setAlignment(cellTitle, wb, CellStyle.ALIGN_CENTER);

        for (Event event: parishLinkageArr) {
            Row r = sheet.createRow(row++);
            Cell cell = r.createCell(0);
            cell.setCellValue(event.toString());
            cell.setCellStyle(leftBorder);
            setRowData(r, event);
        }
    }

    private void getOthers() {
        Row bufferRow = sheet.createRow(row++);
        bufferRow.createCell(0);

        Row titleRow = sheet.createRow(row++);
        Cell cellTitle = titleRow.createCell(0);
        cellTitle.setCellValue("Others");
        cellTitle.setCellStyle(leftTitleBorder);
        CellUtil.setAlignment(cellTitle, wb, CellStyle.ALIGN_CENTER);

        for (Event event: otherArr) {
            Row r = sheet.createRow(row++);
            Cell cell = r.createCell(0);
            cell.setCellValue(event.toString());
            cell.setCellStyle(leftBorder);
            setRowData(r, event);
        }
    }

    private void saveToFile() {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(title+".xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Saved");
    }
}
