package yfcdb.view.coordinatorView;

import yfcdb.events.Event;
import yfcdb.events.EventType;
import yfcdb.member.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by janaldoustorres on 23/05/15.
 */
public class EventPanel extends EventFormPanel {
    private Event event;
    private final JTextField jtfEventName;
    private final JComboBox<EventType> jcbEventType;
    private final DatePanelWithTime startDatePanel, endDatePanel;
    private final JTextField jtfVenue;
    private final JTextField jtfRegFee;
    private final JTextField jtfNotes;
    final static SimpleDateFormat dt = new SimpleDateFormat("yyyy");

    public EventPanel() {
        setLayout(new GridLayout(7,2));
        
        JLabel jlEventName = new JLabel("Name of Event *:");
        jtfEventName = new JTextField("");
        jtfEventName.setEditable(false);

        JLabel jlEventType = new JLabel("Type of Event *:");
        jcbEventType = new JComboBox<EventType>(EventType.values());
        jcbEventType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventType selectedType = (EventType)jcbEventType.getSelectedItem();
                if(selectedType != null) {
                	if (selectedType.equals(EventType.OTHERS) || selectedType.equals(EventType.PARISH_LINKAGE)
                            || selectedType.equals(EventType.SECTOR_CONFERENCE)
                            || selectedType.equals(EventType.PROVINCIAL_CONFERENCE)
                            || selectedType.equals(EventType.REGIONAL_CONFERENCE)) {
                        jtfEventName.setEditable(true);
                    } else {
                        jtfEventName.setText(selectedType.toString());
                        jtfEventName.setEditable(false);
                    }
                }
            }
        });

        JLabel jlStartDate = new JLabel("Start Date *:");
        startDatePanel = new DatePanelWithTime();

        JLabel jlEndDate = new JLabel("End Date *:");
        endDatePanel = new DatePanelWithTime();

        JLabel jlVenue = new JLabel("Venue *:");
        jtfVenue = new JTextField("");

        JLabel jlRegFee = new JLabel("Reg Fee:");
        jtfRegFee = new JTextField("");

        JLabel jlNotes = new JLabel("Notes:");
        jtfNotes = new JTextField("");

        add(jlEventName);
        add(jtfEventName);
        add(jlEventType);
        add(jcbEventType);
        add(jlStartDate);
        add(startDatePanel);
        add(jlEndDate);
        add(endDatePanel);
        add(jlVenue);
        add(jtfVenue);
        add(jlRegFee);
        add(jtfRegFee);
        add(jlNotes);
        add(jtfNotes);
    }
    
    public EventPanel(Event event) {
    	this();
    	this.event = event;
    }

    @Override
    public void setInfo(Event event) {
        jtfEventName.setText(event.getName());
        jcbEventType.setSelectedItem(event.getType());
        startDatePanel.setDate(event.getStartDate());
        endDatePanel.setDate(event.getEndDate());
        jtfVenue.setText(event.getVenue());
        jtfRegFee.setText(String.valueOf(event.getRegFee()));
        jtfNotes.setText(event.getNotes());
    }

    @Override
    public Event getInfo(Event event) {
        String eventName = jtfEventName.getText();
        EventType eventType = (EventType) jcbEventType.getSelectedItem();
        Date start = startDatePanel.getDate();
        Date end = endDatePanel.getDate();
        String venue = jtfVenue.getText();
        double regFee = Double.parseDouble(jtfRegFee.getText());
        String notes = jtfNotes.getText();
        
        System.out.println(start);

        event.setName(eventName);
        event.setType(eventType);
        event.setStartDate(start);
        event.setEndDate(end);
        event.setVenue(venue);
        event.setRegFee(regFee);
        event.setNotes(notes);

        return event;
    }

    @Override
    public boolean isFilledOut() {
        try {
            Double.parseDouble(jtfRegFee.getText());
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Reg fee must be a double");
        	return false;
        }
        if (jcbEventType.getSelectedItem().equals("") || jtfVenue.getText().equals("")
                || jtfRegFee.getText().equals("")) {
        	JOptionPane.showMessageDialog(null, "Fill out required fields");
            return false;
        }
        return true;
    }
    
    @Override
    public void updateEvent() {
    	event = getInfo(event);
    }
    
    @Override
    public Event getEvent() {
    	updateEvent();
        return event;
    }

    public void setEvent(Event event) { this.event = event; }
}
