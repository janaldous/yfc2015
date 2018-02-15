package yfcdb.view.coordinatorView;

import yfcdb.member.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 * Created by janaldoustorres on 22/05/15.
 */
@Deprecated
public class BriefingPanel extends JPanel {
    private static final int ageRange = 9;

    public BriefingPanel() {
        super(new GridLayout(6, 2));

        JLabel jlId = new JLabel("Member ID:");
        JTextField jtfId = new JTextField();
        jtfId.setEditable(false);

        JLabel jlDateLastUpdated = new JLabel("Date Last Updated:");
        JTextField jtfDateLastUpdated = new JTextField("jo");
        jtfDateLastUpdated.setEditable(false);

        JLabel jlAge = new JLabel("Age:");
        JTextField jtfAge = new JTextField();
        jtfAge.setEditable(false);

        JLabel jlYfcAge = new JLabel("Years in YFC:");
        JTextField jtfYfcAge = new JTextField();
        jtfYfcAge.setEditable(false);

        JLabel jlPosition = new JLabel("YFC Position:");
        JComboBox jcbPositions = new JComboBox<String>(Position.getPositions());


        String[] yfcAgeList = new String[ageRange];
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < yfcAgeList.length; i++) { yfcAgeList[i] = Integer.toString(currentYear-i); }
        JLabel jlEntryYear = new JLabel("Year joined:");
        JComboBox jcbEntryYear = new JComboBox<String>(yfcAgeList);

        add(jlId);
        add(jtfId);
        add(jlDateLastUpdated);
        add(jtfDateLastUpdated);
        add(jlAge);
        add(jtfAge);
        add(jlYfcAge);
        add(jtfYfcAge);
        add(jlPosition);
        add(jcbPositions);
        add(jlEntryYear);
        add(jcbEntryYear);
    }
}
