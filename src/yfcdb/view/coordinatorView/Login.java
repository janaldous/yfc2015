package yfcdb.view.coordinatorView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by janaldoustorres on 21/05/15.
 */
public class Login extends JFrame {
    public class Panel extends JPanel {
        public Panel() {
            JLabel jlUsername = new JLabel("Username");
            JTextField jtfUsername = new JTextField(20);
            JLabel jlPassword = new JLabel("Password");
            JTextField jtfPassword = new JTextField(20);

            add(jlUsername);
            add(jtfUsername);
            add(jlPassword);
            add(jtfPassword);
        }
    }

    public Login() {
        super("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Panel panel = new Panel();
        JButton jbOk = new JButton("Ok");

        add(panel, BorderLayout.CENTER);
        add(jbOk, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String args[]) {
        new Login().setVisible(true);
    }
 }
