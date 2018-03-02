package yfcdb.view.coordinatorView;

import yfcdb.files.ExternalResource;
import yfcdb.member.Member;
import yfcdb.member.PersonList;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janaldoustorres on 25/05/15.
 */
public class MemberInfoPanelListener implements ActionListener {
    private List<FormPanel> panels;
    private MemberInfoPanel memberInfoPanel;
    private ExternalResource externalResource;

    public MemberInfoPanelListener() {
        panels = new ArrayList<FormPanel>();
    }
    
    public MemberInfoPanelListener(MemberInfoPanel memberInfoPanel, ExternalResource externalResource) {
    	this();
        this.memberInfoPanel = memberInfoPanel;
    	this.externalResource = externalResource;
    }

    public void addPanels(List<FormPanel> panels) {
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
            memberInfoPanel.updateMember();
        	Member member = memberInfoPanel.getMember();
            externalResource.updateOrSaveMember(member);
            memberInfoPanel.changeMainWindow();
            
            PersonList personList = PersonList.getInstance();

            if (!personList.contains(member)) {
                personList.addPerson(member);
                personList.print();
            }
            
            JOptionPane.showMessageDialog(null, "Success");
        } else {
            JOptionPane.showMessageDialog(null, "Not filled out");
        }

    }
}
