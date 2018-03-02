package yfcdb.view.coordinatorView;

import yfcdb.member.Member;
import yfcdb.member.Person;
import yfcdb.member.PersonList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by janaldoustorres on 31/05/15.
 */
public class MembersTablePanel extends JPanel {
    private DefaultTableModel defaultTableModel;
    final static String[] globePrefix = {"0905", "0906", "0915", "0915", "0916", "0917", "0926", "0927", "0935", "0936",
            "0937", "0994", "0996", "0997"};
    final static String[] smartPrefix = {"0907", "0907", "0908", "0909", "0910", "0912", "0918", "0919", "0920", "0921",
            "0938", "0939", "0947", "0948", "0949", "0998", "0999"};

    private RowFilter<DefaultTableModel,Integer> globeFilter = new RowFilter<DefaultTableModel,Integer>() {
        public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
            String phoneNumber = (String)entry.getValue(5);
            //true if in globePrefix array
            for (String prefix: globePrefix) {
                if (phoneNumber.startsWith(prefix)) {
                    return true;
                }
            }
            return false;
        }
    };

    private RowFilter<DefaultTableModel,Integer> smartFilter = new RowFilter<DefaultTableModel,Integer>() {
        public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
            String phoneNumber = (String)entry.getValue(5);
            //true if in smartPrefix array
            for (String prefix: smartPrefix) {
                if (phoneNumber.startsWith(prefix)) {
                    return true;
                }
            }
            return false;
        }
    };

    public MembersTablePanel() {
        setLayout(new BorderLayout());
        JLabel jlTitle = new JLabel("<html><h1>Members List</h1></html>");

        String[] columnNames = {"toString()", "Position", "Last Name", "First Name", "Birthday", "Contact No"};
        defaultTableModel = new DefaultTableModel(columnNames, 0);


        JTable jtMembers = new JTable(defaultTableModel);
        jtMembers.setAutoCreateRowSorter(true);
        populateTable();

        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(defaultTableModel);
        jtMembers.setRowSorter(sorter);

        JPanel jpFilter = new JPanel();
        final JTextField jtfSearchBar = new JTextField(20);
        JButton jbFilter = new JButton("Filter");
        jbFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jtfSearchBar.getText();

                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else if (text.equalsIgnoreCase("globe")) {
                    sorter.setRowFilter(globeFilter);
                } else if (text.equalsIgnoreCase("smart")) {
                    sorter.setRowFilter(smartFilter);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });
        jpFilter.add(jtfSearchBar);
        jpFilter.add(jbFilter);

        add(jlTitle, BorderLayout.NORTH);
        add(new JScrollPane(jtMembers), BorderLayout.CENTER);
        add(jpFilter, BorderLayout.SOUTH);
    }

    private void populateTable() {
        PersonList personList = PersonList.getInstance();
        List<Member> personArrayList = personList.getPersonArrayList();
        for (Member person: personArrayList) {
            defaultTableModel.addRow(person.toArray());
        }
    }
}
