package yfcdb.view.coordinatorView;

import yfcdb.events.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by janaldoustorres on 01/06/15.
 */
public class EventsTablePanel extends JPanel {
    private class TableSelectionModel extends DefaultListSelectionModel {
        @Override
        public void addListSelectionListener(ListSelectionListener l) {
            super.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println(e.getLastIndex());
                }
            });
        }
    }
    private final DefaultTableModel defaultTableModel;

    public EventsTablePanel() {
        setLayout(new BorderLayout());
        JLabel jlTitle = new JLabel("<html><h1>Events List</h1></html>");

        String[] columnNames = {"Date", "Name", "Type", "Venue", "Notes", "Attendees"};
        defaultTableModel = new DefaultTableModel(columnNames, 0);

        populateTable();


        JTable jtEventsTable = new JTable(defaultTableModel);
        jtEventsTable.setSelectionModel(new TableSelectionModel());
        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(defaultTableModel);
        jtEventsTable.setRowSorter(sorter);

        JPanel jpFilter = new JPanel();
        final JTextField jtfSearchBar = new JTextField(20);
        JButton jbFilter = new JButton("Filter");
        jbFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jtfSearchBar.getText();

                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });
        jpFilter.add(jtfSearchBar);
        jpFilter.add(jbFilter);


        add(jlTitle, BorderLayout.NORTH);
        add(new JScrollPane(jtEventsTable), BorderLayout.CENTER);
        add(jpFilter, BorderLayout.SOUTH);
    }

    private void populateTable() {
        EventList eventList = EventList.getInstance();
        for (yfcdb.events.Event event: eventList.getEventArrayList()) {
            defaultTableModel.addRow(event.toArray());
        }
    }


}
