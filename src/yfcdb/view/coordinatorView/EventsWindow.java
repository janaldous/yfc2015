package yfcdb.view.coordinatorView;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by janaldoustorres on 22/05/15.
 */
public class EventsWindow extends JFrame {
    public EventsWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        String[] columnNames = {"Date", "Name", "Type", "Venue", "Attendees"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnNames, 0);
        defaultTableModel.addRow(new String[] {"2/15/2013", "Parents Honoring", "PF", "SRE clubhouse", "3"});
        defaultTableModel.addRow(new String[] {"2/14/2013", "Chapter Assembly", "CA", "JP2", "6"});
        defaultTableModel.addRow(new String[]{"11,25,2012", "Chapter Assembly", "CA", "JP2", "5"});


        JTable jtEventsTable = new JTable(defaultTableModel);

        JButton jbAddEvent = new JButton("+");
        jbAddEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EventDialog().setVisible(true);
            }
        });

        add(new JScrollPane(jtEventsTable), BorderLayout.CENTER);
        add(jbAddEvent, BorderLayout.SOUTH);

        pack();
    }

    public static void main(String[] args) {
        new EventsWindow().setVisible(true);
    }
}
