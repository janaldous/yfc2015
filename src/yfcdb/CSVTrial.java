package yfcdb;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by janaldoustorres on 01/06/15.
 */
public class CSVTrial {
    public static void main(String[] args) throws IOException {
        char comma_separator = ',';
        String comma = ",";
        CSVWriter writer = new CSVWriter(new FileWriter("report.csv"), comma_separator);
        // feed in your array (or convert your data to an array)

        String[] title = "2012 WEST 2 YFC ACTIVITY REPORT".split(comma);
        String[] yfcGroup = "Cluster,2,Chapter,c".split(comma);
        String[] leaders = "CHAPTERLEADERS:, ChristianAlvarez/AlyssaAguas, CHAPTER COORDINATORS :, Mike and Elsie Torres".split(comma);
        String[] header = "Activities, Jan,, Feb,, Mar,, Apr,, May,, Jun".split(comma);
        String[] header2 = ",Date, #ofAttendees,Date, #ofAttendees,Date, #ofAttendees,Date, #ofAttendees,Date, #ofAttendees".split(comma);
        String[] monthlyGathering = "Monthly Gathering".split(comma);
        String[] ca = "Chapter Assembley, 22-Jan, 15,5-Feb, 14, 11-Mar, 10, 15-Apr, 21, ,,12-JUn,35".split(comma);
        String[] pastoralFormation = "Pastoral Formation".split(comma);
        String[] yfcConferences = "YFC Conferences".split(comma);
        String[] parishLinkages = "Parish Linkages".split(comma);
        String[] others = "Others".split(comma);

        writer.writeNext(title);
        writer.writeNext(yfcGroup);
        writer.writeNext(leaders);
        writer.writeNext(header);
        writer.writeNext(header2);
        writer.writeNext(monthlyGathering);
        writer.writeNext(ca);
        writer.writeNext(pastoralFormation);
        writer.writeNext(yfcConferences);
        writer.writeNext(parishLinkages);
        writer.writeNext(others);
        writer.close();
    }
}
