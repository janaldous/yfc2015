package yfcdb.view.coordinatorView;

import yfcdb.member.Member;
import yfcdb.member.Person;
import yfcdb.member.PersonList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by janaldoustorres on 05/06/15.
 */
public class PersonInfoPanelListener implements ActionListener {
    private ArrayList<PersonFormPanel> panels;
    private PersonInfoPanelTemplate personInfoPanel;

    public PersonInfoPanelListener() {
        panels = new ArrayList<PersonFormPanel>();
    }

    public PersonInfoPanelListener(PersonInfoPanelTemplate personInfoPanel) {
        this.personInfoPanel = personInfoPanel;
        panels = new ArrayList<PersonFormPanel>();
    }

    public void addPanels(ArrayList<PersonFormPanel> panels) {
        this.panels.addAll(panels);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean success = true;
        for (PersonFormPanel panel: panels) {
            if (!panel.isFilledOut()) {
                success = false;
            }
        }
        if (success) {
            JOptionPane.showMessageDialog(null, "Success");
            personInfoPanel.updatePerson();
            PersonList personList = PersonList.getInstance();
            Person person = personInfoPanel.getPerson();
            if (!personList.contains(person)) {
                personList.addPerson(person);
                personList.print();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Not filled out");
        }

    }
}