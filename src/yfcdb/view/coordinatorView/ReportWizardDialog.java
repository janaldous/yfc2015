package yfcdb.view.coordinatorView;

import yfcdb.files.ExternalResource;
import yfcdb.files.XSLXReport;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by janaldoustorres on 01/06/15.
 */
public class ReportWizardDialog extends JDialog implements ActionListener {

    private final JTextField jtfReportTitle;
    private final JTextArea jtaReportFooter;
    private final DateSpinner dateSpinner;
    private final JComboBox jcbFileType;
    private MainWindow mainWindow;
    private ExternalResource externalResource;

    private class DateSpinner extends JPanel implements ChangeListener {
        private final JSpinner spinner;

        private DateSpinner() {
            Calendar calendar = Calendar.getInstance();
            Date initDate = calendar.getTime();
            calendar.add(Calendar.YEAR, -100);
            Date earliestDate = calendar.getTime();
            calendar.add(Calendar.YEAR, 200);
            Date latestDate = calendar.getTime();
            SpinnerDateModel model = new SpinnerDateModel(initDate,
                    earliestDate,
                    latestDate,
                    Calendar.YEAR);
            spinner = new JSpinner(model);
            spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy"));
            spinner.addChangeListener(this);

            add(spinner);
        }

        private void setDate(Date date) {
            spinner.setValue(date);
        }

        private Date getDate() {
            Date date = (Date)spinner.getValue();
            return date;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateSpinner.getDate());
            int year = cal.get(Calendar.YEAR);
            changeYear(year);
        }
    }

    public ReportWizardDialog(MainWindow mainWindow, ExternalResource externalResource) {
        this.mainWindow = mainWindow;
        this.externalResource = externalResource;
        setLayout(new GridLayout(5, 2));
        setTitle("Report Wizard");

        JLabel jlReportTitle = new JLabel("Report title");
        jtfReportTitle = new JTextField("WEST 2 YFC ACTIVITY REPORT");
        changeYear(Calendar.getInstance().get(Calendar.YEAR));

        JLabel jlReportFooter = new JLabel("Footer (for notes):");
        jtaReportFooter = new JTextArea("");
        jtaReportFooter.setToolTipText("Enter for notes");

        JLabel jlDateYear = new JLabel("Year");
        dateSpinner = new DateSpinner();

        JLabel jlFileType = new JLabel("File type");
        jcbFileType = new JComboBox(new String[] {"xls", "csv", "pdf"});

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jbCancel = new JButton("Cancel");
        JButton jbSave = new JButton("Create");
        jbSave.addActionListener(this);
        bottomPanel.add(jbCancel);
        bottomPanel.add(jbSave);

        add(jlReportTitle);
        add(jtfReportTitle);
        add(jlReportFooter);
        add(jtaReportFooter);
        add(jlDateYear);
        add(dateSpinner);
        add(jlFileType);
        add(jcbFileType);
        add(bottomPanel);

        pack();
    }

    private boolean isFilledOut() {
        if (jtfReportTitle.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isFilledOut()) {
            String title = jtfReportTitle.getText().toUpperCase();
            String footer = jtaReportFooter.getText();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateSpinner.getDate());
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            Date start = cal.getTime();
            cal.set(Calendar.DAY_OF_MONTH, 31);
            cal.set(Calendar.MONTH, Calendar.DECEMBER);
            Date end = cal.getTime();

            new XSLXReport(title, footer, start, end, externalResource);

            mainWindow.changeCenterPanelToReportTable(start, end);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "not filled out");
        }
    }

    public void changeYear(int year) {
        String title = year + " WEST 2 YFC ACTIVITY REPORT";
        jtfReportTitle.setText(title);
    }
}
