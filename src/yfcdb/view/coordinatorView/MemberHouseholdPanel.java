package yfcdb.view.coordinatorView;

import yfcdb.member.Member;

import javax.swing.*;
import java.awt.*;

/**
 * Created by janaldoustorres on 22/05/15.
 */
public class MemberHouseholdPanel extends JPanel {
    private Member member;
    private DefaultListModel defaultListModel;

    private class HHLeaderPanel extends JPanel {
        private HHLeaderPanel() {
            super(new GridLayout(2, 2));

            JLabel jlHHLeader = new JLabel("HH Leader:");
            JTextField jtfHHLeader = new JTextField();
            jtfHHLeader.setEditable(false);

            JLabel jlHHContact = new JLabel("HH Contact:");
            JTextField jtfContact = new JTextField();
            jtfContact.setEditable(false);

            add(jlHHLeader);
            add(jtfHHLeader);
            add(jlHHContact);
            add(jtfContact);
        }
    }
    
    public MemberHouseholdPanel(Member member) {
        super(new FlowLayout(FlowLayout.CENTER, 10, 5));
        this.member = member;
        defaultListModel = new DefaultListModel();
        populateList();

        JList jlHHMembers = new JList(defaultListModel);
        jlHHMembers.setPreferredSize(new Dimension(200, 500));
        HHLeaderPanel hhlp = new HHLeaderPanel();

        add(jlHHMembers);
        add(hhlp);
    }

    private void populateList() {
        defaultListModel.addElement("jat");
        defaultListModel.addElement("chesl");
    }
}
