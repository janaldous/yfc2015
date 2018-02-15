package yfcdb.view.coordinatorView;

import yfcdb.member.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

/**
 * Created by janaldoustorres on 22/05/15.
 */
public class MemberInfoPanel extends JPanel {
    private ArrayList<FormPanel> panels;
    private Member member;
    private MemberInfoPanelListener memberInfoPanelListener;
    private BriefingPanel briefingPanel;
    private MainWindow mainWindow;

    public class BriefingPanel extends FormPanel {
        private static final int ageRange = 9;
        private JTextField jtfId, jtfDateLastUpdated, jtfAge, jtfYfcAge;
        private JComboBox<Position> jcbPosition;
        private JComboBox<String> jcbEntryYear;

        private BriefingPanel() {
            setLayout(new GridLayout(3, 4));
            setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel jlId = new JLabel("Member ID:");
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
            jcbPosition = new JComboBox<Position>(Position.values());


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

        public void setInfo(Member member) {
            jtfId.setText(String.valueOf(member.getID()));
            jtfDateLastUpdated.setText(member.getDateUpdated());
            jtfAge.setText(String.valueOf(member.getAge()));
            jtfYfcAge.setText(String.valueOf(member.getYfcAge()));
            jcbPosition.setSelectedItem(member.getPosition());
            jcbEntryYear.setSelectedItem(member.getYfcEntryYear());
        }

        @Override
        public Member getInfo(Member member) {
            Position position = (Position) jcbPosition.getSelectedItem();
            int entryYear = Integer.parseInt((String)jcbEntryYear.getSelectedItem());
            member.setPosition(position);
            member.setDateUpdated();
            member.setYfcEntryYear(entryYear);
            return null;
        }

        private void setInfoForNewMember(Member member) {
            jtfId.setText(String.valueOf(member.getID()));
            jtfDateLastUpdated.setText(member.getDateUpdated());
            jcbPosition.setSelectedItem(member.getPosition());
            jtfAge.setText("0");
            jtfYfcAge.setText("0");
        }

        public void setAge(int age) {
            jtfAge.setText(String.valueOf(age));
        }
    }

    public class NamePanel extends FormPanel {
        private JTextField jtfFirstName, jtfMiddleName, jtfLastName, jtfNickname;

        private NamePanel() {
            //names
            setLayout(new GridLayout(1,8, 0, 0));
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

        public void setInfo(Member member) {
            jtfFirstName.setText(member.getFirstName());
            jtfMiddleName.setText(member.getMiddlename());
            jtfLastName.setText(member.getLastname());
            jtfNickname.setText(member.getNickname());
        }

        @Override
        public Member getInfo(Member member) {
            String firstName = jtfFirstName.getText();
            String middleName = jtfMiddleName.getText();
            String lastName = jtfLastName.getText();
            String nickname = jtfNickname.getText();
            member.setFirstName(firstName);
            member.setMiddlename(middleName);
            member.setLastname(lastName);
            member.setNickname(nickname);
            return member;
        }
    }
    
    public class PersonalPanel extends FormPanel {
        private final String[] yesNoList = new String[] {"N", "Y"};
        private final String[] genderList = new String[] {"M", "F"};
        private DatePanel dpBirthday;
        private JComboBox jcbGender, jcbKfcTransfer, jcbBloodType, jcbShirtSize;

