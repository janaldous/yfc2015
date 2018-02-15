package yfcdb.view.coordinatorView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by janaldoustorres on 22/05/15.
 */
public class DatePanel extends JPanel implements ChangeListener {
    private static final int yearRange = 23;
    protected final JSpinner spinner;
    protected final SpinnerDateModel model;
    private CoordinatorInfoPanel.BriefingPanel briefingPanel1;
    private MemberInfoPanel.BriefingPanel briefingPanel;

    public DatePanel(MemberInfoPanel.BriefingPanel briefingPanel) {
        this();
        this.briefingPanel = briefingPanel;
        spinner.addChangeListener(this);
    }

    public DatePanel(CoordinatorInfoPanel.BriefingPanel briefingPanel1) {
        this();
        this.briefingPanel1 = briefingPanel1;
        spinner.addChangeListener(this);
    }

    public DatePanel() {
        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -100);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date latestDate = calendar.getTime();
        model = new SpinnerDateModel(initDate,
                earliestDate,
                latestDate,
                Calendar.YEAR);
        spinner = new JSpinner(model);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "MMM dd yyyy"));


        add(spinner);
    }

    public void setDate(Date date) {
        spinner.setValue(date);
    }

    public Date getDate() {
        return (Date) spinner.getValue();
    }

    public int getYearsFromToday() {
        Date date = (Date) spinner.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        Calendar calToday = Calendar.getInstance();

        int age = calToday.get(Calendar.YEAR) - year;

        if (calToday.get(Calendar.MONTH) < month) {
            age--;
        } else if ((calToday.get(Calendar.MONTH) == month)
                && (calToday.get(Calendar.DAY_OF_MONTH) < day)) {
            age--;
        }


        return age;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (briefingPanel == null) {
            briefingPanel1.setAge(getYearsFromToday());
        } else {
            briefingPanel.setAge(getYearsFromToday());
        }
    }
}
