package yfcdb.view.coordinatorView;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by janaldoustorres on 03/06/15.
 */
public class DatePanelWithTime extends DatePanel {
    public DatePanelWithTime() {
        super();
        spinner.setEditor(new JSpinner.DateEditor(spinner, "MMM dd yyyy h:mm a"));
    }
}