        private PersonalPanel() {
            setBorder(BorderFactory.createTitledBorder("Personal"));
            setLayout(new GridLayout(2,1));
            JLabel jlBirthday = new JLabel("Birthday *:");
            dpBirthday = new DatePanel(briefingPanel);

            JLabel jlGender = new JLabel("Gender *:");
            jcbGender = new JComboBox<String>(genderList);

            JLabel jlKfcTransfer = new JLabel("KFC to YFC:");
            jcbKfcTransfer = new JComboBox<String>(yesNoList);

            JLabel jlBloodType = new JLabel("Blood Type:");
            jcbBloodType = new JComboBox<BloodType>(BloodType.values());

            JLabel jlShirtSize = new JLabel("Shirt Size:");
            jcbShirtSize = new JComboBox<ShirtSize>(ShirtSize.values());
            jcbShirtSize.setSelectedItem(ShirtSize.M);

            //first row
            JPanel jpFirstRow = new JPanel();
            jpFirstRow.add(jlBirthday);
            jpFirstRow.add(dpBirthday);
            jpFirstRow.add(jlGender);
            jpFirstRow.add(jcbGender);

            //second row
            JPanel jpSecondRow = new JPanel();
            jpSecondRow.add(jlKfcTransfer);
            jpSecondRow.add(jcbKfcTransfer);
            jpSecondRow.add(jlBloodType);
            jpSecondRow.add(jcbBloodType);
            jpSecondRow.add(jlShirtSize);
            jpSecondRow.add(jcbShirtSize);

            add(jpFirstRow);
            add(jpSecondRow);

            addToComponentList(dpBirthday);
            addToComponentList(jcbGender);
            addToComponentList(jcbKfcTransfer);
            addToComponentList(jcbBloodType);
            addToComponentList(jcbShirtSize);
        }

        public void setInfo(Member member) {
            dpBirthday.setDate(member.getBirthday());
            jcbGender.setSelectedItem(member.getGender());
            jcbKfcTransfer.setSelectedItem(member.isKfcTransfer());
            jcbBloodType.setSelectedItem(member.getBloodType());
            jcbShirtSize.setSelectedItem(member.getShirtSize());
        }

        @Override
        public Member getInfo(Member member) {
            Date birthday = dpBirthday.getDate();
            String gender = jcbGender.getSelectedItem().toString();
            String transferee = jcbKfcTransfer.getSelectedItem().toString();
            boolean kfcTransfer = (transferee.equals("Y")) ? true : false;
            BloodType bloodType = (BloodType) jcbBloodType.getSelectedItem();
            ShirtSize shirtSize = (ShirtSize) jcbShirtSize.getSelectedItem();
            member.setBirthday(birthday);
            member.setGender(gender);
            member.setKfcToYfc(kfcTransfer);
            member.setBloodType(bloodType);
            member.setShirtSize(shirtSize);
            return null;
        }
    }

    public class ContactPanel extends FormPanel {
        private String[] villages = {"SRV1", "SRV2", "SRV3", "SRV4", "SRE1", "SRE2", "SJV1", "SJV2", "SJV3",
                "La Residencia", "Inchikan"};
        private String[] cities = {"Sta Rosa", "Biñan"};
        private JTextField jtfAddressStreet, jtfPostCode, jtfContact, jtfEmail;
        private JComboBox<String> jcbAddressVillage, jcbAddressCity;

        private ContactPanel() {
            setLayout(new GridLayout(6, 1));
            setBorder(BorderFactory.createTitledBorder("Contact"));
            JLabel jlAddressStreet = new JLabel("Address (Street) *:");
            jtfAddressStreet = new JTextField();

            JLabel jlAddressVillage = new JLabel("Address (Village) *:");
            jcbAddressVillage = new JComboBox<String>(villages);
            jcbAddressVillage.setEditable(true);

            JLabel jlAddressCity = new JLabel("Address (City)");
            jcbAddressCity = new JComboBox<String>(cities);
            jcbAddressCity.setEditable(true);

            JLabel jlPostCode = new JLabel("Post code");
            jtfPostCode = new JTextField("4026");

            JLabel jlContact = new JLabel("Contact No *:");
            jtfContact = new JTextField();

            JLabel jlEmail = new JLabel("Email:");
            jtfEmail = new JTextField();

            add(jlAddressStreet);
            add(jtfAddressStreet);
            add(jlAddressVillage);
            add(jcbAddressVillage);
            add(jlAddressCity);
            add(jcbAddressCity);
            add(jlPostCode);
            add(jtfPostCode);
            add(jlContact);
            add(jtfContact);
            add(jlEmail);
            add(jtfEmail);
        }

