package yfcdb.view.coordinatorView;

import yfcdb.member.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by janaldoustorres on 05/06/15.
 */
public class CoordinatorInfoPanel extends JPanel implements PersonInfoPanelTemplate {
    private ArrayList<PersonFormPanel> panels;
    private Coordinator coordinator;
    private PersonInfoPanelListener personInfoPanelListener;
    private BriefingPanel briefingPanel;
    private MainWindow mainWindow;

    public class BriefingPanel extends PersonFormPanel<Coordinator> {
        private static final int ageRange = 9;
        private JTextField jtfId, jtfDateLastUpdated, jtfAge, jtfYfcAge;
        private JComboBox<Position> jcbPosition;
        private JComboBox<String> jcbEntryYear;

        private BriefingPanel() {
            setLayout(new GridLayout(3, 4));
            setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel jlId = new JLabel("Coordinator ID:");
            jtfId = new JTextField();
            jtfId.setEditable(false);

            JLabel jlDateLastUpdated = new JLabel("Date Last Updated:");
            jtfDateLastUpdated = new JTextField();
            jtfDateLastUpdated.setEditable(false);

            JLabel jlAge = new JLabel("Age:");
            jtfAge = new JTextField();
            jtfAge.setEditable(false);

            JLabel jlYfcAge = new JLabel("Years in YFC:");
            jtfYfcAge = new JTextField();
            jtfYfcAge.setEditable(false);

            JLabel jlPosition = new JLabel("YFC Position:");
            jcbPosition = new JComboBox<Position>(new Position[] {Position.COORDINATOR});

            String[] yfcAgeList = new String[ageRange];
            final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = 0; i < yfcAgeList.length; i++) { yfcAgeList[i] = Integer.toString(currentYear-i); }
            JLabel jlEntryYear = new JLabel("Year joined:");
            jcbEntryYear = new JComboBox<String>(yfcAgeList);
            jcbEntryYear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int age = currentYear-Integer.parseInt((String) jcbEntryYear.getSelectedItem());
                    jtfYfcAge.setText(Integer.toString(age));
                }
            });

            add(jlId);
            add(jtfId);
            add(jlDateLastUpdated);
            add(jtfDateLastUpdated);
            add(jlAge);
            add(jtfAge);
            add(jlYfcAge);
            add(jtfYfcAge);
            add(jlPosition);
            add(jcbPosition);
            add(jlEntryYear);
            add(jcbEntryYear);
        }

        public void setInfo(Coordinator coordinator) {
            jtfId.setText(String.valueOf(coordinator.getID()));
            jtfDateLastUpdated.setText(coordinator.getDateUpdated());
            jtfAge.setText(String.valueOf(coordinator.getAge()));
            jtfYfcAge.setText(String.valueOf(coordinator.getYfcAge()));
            jcbPosition.setSelectedItem(coordinator.getPosition());
            jcbEntryYear.setSelectedItem(coordinator.getYfcEntryYear());
        }

        public void getInfo(Coordinator coordinator) {
            Position position = (Position) jcbPosition.getSelectedItem();
            int entryYear = Integer.parseInt((String)jcbEntryYear.getSelectedItem());
            coordinator.setPosition(position);
            coordinator.setDateUpdated();
            coordinator.setYfcEntryYear(entryYear);
        }

        private void setInfoForNewCoordinator(Coordinator coordinator) {
            jtfId.setText(String.valueOf(coordinator.getID()));
            jtfDateLastUpdated.setText(coordinator.getDateUpdated());
            jcbPosition.setSelectedItem(coordinator.getPosition());
            jtfAge.setText("0");
            jtfYfcAge.setText("0");
        }

        public void setAge(int age) {
            jtfAge.setText(String.valueOf(age));
        }
    }

    public class NamePanel extends PersonFormPanel<Coordinator> {
        private JTextField jtfFirstName, jtfMiddleName, jtfLastName, jtfNickname;

        private NamePanel() {
            //names
            setLayout(new GridLayout(1, 8, 0, 0));
            setBorder(BorderFactory.createTitledBorder("Name"));
            JLabel jlFirstName = new JLabel("First Name *:");
            jtfFirstName = new JTextField();

            JLabel jlMiddleName = new JLabel("Middle Name:");
            jtfMiddleName = new JTextField();

            JLabel jlLastName = new JLabel("Last Name *:");
            jtfLastName = new JTextField();

            JLabel jlNickname = new JLabel("Nick Name *:");
            jtfNickname = new JTextField();

            add(jlFirstName);
            add(jtfFirstName);
            add(jlMiddleName);
            add(jtfMiddleName);
            add(jlLastName);
            add(jtfLastName);
            add(jlNickname);
            add(jtfNickname);
        }

        public void setInfo(Coordinator coordinator) {
            jtfFirstName.setText(coordinator.getFirstName());
            jtfMiddleName.setText(coordinator.getMiddlename());
            jtfLastName.setText(coordinator.getLastname());
            jtfNickname.setText(coordinator.getNickname());
        }

        @Override
        public void getInfo(Coordinator coordinator) {
            String firstName = jtfFirstName.getText();
            String middleName = jtfMiddleName.getText();
            String lastName = jtfLastName.getText();
            String nickname = jtfNickname.getText();
            coordinator.setFirstName(firstName);
            coordinator.setMiddlename(middleName);
            coordinator.setLastname(lastName);
            coordinator.setNickname(nickname);
        }
    }

    public class PersonalPanel extends PersonFormPanel<Coordinator> {
        private DatePanel dpBirthday;
        private JComboBox jcbPrefix, jcbShirtSize;

        private PersonalPanel() {
            setBorder(BorderFactory.createTitledBorder("Personal"));
            setLayout(new GridLayout(2,1));

            JPanel jpTop = new JPanel(new GridLayout(1,2));
            JLabel jlBirthday = new JLabel("Birthday *:");
            dpBirthday = new DatePanel(briefingPanel);
            jpTop.add(jlBirthday);
            jpTop.add(dpBirthday);

            JPanel jpBottom = new JPanel(new GridLayout(1, 4));
            JLabel jlPrefix = new JLabel("Prefix *:");
            jcbPrefix = new JComboBox<Prefix>(Prefix.values());
            JLabel jlShirtSize = new JLabel("Shirt Size:");
            jcbShirtSize = new JComboBox<ShirtSize>(ShirtSize.values());
            jcbShirtSize.setSelectedItem(ShirtSize.M);

            jpBottom.add(jlPrefix);
            jpBottom.add(jcbPrefix);
            jpBottom.add(jlShirtSize);
            jpBottom.add(jcbShirtSize);

            add(jpTop);
            add(jpBottom);
        }

        public void setInfo(Coordinator coordinator) {
            dpBirthday.setDate(coordinator.getBirthday());
            jcbPrefix.setSelectedItem(coordinator.getPrefix());
            jcbShirtSize.setSelectedItem(coordinator.getShirtSize());
        }

        public void getInfo(Coordinator coordinator) {
            Date birthday = dpBirthday.getDate();
            Prefix prefix = (Prefix)jcbPrefix.getSelectedItem();
            ShirtSize shirtSize = (ShirtSize) jcbShirtSize.getSelectedItem();
            coordinator.setBirthday(birthday);
            coordinator.setPrefix(prefix);
            coordinator.setShirtSize(shirtSize);
        }
    }

    public class ContactPanel extends PersonFormPanel<Coordinator> {
        private JTextField jtfAddressStreet, jtfAddressVillage, jtfAddressCity, jtfPostCode, jtfContact, jtfEmail;

        private ContactPanel() {
            setLayout(new GridLayout(6, 1));
            setBorder(BorderFactory.createTitledBorder("Contact"));
            JLabel jlAddressStreet = new JLabel("Address (Street) *:");
            jtfAddressStreet = new JTextField();

            JLabel jlAddressVillage = new JLabel("Address (Village) *:");
            jtfAddressVillage = new JTextField();

            JLabel jlAddressCity = new JLabel("Address (City)");
            jtfAddressCity = new JTextField();

            JLabel jlPostCode = new JLabel("Post code");
            jtfPostCode = new JTextField("4026");

            JLabel jlContact = new JLabel("Contact No *:");
            jtfContact = new JTextField();

            JLabel jlEmail = new JLabel("Email:");
            jtfEmail = new JTextField();

            add(jlAddressStreet);
            add(jtfAddressStreet);
            add(jlAddressVillage);
            add(jtfAddressVillage);
            add(jlAddressCity);
            add(jtfAddressCity);
            add(jlPostCode);
            add(jtfPostCode);
            add(jlContact);
            add(jtfContact);
            add(jlEmail);
            add(jtfEmail);
        }

        public void setInfo(Coordinator coordinator) {
            Address address = coordinator.getAddress();
            jtfAddressStreet.setText(address.getStreet());
            jtfAddressVillage.setText(address.getVillage());
            jtfAddressCity.setText(address.getCity());
            jtfPostCode.setText(address.getPostalCode());
            jtfContact.setText(coordinator.getCellphoneNumber());
            jtfEmail.setText(coordinator.getEmail());
        }

        public void getInfo(Coordinator coordinator) {
            String street = jtfAddressStreet.getText();
            String village = jtfAddressVillage.getText();
            String city = jtfAddressCity.getText();
            String postalCode = jtfPostCode.getText();
            String contact = jtfContact.getText();
            String email = jtfEmail.getText();
            Address address = new Address(street, city, village, postalCode);
            coordinator.setAddress(address);
            coordinator.setCellphoneNumber(contact);
            coordinator.setEmail(email);
        }
    }

    private class BottomPanel extends JPanel {
        private BottomPanel() {
            setLayout(new FlowLayout(FlowLayout.RIGHT));
            JButton jbDelete = new JButton("Delete");
            jbDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //deletePerson();
                }
            });
            JButton jbSave = new JButton("Save");
            personInfoPanelListener.addPanels(panels);
            jbSave.addActionListener(personInfoPanelListener);

            add(jbDelete);
            add(jbSave);
        }
    }

    public CoordinatorInfoPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20) );

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5; c.weighty = 0.5;

        briefingPanel = new BriefingPanel();
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        add(briefingPanel, c);

        NamePanel namePanel = new NamePanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 1; c.gridwidth = 1;
        add(namePanel, c);

        PersonalPanel personalPanel = new PersonalPanel();
        c.gridx = 0; c.gridy = 2; c.gridwidth = 1;
        add(personalPanel, c);

        ContactPanel contactPanel = new ContactPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 3; c.gridwidth = 1;
        add(contactPanel, c);

        panels = new ArrayList<PersonFormPanel>();
        panels.add(briefingPanel);
        panels.add(namePanel);
        panels.add(personalPanel);
        panels.add(contactPanel);

        personInfoPanelListener = new PersonInfoPanelListener(this);

        BottomPanel bottomPanel = new BottomPanel();
        c.gridx = 0; c.gridy = 4; c.gridwidth = 1;
        add(bottomPanel, c);
    }

    public CoordinatorInfoPanel(MainWindow mainWindow, Coordinator coordinator) {
        this(mainWindow);
        this.coordinator = coordinator;
        if (coordinator.getFirstName() == null) {
            briefingPanel.setInfoForNewCoordinator(coordinator);
        } else {
            setInfo(coordinator);
        }
    }

    public void setInfo(Coordinator coordinator) {
        for (PersonFormPanel panel: panels) {
            panel.setInfo(coordinator);
        }
    }

    @Override
    public Person getPerson() {
        return coordinator;
    }

    @Override
    public void updatePerson() {
        for (PersonFormPanel panel: panels) {
            panel.getInfo(coordinator);
        }
        mainWindow.changeCenterPanelToCoordinator(coordinator);
    }

    @Override
    public void setPerson(Person person) {
        this.coordinator = (Coordinator)person;
    }

    private void deletePerson() {
        PersonList personList = PersonList.getInstance();
        personList.removePerson(coordinator);
        mainWindow.changeCenterPanelToEmpty();
    }
}