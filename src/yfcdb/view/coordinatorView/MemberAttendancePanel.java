package yfcdb.view.coordinatorView;

import yfcdb.events.*;
import yfcdb.events.Event;
import yfcdb.member.Member;
import yfcdb.member.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by janaldoustorres on 22/05/15.
 */
public class MemberAttendancePanel extends JPanel {
    private JLabel jlPrompt = new JLabel();
    private Member member;
    private DefaultTableModel defaultTableModel;
    private int attendedEvents = 0;

    //TODO fix this=============================================================================
    public MemberAttendancePanel(Member member) {
        this.member = member;
        setLayout(new BorderLayout());
        String[] columnNames = {"Date", "Event", "Role"};
        defaultTableModel = new DefaultTableModel(columnNames, 0);
        //populateTable();

        JTable jtTable = new JTable(defaultTableModel); //TODO fix this table

        add(new JScrollPane(jtTable), BorderLayout.CENTER);
        add(jlPrompt, BorderLayout.SOUTH);
    }

    /*private void populateTable() {
        EventList eventList = EventList.getInstance();
        List<Event> eventArrayList = eventList.getEventsAttendedBy(member);

        for (Event event: eventArrayList) {
            defaultTableModel.addRow(event.toArray(member));
        }
        setAttendedEvents();
    }*/

    private void setAttendedEvents() {
        int noOfEvents = defaultTableModel.getRowCount();
        String prompt = "You have attended " + noOfEvents + " events";
        jlPrompt.setText(prompt);
    }
}
