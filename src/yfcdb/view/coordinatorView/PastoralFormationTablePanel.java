package yfcdb.view.coordinatorView;

import yfcdb.events.Attendee;
import yfcdb.events.Event;
import yfcdb.events.EventList;
import yfcdb.events.EventType;
import yfcdb.events.Role;
import yfcdb.files.ExternalResource;
import yfcdb.member.Member;
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
import java.util.List;

/**
 * Created by janaldoustorres on 05/06/15.
 */
public class PastoralFormationTablePanel extends JPanel implements TablePanelTemplate {
    private final DefaultTableModel defaultTableModel;
    final static SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
    private ExternalResource externalResource;
	private JLabel jlNoOfRows;

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

    public PastoralFormationTablePanel(ExternalResource externalResource) {
    	this.externalResource = externalResource;
        setLayout(new BorderLayout());
        
        JPanel jpTitle = new JPanel();
        JLabel jlTitle = new JLabel("<html><h1>Pastoral Formation Table</h1></html>");
        jlNoOfRows = new JLabel();
        jpTitle.add(jlTitle);
        jpTitle.add(jlNoOfRows);

        String[] columnNames = {"Type", "Member", "Role", "Date"};
        defaultTableModel = new DefaultTableModel(columnNames, 0);

        JTable jtMembers = new JTable(defaultTableModel);
        jtMembers.setAutoCreateRowSorter(true);

        //if pastoral formation even type is typed in then show members who have attended that pastoral formation event

        add(jpTitle, BorderLayout.NORTH);
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
        List<Member> personNotAttendedArrayList = new ArrayList<Member>(personList.getPersonArrayList());

        EventList eventList = EventList.getInstance();
        ArrayList<Event> eventArrayList = eventList.getEventsOfType(type);

        for (Event event: eventArrayList) {
            List<Attendee> attendes = externalResource.getEventAttendees(event);

            Iterator<Attendee> iterator = externalResource.getEventAttendeesIterator(event);

            while (iterator.hasNext()) {
            	Attendee attendee = (Attendee)iterator.next();
                //add to model if person attended
                Member member = attendee.getMember();
                Role role = attendee.getRole();
                Object[] array = {type, member.getShortName(), role, dt.format(event.getStartDate())};
                defaultTableModel.addRow(array);
                //remove from list if attended
                personNotAttendedArrayList.remove(member);
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
        
        int noOfRows = defaultTableModel.getRowCount();
        jlNoOfRows.setText(noOfRows + " rows");
    }
}
