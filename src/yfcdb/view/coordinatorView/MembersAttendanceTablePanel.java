package yfcdb.view.coordinatorView;

import yfcdb.events.Attendee;
import yfcdb.events.Event;
import yfcdb.events.Role;
import yfcdb.files.ExternalResource;
import yfcdb.member.Member;
import yfcdb.member.Person;
import yfcdb.member.PersonList;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by janaldoustorres on 01/06/15.
 */
public class MembersAttendanceTablePanel extends JPanel {
    private DefaultTableModel defaultTableModel;
    private ExternalResource externalResource;
    private Event event;

    public MembersAttendanceTablePanel(ExternalResource externalResource, Event event) {
    	this.externalResource = externalResource;
    	this.event = event;
    	
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
        List<Member> personArrayList = personList.getPersonArrayList();
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

    public void getInfo() {
        List<Attendee> attendees = new ArrayList<Attendee>();

        for (int row = 0; row < defaultTableModel.getRowCount(); row++) {
            Member member = (Member)defaultTableModel.getValueAt(row, 0);
            try {
            	Role role = (Role) defaultTableModel.getValueAt(row, 1);
            	if (role != null) {
                	attendees.add(new Attendee(member, role));
                }
            } catch (ClassCastException e) {
            	//cell = null or "" (empty string)
            	continue;
            }
        }
        
        event.setAttendees(attendees);
    }

    public Event setInfo(Event event) {    	
    	
        for (int row = 0; row < defaultTableModel.getRowCount(); row++) {
            Member member = (Member)defaultTableModel.getValueAt(row, 0);
            for(Iterator<Attendee> iter = externalResource.getEventAttendeesIterator(event); iter.hasNext();) {
            	Attendee attendee = iter.next();
            	if(member.equals(attendee.getMember())) {
            		//get role
                    Role role = attendee.getRole();
                    //sets role at this Table
                    defaultTableModel.setValueAt(role, row, 1);
            		break;
            	}
            }
        }
        
        return event;
    }

    public void updateEvent() {
    	getInfo();
    }
    
    public void setEvent(Event event) {
    	this.event = event;
    }
    
    public Event getEvent() {
    	getInfo();
    	return event;
    }
}
