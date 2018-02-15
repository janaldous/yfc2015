package yfcdb.view.coordinatorView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import yfcdb.events.Event;
import yfcdb.events.EventList;

/**
 * Created by janaldoustorres on 31/05/15.
 */
public class EventsInfoPanel extends JPanel {
    private final EventPanel eventPanel;
    private final MembersAttendanceTablePanel membersAttendanceTablePanel;
    private Event event;
    private MainWindow mainWindow;

    private class EventInfoPanelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean success = true;
            if (event == null) {
                event = new Event();
                eventPanel.setEvent(event);
            }
            if (!eventPanel.isFilledOut()) {
                success = false;
            }
            if (success) {
                JOptionPane.showMessageDialog(null, "Success");
                updateEvent();
                EventList eventList = EventList.getInstance();
                if (!eventList.contains(event)) {
                    eventList.addEvent(event);
                    eventList.print();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Not filled out");
            }
        }
    }

    public EventsInfoPanel(final MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20) );

        JPanel centerPanel = new JPanel(new GridLayout(2,1));
        eventPanel = new EventPanel();
        membersAttendanceTablePanel = new MembersAttendanceTablePanel();
        centerPanel.add(eventPanel);
        centerPanel.add(membersAttendanceTablePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jbSave = new JButton("Save");
        EventInfoPanelListener eventInfoPanelListener = new EventInfoPanelListener();
        jbSave.addActionListener(eventInfoPanelListener);
        JButton jbDelete = new JButton("Delete");
        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventList eventList = EventList.getInstance();
                eventList.removeEvent(event);
                mainWindow.changeCenterPanelToEmpty();
            }
        });
        buttonPanel.add(jbDelete);
        buttonPanel.add(jbSave);

        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public EventsInfoPanel(MainWindow mainWindow, String title) {
        this(mainWindow);
        String html = "<html><h1>" + title + "</h1></html>";
        add(new JLabel(html), BorderLayout.NORTH);
    }

    public EventsInfoPanel(MainWindow mainWindow, Event event) {
        this(mainWindow);
        this.event = event;
        eventPanel.setInfo(event);
        membersAttendanceTablePanel.setInfo(event);
    }

    public void updateEvent() {
        eventPanel.updateEvent(event);
        membersAttendanceTablePanel.updateEvent(event);
        mainWindow.changeCenterPanelToEvent(event);
    }

    public Event getEvent() {
        return event;
    }
}