        public void setInfo(Member member) {
            Address address = member.getAddress();
            jtfAddressStreet.setText(address.getStreet());
            jcbAddressVillage.setSelectedItem(address.getVillage());
            jcbAddressCity.setSelectedItem(address.getCity());
            jtfPostCode.setText(address.getPostalCode());
            jtfContact.setText(member.getCellphoneNumber());
            jtfEmail.setText(member.getEmail());
        }

        @Override
        public Member getInfo(Member member) {
            String street = jtfAddressStreet.getText();
            String village = (String)jcbAddressVillage.getSelectedItem();
            String city = (String)jcbAddressCity.getSelectedItem();
            String postalCode = jtfPostCode.getText();
            String contact = jtfContact.getText();
            String email = jtfEmail.getText();
            Address address = new Address(street, city, village, postalCode);
            member.setAddress(address);
            member.setCellphoneNumber(contact);
            member.setEmail(email);
            return member;
        }
    }

    public class SchoolPanel extends FormPanel {
        private final String[] gradeList = {"-","G6","G7","G8","G9","G10","G11","G12","HS Y1","HS Y2","HS Y3",
                "HS Y4","C Y1","C Y2","C Y3","C Y4","C Y5","Graduated","Wala"};
        private JTextField jtfSchool, jtfCollegeCourse;
        private JComboBox jcbGradeLevel;

        private SchoolPanel() {
            setLayout(new GridLayout(6, 2));
            setBorder(BorderFactory.createTitledBorder("Education"));
            JLabel jlSchool = new JLabel("School:");
            jtfSchool = new JTextField();

            JLabel jlGradeLevel = new JLabel("Grade Level");
            jcbGradeLevel = new JComboBox<String>(gradeList);

            JLabel jlCollegeCourse = new JLabel("College Course:");
            jtfCollegeCourse = new JTextField();

            add(jlSchool);
            add(jtfSchool);
            add(jlGradeLevel);
            add(jcbGradeLevel);
            add(jlCollegeCourse);
            add(jtfCollegeCourse);
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
        }

        public void setInfo(Member member) {
            Education education = member.getEducation();
            jtfSchool.setText(education.getSchool());
            jcbGradeLevel.setSelectedItem(education.getLevel());
            jtfCollegeCourse.setText(education.getCourse());
        }

        @Override
        public Member getInfo(Member member) {
            String school = jtfSchool.getText();
            String level = jcbGradeLevel.getSelectedItem().toString();
            String course = jtfCollegeCourse.getText();
            Education education = new Education(school, level, course);
            member.setEducation(education);
            return member;
        }
    }

    public class ParentPanel extends FormPanel {
        private JTextField jtfFatherName, jtfFatherContact, jtfFatherEmail, jtfFatherOccupation;
        private JTextField jtfMotherName, jtfMotherContact, jtfMotherEmail, jtfMotherOccupation;

