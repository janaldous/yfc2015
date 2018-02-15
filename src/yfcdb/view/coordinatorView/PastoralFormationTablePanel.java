package yfcdb.view.coordinatorView;

import yfcdb.events.Event;
import yfcdb.events.EventList;
import yfcdb.events.EventType;
import yfcdb.events.Role;
import yfcdb.member.Person;
import yfcdb.member.PersonList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by janaldoustorres on 05/06/15.
 */
public class PastoralFormationTablePanel extends JPanel implements TablePanelTemplate {
    private final DefaultTableModel defaultTableModel;
    final static SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");

    private class FilterPanel extends JPanel {
        private final String[] attendToggleArr = {"Attended", "Did not attend"};

        private FilterPanel() {
            final JComboBox<String> jcbAttendToggle = new JComboBox<String>(attendToggleArr);
            final JComboBox<EventType> jcbPastoralFormation = new JComboBox<EventType>(EventType.pastoralValues());
            JButton jbFilter = new JButton("Filter");
            jbFilter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EventType type = (EventType) jcbPastoralFormation.getSelectedItem();
                    boolean attended = jcbAttendToggle.getSelectedIndex() == 0 ? true: false;
                    filterTable(attended, type);
                }
            });
            add(jcbAttendToggle);
            add(jcbPastoralFormation);
            add(jbFilter);
        }
    }

    public PastoralFormationTablePanel() {
        setLayout(new BorderLayout());

        JLabel jlTitle = new JLabel("<html><h1>Pastoral Formation Table</h1></html>");

        String[] columnNames = {"Type", "Member", "Role", "Date"};
        defaultTableModel = new DefaultTableModel(columnNames, 0);

        JTable jtMembers = new JTable(defaultTableModel);
        jtMembers.setAutoCreateRowSorter(true);

        //if pastoral formation even type is typed in then show members who have attended that pastoral formation event

        add(jlTitle, BorderLayout.NORTH);
        add(new JScrollPane(jtMembers), BorderLayout.CENTER);
        add(new FilterPanel(), BorderLayout.SOUTH);
    }

    public void populateTable() {
//        EventList eventList = EventList.getInstance();
//        ArrayList<Event> arrayList = eventList.getPastoralFormationEvents();
//
//        for (Event event: arrayList) {
//            defaultTableModel.addRow(event.toArray());
//        }
    }

    private void filterTable(boolean attended, EventType type) {
        //clear table
        defaultTableModel.setRowCount(0);

        PersonList personList = PersonList.getInstance();
        ArrayList<Person> personNotAttendedArrayList = personList.getPersonArrayList();

        EventList eventList = EventList.getInstance();
        ArrayList<Event> eventArrayList = eventList.getEventsOfType(type);

        for (Event event: eventArrayList) {
            HashMap<Person, Role> personRoleHashMap = event.getAttendees();

            Iterator iterator = personRoleHashMap.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry)iterator.next();
                //add to model if person attended
                Person person = (Person)pair.getKey();
                Role role = (Role)pair.getValue();
                Object[] array = {type, person.getShortName(), role, dt.format(event.getStartDate())};
                defaultTableModel.addRow(array);
                //remove from list if attended
                personNotAttendedArrayList.remove(person);
            }
        }

        if (!attended) {
            defaultTableModel.setRowCount(0);
            for (Person person: personNotAttendedArrayList) {
                //add to model if person did not attend
                Object[] array = {type, person.getShortName(), null, null};
                defaultTableModel.addRow(array);
            }
        }
    }
}
