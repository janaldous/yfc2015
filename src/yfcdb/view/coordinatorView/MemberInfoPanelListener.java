package yfcdb.view.coordinatorView;

import yfcdb.member.Member;
import yfcdb.member.MemberList;
import yfcdb.member.PersonList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by janaldoustorres on 25/05/15.
 */
public class MemberInfoPanelListener implements ActionListener {
    private ArrayList<FormPanel> panels;
    private MemberInfoPanel memberInfoPanel;

    public MemberInfoPanelListener() {
        panels = new ArrayList<FormPanel>();
    }

    public MemberInfoPanelListener(MemberInfoPanel memberInfoPanel) {
        this.memberInfoPanel = memberInfoPanel;
        panels = new ArrayList<FormPanel>();
    }

    public void addPanels(ArrayList<FormPanel> panels) {
        this.panels.addAll(panels);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean success = true;
        for (FormPanel panel: panels) {
            if (!panel.isFilledOut()) {
                success = false;
            }
        }
        if (success) {
            JOptionPane.showMessageDialog(null, "Success");
            memberInfoPanel.updateMember();
            PersonList personList = PersonList.getInstance();
            Member member = memberInfoPanel.getMember();
            if (!personList.contains(member)) {
                personList.addPerson(member);
                personList.print();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Not filled out");
        }

    }
}