        private ParentPanel() {
            setLayout(new GridLayout(1, 2));
            setBorder(BorderFactory.createTitledBorder("Parents"));
            JPanel jpFather = new JPanel(new GridLayout(4,2));
            JLabel jlFatherName = new JLabel("Father Name:");
            jtfFatherName = new JTextField();

            JLabel jlFatherContact = new JLabel("Father Contact No:");
            jtfFatherContact = new JTextField();

            JLabel jlFatherEmail = new JLabel("Father Email:");
            jtfFatherEmail = new JTextField();

            JLabel jlFatherOccupation = new JLabel("Father Occupation:");
            jtfFatherOccupation = new JTextField();

            jpFather.add(jlFatherName);
            jpFather.add(jtfFatherName);
            jpFather.add(jlFatherContact);
            jpFather.add(jtfFatherContact);
            jpFather.add(jlFatherEmail);
            jpFather.add(jtfFatherEmail);
            jpFather.add(jlFatherOccupation);
            jpFather.add(jtfFatherOccupation);

            JPanel jpMother = new JPanel(new GridLayout(4,2));
            JLabel jlMotherName = new JLabel("Mother Name:");
            jtfMotherName = new JTextField();

            JLabel jlMotherContact = new JLabel("Mother Contact No:");
            jtfMotherContact = new JTextField();

            JLabel jlMotherEmail = new JLabel("Mother Email:");
            jtfMotherEmail = new JTextField();

            JLabel jlMotherOccupation = new JLabel("Mother Occupation:");
            jtfMotherOccupation = new JTextField();

            jpMother.add(jlMotherName);
            jpMother.add(jtfMotherName);
            jpMother.add(jlMotherContact);
            jpMother.add(jtfMotherContact);
            jpMother.add(jlMotherEmail);
            jpMother.add(jtfMotherEmail);
            jpMother.add(jlMotherOccupation);
            jpMother.add(jtfMotherOccupation);

            add(jpFather);
            add(jpMother);
        }

        public void setInfo(Member member) {
            Parent father = member.getFather();
            Parent mother = member.getMother();
            if (!(father.getPerson() instanceof Coordinator)) {
                jtfFatherName.setEditable(false);
                jtfFatherContact.setEditable(false);
                jtfFatherEmail.setEditable(false);
                jtfFatherOccupation.setEditable(false);
            }
            if (!(mother.getPerson() instanceof Coordinator)) {
                jtfMotherName.setEditable(false);
                jtfMotherEmail.setEditable(false);
                jtfMotherContact.setEditable(false);
                jtfMotherOccupation.setEditable(false);
            }
            jtfFatherName.setText(father.getName());
            jtfFatherContact.setText(father.getContact());
            jtfFatherEmail.setText(father.getEmail());
            jtfFatherOccupation.setText(father.getOccupation());
            jtfMotherName.setText(mother.getName());
            jtfMotherContact.setText(mother.getContact());
            jtfMotherEmail.setText(mother.getEmail());
            jtfMotherOccupation.setText(mother.getOccupation());
        }

        @Override
        public Member getInfo(Member member) {
            String fName = jtfFatherName.getText();
            String fContact = jtfFatherContact.getText();
            String fEmail = jtfFatherEmail.getText();
            String fOccupation = jtfFatherOccupation.getText();
            String mName = jtfMotherName.getText();
            String mContact = jtfMotherContact.getText();
            String mEmail = jtfMotherEmail.getText();
            String mOccupation = jtfMotherOccupation.getText();

            if (member.getFather() != null && !(member.getFather().getPerson() instanceof Coordinator)) {
                Parent father = new Parent("Father", fName, fOccupation, fContact, fEmail);
                member.setFather(father);
            }
            if (member.getMother() != null && !(member.getMother().getPerson() instanceof Coordinator)) {
                Parent mother = new Parent("Mother", mName, mOccupation, mContact, mEmail);
                member.setMother(mother);
            }
            return null;
        }
    }

    public class EmergencyContactPanel extends FormPanel {
        private JCheckBox jchbFather, jchbMother, jchbOther;
        private JTextField jtfName, jtfRelation, jtfContact;
        private OtherEmergencyContactPanel otherEmergencyContactPanel;
        private class OtherEmergencyContactPanel extends JPanel {
            private OtherEmergencyContactPanel() {
                JLabel jlName = new JLabel("Name:");
                jtfName = new JTextField(15);
                JLabel jlRelation = new JLabel("Relationship:");
                jtfRelation = new JTextField(10);
                JLabel jlContact = new JLabel("Contact:");
                jtfContact = new JTextField(15);

                add(jlName);
                add(jtfName);
                add(jlRelation);
                add(jtfRelation);
                add(jlContact);
                add(jtfContact);
            }

            private void setInfo(EmergencyContact ec) {
                jtfName.setText(ec.getName());
                jtfRelation.setText(ec.getRelationship());
                jtfContact.setText(ec.getContactNo());
            }

