package yfcdb.view.coordinatorView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import yfcdb.events.Event;
import yfcdb.events.EventList;
import yfcdb.files.ExternalResource;

/**
 * Created by janaldoustorres on 31/05/15.
 */
public class EventsInfoPanel extends JPanel {
    private final EventPanel eventPanel;
    private final MembersAttendanceTablePanel membersAttendanceTablePanel;
    private Event event;
    private MainWindow mainWindow;
    private ExternalResource externalResource;

    private class EventInfoPanelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            boolean success = eventPanel.isFilledOut();
            
            if (success) {
            	updateEvent();
            	event = membersAttendanceTablePanel.getEvent();
            	System.out.println("="+event.getStartDate());

            	externalResource.updateOrSaveEvent(event);
            	changeMainWindow();

            	EventList eventList = EventList.getInstance();
                if (!eventList.contains(event)) {
                    eventList.addEvent(event);
                    eventList.print();
                }
                JOptionPane.showMessageDialog(null, "Success");
            } else {
                JOptionPane.showMessageDialog(null, "Not filled out");
            }
        }
    }

    public EventsInfoPanel(final MainWindow mainWindow, 
    		ExternalResource externalResource) {
        this.mainWindow = mainWindow;
        this.externalResource = externalResource;
        this.event = new Event();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20) );

        JPanel centerPanel = new JPanel(new GridLayout(2,1));
        
        eventPanel = new EventPanel(event);
        membersAttendanceTablePanel = new MembersAttendanceTablePanel(externalResource, event);
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

    public EventsInfoPanel(MainWindow mainWindow, String title, 
    		ExternalResource externalResource) {
        this(mainWindow, new Event(), externalResource);
        String html = "<html><h1>" + title + "</h1></html>";
        add(new JLabel(html), BorderLayout.NORTH);
    }
    
    public EventsInfoPanel(final MainWindow mainWindow, Event event, 
    		ExternalResource externalResource) {
    	this(mainWindow, externalResource);
    	eventPanel.setEvent(event);
    	eventPanel.setInfo(event);
    	membersAttendanceTablePanel.setEvent(event);
    	membersAttendanceTablePanel.setInfo(event);
    }
    
    public void updateEvent() {
        eventPanel.updateEvent();
        membersAttendanceTablePanel.updateEvent();
    }
    
    public void changeMainWindow() {
        mainWindow.changeCenterPanelToEvent(event);
    }

    public Event getEvent() {
        return event;
    }
}
