package yfcdb.view.coordinatorView;

import yfcdb.member.Member;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by janaldoustorres on 28/05/15.
 */
public abstract class FormPanel extends JPanel {
    private ArrayList<Component> componentsList = new ArrayList<Component>();

    @Override
    public Component add(Component component) {
        if (!(component instanceof JLabel)) {
            componentsList.add(component);
        } else if (component instanceof JPanel) {
            for (Component c: ((JPanel)component).getComponents()) {
                if (!(c instanceof JLabel)) {
                    componentsList.add(component);
                }
            }
        }
        return super.add(component);
    }

    public void addToComponentList(Component component) {
        componentsList.add(component);
    }

    public abstract void setInfo(Member member);

    public boolean isFilledOut() {
        boolean isFilled = true;
        Border redBorder = BorderFactory.createLineBorder(Color.red);
        for(Component component: componentsList) {
            if(component instanceof JTextField) {
                JTextField jtfComponent = ((JTextField) component);
                if (jtfComponent.getText().isEmpty()) {
                    isFilled = false;
                    jtfComponent.setBorder(redBorder);
                }
            } else if (component instanceof JTextArea) {
                JTextArea jtfComponent = ((JTextArea) component);
                if (jtfComponent.getText().isEmpty()) {
                    isFilled = false;
                    jtfComponent.setBorder(redBorder);
                }
            } else if (component instanceof JComboBox) {
                JComboBox jcbComponent = (JComboBox) component;
                if (jcbComponent.getSelectedItem() == null || jcbComponent.getSelectedItem().equals("-")) {
                    isFilled = false;
                    jcbComponent.setBorder(redBorder);
                }
            }
        }
        return isFilled;
    }

    public abstract Member getInfo(Member member);
}