            private EmergencyContact getEmergencyContact() {
                String name = jtfName.getText();
                String relationship = jtfRelation.getText();
                String contact = jtfContact.getText();
                EmergencyContact emergencyContact = new EmergencyContact(name, relationship, contact);
                return emergencyContact;
            }
        }

        private EmergencyContactPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder("Emergency Contact"));

            JLabel jlEmergencyPrompt = new JLabel("Choose emergency contacts:");
            jchbFather = new JCheckBox("Father");
            jchbMother = new JCheckBox("Mother");
            jchbOther = new JCheckBox("Other");
            jchbOther.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (((JCheckBox)e.getItem()).isSelected()) {
                        otherEmergencyContactPanel = new OtherEmergencyContactPanel();
                        add(otherEmergencyContactPanel, BorderLayout.SOUTH);
                        revalidate();
                        repaint();
                    } else {
                        remove(otherEmergencyContactPanel);
                        revalidate();
                        repaint();
                    }
                }
            });

            JPanel jpChoices = new JPanel();
            jpChoices.add(jlEmergencyPrompt);
            jpChoices.add(jchbFather);
            jpChoices.add(jchbMother);
            jpChoices.add(jchbOther);

            add(jpChoices, BorderLayout.CENTER);
        }

        public void setInfo(Member member) {
            for (EmergencyContact ec: member.getEmergencyContactList()) {
                if (ec.equals(member.getMother())) {
                    jchbMother.setSelected(true);
                } else if (ec.equals(member.getFather())) {
                    jchbFather.setSelected(true);
                } else {
                    jchbOther.setSelected(true);
                    otherEmergencyContactPanel.setInfo(ec);
                }
            }
        }

        @Override
        public Member getInfo(Member member) {
            ArrayList<EmergencyContact> ecList = member.getEmergencyContactList();
            if (jchbFather.isSelected() && !ecList.contains(member.getFather())) {
                ecList.add(member.getFather().toEmergencyContact());
            } else if (jchbMother.isSelected() && !ecList.contains(member.getMother())) {
                ecList.add(member.getMother().toEmergencyContact());
            } else if (jchbOther.isSelected()) {
                if (ecList.get(ecList.size()-1).getParent() != member.getMother() ||
                        ecList.get(ecList.size()-1).getParent() != member.getFather()) {
                    EmergencyContact emergencyContact = otherEmergencyContactPanel.getEmergencyContact();
                    ecList.add(emergencyContact);
                }
            }
            return member;
        }
    }

    public class SeminarsTablePanel extends FormPanel {
        private DefaultTableModel defaultTableModel;

        public SeminarsTablePanel() {
            setLayout(new BorderLayout());
            JLabel jlSeminarPrompt = new JLabel("Seminars/Retreats attended (religious, extracurriculars, etc)");

            String[] columnNames = {"Name of school/organization", "Position/Nature of service"};
            defaultTableModel = new DefaultTableModel(columnNames, 0);

            JTable jtSeminarTable = new JTable(defaultTableModel);

            JScrollPane jsp = new JScrollPane(jtSeminarTable);
            jsp.setPreferredSize(new Dimension(200, jtSeminarTable.getRowHeight()*5));
            add(jlSeminarPrompt, BorderLayout.NORTH);
            add(jsp, BorderLayout.CENTER);
        }

        public void setInfo(Member member) {
            for(SeminarRetreat sr: member.getSeminarRetreatList()) {
                defaultTableModel.addRow(sr.toArray());
            }
        }

        @Override
        public Member getInfo(Member member) {
            ArrayList<SeminarRetreat> srList = new ArrayList<SeminarRetreat>();
            for (int row = 0; row < defaultTableModel.getRowCount(); row++) {
                String org = (String) defaultTableModel.getValueAt(row, 0);
                String role = (String) defaultTableModel.getValueAt(row, 1);
                srList.add(new SeminarRetreat(org, role));
            }
            member.setSeminarRetreatList(srList);
            return member;
        }
    }

    public class OtherPanel extends FormPanel {
        private JTextArea jtaSpecialSkills, jtaIllness;
        private SeminarsTablePanel seminarsTablePanel;

        private OtherPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createTitledBorder("Other"));
            JLabel jlSpecialSkills = new JLabel("Special Skills:");
            jtaSpecialSkills = new JTextArea();
            jtaSpecialSkills.setPreferredSize(new Dimension(200, 100));
            JLabel jlIllness = new JLabel("Illness:");
            jtaIllness = new JTextArea();
            jtaIllness.setPreferredSize(new Dimension(200, 100));

            seminarsTablePanel = new SeminarsTablePanel();

            JPanel jpTop = new JPanel(new GridLayout(2,2,5,5));
            jpTop.add(jlSpecialSkills);
            jpTop.add(jtaSpecialSkills);
            jpTop.add(jlIllness);
            jpTop.add(jtaIllness);

            add(jpTop);
            add(seminarsTablePanel);
        }

        public void setInfo(Member member) {
            jtaSpecialSkills.setText(member.getSpecialSkills());
            jtaIllness.setText(member.getIllness());
            seminarsTablePanel.setInfo(member);
        }

        @Override
        public Member getInfo(Member member) {
            String specialSkills = jtaSpecialSkills.getText();
            String illness = jtaIllness.getText();
            member.setSpecialSkills(specialSkills);
            member.setIllness(illness);
            member = seminarsTablePanel.getInfo(member);
            return member;
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
            memberInfoPanelListener.addPanels(panels);
            jbSave.addActionListener(memberInfoPanelListener);

            add(jbDelete);
            add(jbSave);
        }
    }

    public MemberInfoPanel(MainWindow mainWindow) {
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
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2;
        add(namePanel, c);

        PersonalPanel personalPanel = new PersonalPanel();
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        add(personalPanel, c);

        ContactPanel contactPanel = new ContactPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 3; c.gridwidth = 1;
        add(contactPanel, c);

        SchoolPanel schoolPanel = new SchoolPanel();
        c.gridx = 1; c.gridy = 3; c.gridwidth = 1;
        add(schoolPanel, c);

        ParentPanel parentPanel = new ParentPanel();
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        add(parentPanel, c);

        EmergencyContactPanel emergencyContactPanel = new EmergencyContactPanel();
        c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
        add(emergencyContactPanel, c);

        OtherPanel otherPanel = new OtherPanel();
        c.gridx = 0; c.gridy = 6; c.gridwidth = 2;
        add(otherPanel, c);

        panels = new ArrayList<FormPanel>();
        panels.add(briefingPanel);
        panels.add(namePanel);
        panels.add(personalPanel);
        panels.add(contactPanel);
        panels.add(schoolPanel);
        panels.add(parentPanel);
        panels.add(emergencyContactPanel);
        panels.add(otherPanel);

        memberInfoPanelListener = new MemberInfoPanelListener(this);

        BottomPanel bottomPanel = new BottomPanel();
        c.gridx = 0; c.gridy = 7; c.gridwidth = 2;
        add(bottomPanel, c);
    }

    public MemberInfoPanel(MainWindow mainWindow, Member member) {
        this(mainWindow);
        this.member = member;
        if (member.getFirstName() == null) {
            briefingPanel.setInfoForNewMember(member);
        } else {
            setInfo(member);
        }
    }

    public void setInfo(Member member) {
        for (FormPanel panel: panels) {
            panel.setInfo(member);
        }
    }

    public void updateMember() {
        for (FormPanel panel: panels) {
            panel.getInfo(member);
        }
        mainWindow.changeCenterPanelToMember(member);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getMember() { return member; }

    private void deletePerson() {
        PersonList personList = PersonList.getInstance();
        personList.removePerson(member);
        mainWindow.changeCenterPanelToEmpty();
    }
}