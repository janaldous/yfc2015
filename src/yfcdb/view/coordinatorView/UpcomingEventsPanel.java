package yfcdb.view.coordinatorView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by janaldoustorres on 22/05/15.
 */
public class UpcomingEventsPanel extends JPanel {
    private class EventPanel extends JPanel {
        private EventPanel(String eventStr) {
            JLabel jlEvent = new JLabel(eventStr);
            JButton jbMoreInfo = new JButton("More info");

            add(jlEvent);
            add(jbMoreInfo);
        }
    }

    public UpcomingEventsPanel() {
        super(new BorderLayout());
        int numOfEvents = 10;

        String sampleEvent = "<html><h1>Event name</h1><p>more event info here</p></html>";

        JPanel jpEvents = new JPanel();
        jpEvents.setLayout(new GridLayout(numOfEvents, 1));
        for (int i = 0; i < numOfEvents; i++) {
            jpEvents.add(new EventPanel(sampleEvent));
        }

        add(jpEvents, BorderLayout.CENTER);
    }
}
