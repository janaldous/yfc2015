package yfcdb.view.coordinatorView;

import yfcdb.events.*;
import yfcdb.events.Event;
import yfcdb.files.ExternalResource;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by janaldoustorres on 04/06/15.
 */
public class ReportPanel extends JPanel {
    private final DefaultTableModel defaultTableModel;
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
    private Date start, end;
    private Calendar cStart, cEnd;
    private ExternalResource externalResource;

    public ReportPanel(Date start, Date end, ExternalResource externalResource) {
        this.start = start;
        this.end = end;
        this.externalResource = externalResource;
        setLayout(new BorderLayout());

        JLabel jlTitle = new JLabel("Report");

        defaultTableModel = new DefaultTableModel();
        setHeaders();
        putDataIntoArray();
        getReportData();

        JTable jTable = new JTable(defaultTableModel);

        add(jlTitle, BorderLayout.NORTH);
        add(new JScrollPane(jTable), BorderLayout.CENTER);
    }

    private void putDataIntoArray() {
        int month = cStart.get(Calendar.MONTH);
        int year = cStart.get(Calendar.YEAR);
        eventArr = new ArrayList<ArrayList>();

        for (int i = 0; i < noOfMonths; i++) {
            ArrayList<Event> monthArr = EventList.getInstance().getEventsOn(
                    month, year);
            System.out.println(monthArr);
            eventArr.add(monthArr);
            if (month++ > 12) {
                month = 0;
                year++;
            }
        }
    }

    private void setHeaders() {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] monthStr = dfs.getMonths();

        cStart = Calendar.getInstance();
        cStart.setTime(start);
        startMonth = cStart.get(Calendar.MONTH);
        cEnd = Calendar.getInstance();
        cEnd.setTime(end);
        endMonth = cEnd.get(Calendar.MONTH);
        int yearDifference = cEnd.get(Calendar.YEAR) - cStart.get(Calendar.YEAR);
        endMonth += (yearDifference*12) +1;
        noOfMonths = endMonth - startMonth;
        String[] headerMonthArr = new String[(noOfMonths*2)+1];
        String[] headerArr2 = new String[(noOfMonths*2)+1];
        int monthCtr = startMonth;
        //month header
        headerMonthArr[0] = "Activities";
        headerArr2[0] = "";
        for (int i = 1; i < headerMonthArr.length && monthCtr < endMonth; i+=2, monthCtr++) {
            headerMonthArr[i] = monthStr[monthCtr];
            headerMonthArr[i+1] = "";
            headerArr2[i] = "Date";
            headerArr2[i+1] = "#ofAttendees";
            if (startMonth >= 12) monthCtr = 0;
        }
        defaultTableModel.setColumnCount(headerMonthArr.length);
        defaultTableModel.addRow(headerMonthArr);
        defaultTableModel.addRow(headerArr2);
    }

    private void getReportData() {
        int row = 2;
        for (Object obj: activitesArr) {
            if (obj instanceof String) {
                defaultTableModel.addRow(new Object[] {(String)obj});
            } else if (obj instanceof EventType) {
                defaultTableModel.addRow(new Object[] {(EventType)obj});
            }
            row++;
        }
        setColumns();
    }

    private void setColumns() {
        int column = 1;
        for (int i = 0; i < noOfMonths; i++) {
            setColumnData(i, column);
            column+=2;
        }
    }

    private void setColumnData(int index, int column) {
        EventType type;



        for (int row = 0; row < defaultTableModel.getRowCount(); row++) {
            //get eventType
            if (defaultTableModel.getValueAt(row, 0) instanceof EventType) {
                type = (EventType) defaultTableModel.getValueAt(row, 0);
                String date = "", attendees = "";
                //if event type is same as event type
                for (Event event: (ArrayList<Event>)eventArr.get(index)) {
                    if (event.getType().equals(type)) {
                        date += event.getDay() + ",";
                        attendees += externalResource.getNoOfEventAttendees(event) + ",";
                    }
                }
                if (!date.isEmpty()) {
                    date = date.substring(0, date.length()-1);
                    attendees = attendees.substring(0, attendees.length()-1);
                }
                defaultTableModel.setValueAt(date, row, column);
                defaultTableModel.setValueAt(attendees, row, column + 1);
            }
        }
    }
}
