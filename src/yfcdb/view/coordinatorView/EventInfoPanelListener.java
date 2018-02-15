package yfcdb.view.coordinatorView;

import yfcdb.events.Event;
import yfcdb.events.EventList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by janaldoustorres on 01/06/15.
 */
@Deprecated
public class EventInfoPanelListener implements ActionListener {
    private EventFormPanel eventFormPanel;
    private MembersAttendanceTablePanel membersAttendanceTablePanel;

    public EventInfoPanelListener() {
    }

    public EventInfoPanelListener(EventFormPanel eventFormPanel, MembersAttendanceTablePanel membersAttendanceTablePanel) {
        this.eventFormPanel = eventFormPanel;
        this.membersAttendanceTablePanel = membersAttendanceTablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean success = true;
        if (((EventPanel) eventFormPanel).getEvent() == null) {
            ((EventPanel) eventFormPanel).setEvent(new Event());
        }
        if (!eventFormPanel.isFilledOut()) {
            success = false;
        }
        if (success) {
            JOptionPane.showMessageDialog(null, "Success");
            //((EventPanel) eventFormPanel).updateEvent();
            EventList eventList = EventList.getInstance();
            Event event = ((EventPanel) eventFormPanel).getEvent();
            if (!eventList.contains(event)) {
                eventList.addEvent(event);
                eventList.print();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Not filled out");
        }
    }
}
