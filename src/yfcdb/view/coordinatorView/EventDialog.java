package yfcdb.view.coordinatorView;

import javax.swing.*;

/**
 * Created by janaldoustorres on 23/05/15.
 */
public class EventDialog extends JDialog {
    public EventDialog() {
        add(new EventPanel());
        pack();
    }
}
