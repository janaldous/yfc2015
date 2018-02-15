package yfcdb.view.coordinatorView;

import yfcdb.events.*;
import yfcdb.events.Event;
import yfcdb.member.Member;
import yfcdb.member.MemberList;
import yfcdb.member.Person;
import yfcdb.member.PersonList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by janaldoustorres on 01/06/15.
 */
public class MembersAttendanceTablePanel extends JPanel {
    private DefaultTableModel defaultTableModel;

    public MembersAttendanceTablePanel() {
        setLayout(new BorderLayout());
        JLabel jlTitle = new JLabel("Members List");

        String[] columnNames = {"Name", "Role"};
        defaultTableModel = new DefaultTableModel(columnNames, 0);


        JTable jtMembers = new JTable(defaultTableModel);
        jtMembers.setPreferredScrollableViewportSize(new Dimension(200, 300));
        setUpRoleColumn(jtMembers.getColumnModel().getColumn(1));

        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(defaultTableModel);
        jtMembers.setRowSorter(sorter);

        populateTable();

        add(jlTitle, BorderLayout.NORTH);
        add(new JScrollPane(jtMembers), BorderLayout.CENTER);
    }

    private void populateTable() {
        PersonList personList = PersonList.getInstance();
        ArrayList<Person> personArrayList = personList.getPersonArrayList();
        for (int row = 0; row < personArrayList.size(); row++) {
            defaultTableModel.addRow(new Object[] {personArrayList.get(row), null});
        }
    }

    private void setUpRoleColumn(TableColumn roleColumn) {
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("");
        for (Role role: Role.values()) {
            comboBox.addItem(role);
        }
        roleColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click to mark present, leave blank for absent");
        roleColumn.setCellRenderer(renderer);
    }

    public void getInfo(yfcdb.events.Event event) {
        HashMap<Person, Role> attendeeMap = event.getAttendees();

        for (int row = 0; row < defaultTableModel.getRowCount(); row++) {
            Person person = (Person)defaultTableModel.getValueAt(row, 0);
            Role role = (Role) defaultTableModel.getValueAt(row, 1);
            if (role != null) {
                attendeeMap.putIfAbsent(person, role);
            }
        }

        System.out.println(event.getAttendees());
    }

    public yfcdb.events.Event setInfo(yfcdb.events.Event event) {
        HashMap<Person, Role> attendeeMap = event.getAttendees();

        for (int row = 0; row < defaultTableModel.getRowCount(); row++) {
            Person person = (Person)defaultTableModel.getValueAt(row, 0);
            if (attendeeMap.containsKey(person)) {
                //put role
                Role role = attendeeMap.get(person);
                //sets role at this Table
                defaultTableModel.setValueAt(role, row, 1);
            } else {
                continue;
            }
        }
        return event;
    }

    public void updateEvent(Event event) {
        getInfo(event);
    }
}
