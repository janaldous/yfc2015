package yfcdb.view.coordinatorView;

import yfcdb.files.ExternalResource;
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
            jtfId.setText(String.valueOf(member.getId()));
            jtfDateLastUpdated.setText(member.getDateUpdated().toString());
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
            member.setYfcEntryYear(entryYear);
            return member;
        }

        private void setInfoForNewMember(Member member) {
            jtfId.setText("tba");
            jtfDateLastUpdated.setText("n/a");
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
        private final Gender[] genderList = Gender.values();
        private DatePanel dpBirthday;
        private JComboBox jcbGender, jcbKfcTransfer, jcbBloodType, jcbShirtSize;

        private PersonalPanel() {
            setBorder(BorderFactory.createTitledBorder("Personal"));
            setLayout(new GridLayout(2,1));
            JLabel jlBirthday = new JLabel("Birthday *:");
            dpBirthday = new DatePanel(briefingPanel);

            JLabel jlGender = new JLabel("Gender *:");
            jcbGender = new JComboBox<Gender>(genderList);

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
            Gender gender = (Gender) jcbGender.getSelectedItem();
            String transferee = jcbKfcTransfer.getSelectedItem().toString();
            boolean kfcTransfer = (transferee.equals("Y")) ? true : false;
            BloodType bloodType = (BloodType) jcbBloodType.getSelectedItem();
            ShirtSize shirtSize = (ShirtSize) jcbShirtSize.getSelectedItem();
            member.setBirthday(birthday);
            member.setGender(gender);
            member.setKfcToYfc(kfcTransfer);
            member.setBloodType(bloodType);
            member.setShirtSize(shirtSize);
            return member;
        }
    }

    public class ContactPanel extends FormPanel {
        private final String[] VILLAGES = {"SRV1", "SRV2", "SRV3", "SRV4", "SRE1", "SRE2", "SJV1", "SJV2", "SJV3",
                "La Residencia", "Inchikan"};
        private final String[] CITIES = {"Sta Rosa", "Biñan"};
        private JTextField jtfAddressStreet, jtfPostCode, jtfContact, jtfEmail, jtfAddressProvince;
        private JComboBox<String> jcbAddressVillage, jcbAddressCity;
        private final int NUMBER_OF_FIELDS = 7;
        
        private ContactPanel() {
            setLayout(new GridLayout(NUMBER_OF_FIELDS, 1));
            setBorder(BorderFactory.createTitledBorder("Contact"));
            JLabel jlAddressStreet = new JLabel("Address (Street) *:");
            jtfAddressStreet = new JTextField();

            JLabel jlAddressVillage = new JLabel("Address (Village) *:");
            jcbAddressVillage = new JComboBox<String>(VILLAGES);
            jcbAddressVillage.setEditable(true);

            JLabel jlAddressCity = new JLabel("Address (City)");
            jcbAddressCity = new JComboBox<String>(CITIES);
            jcbAddressCity.setEditable(true);
            
            JLabel jlAddressProvince = new JLabel("Province");
            jtfAddressProvince = new JTextField("Laguna");
            jtfAddressProvince.setEditable(false);

            JLabel jlPostCode = new JLabel("Post code");
            jtfPostCode = new JTextField("4026");
            jtfPostCode.setEditable(false);

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
            add(jlAddressProvince);
            add(jtfAddressProvince);
            add(jlPostCode);
            add(jtfPostCode);
            add(jlContact);
            add(jtfContact);
            add(jlEmail);
            add(jtfEmail);
        }

        public void setInfo(Member member) {
            Address address = member.getAddress();
            if(address != null) {
            	jtfAddressStreet.setText(address.getAddress1());
                jcbAddressVillage.setSelectedItem(address.getAddress2());
                jcbAddressCity.setSelectedItem(address.getCity());
                jtfAddressProvince.setText(address.getProvince());
                jtfPostCode.setText(address.getPostalCode());
                jtfContact.setText(member.getCellphoneNumber());
                jtfEmail.setText(member.getEmail());
            }
        }

        @Override
        public Member getInfo(Member member) {
            String street = jtfAddressStreet.getText();
            String village = (String)jcbAddressVillage.getSelectedItem();
            String city = (String)jcbAddressCity.getSelectedItem();
            String province = jtfAddressProvince.getText();
            String postalCode = jtfPostCode.getText();
            String contact = jtfContact.getText();
            String email = jtfEmail.getText();
            Address address = new Address(street, village, city, province, postalCode);
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
        	if(member.getEducation() != null) {
        		Education education = member.getEducation();
                jtfSchool.setText(education.getSchool());
                jcbGradeLevel.setSelectedItem(education.getLevel());
                jtfCollegeCourse.setText(education.getCourse());
        	}
        }

        @Override
        public Member getInfo(Member member) {
            String school = jtfSchool.getText();
            String level = jcbGradeLevel.getSelectedItem().toString();
            String course = jtfCollegeCourse.getText();
            Education education = new Education();
            education.setSchool(school);
            education.setLevel(level);
            education.setCourse(course);
            member.setEducation(education);
            return member;
        }
    }
    
    public class ParentPanel extends FormPanel {
        FormPanel jpFather, jpMother;
    	
        private ParentPanel() {
            setLayout(new GridLayout(1, 2));
            setBorder(BorderFactory.createTitledBorder("Parents"));
            jpFather = new OneParentPanel(Relationship.FATHER);
            jpMother = new OneParentPanel(Relationship.MOTHER);

            add(jpFather);
            add(jpMother);
        }

        public void setInfo(Member member) {
        	if(member.getFather()!=null) {
        		jpFather.setInfo(member);
        	}
            if(member.getMother()!=null) {
            	jpMother.setInfo(member);
            }
        }

        @Override
        public Member getInfo(Member member) {
			member = jpFather.getInfo(member);
			member = jpMother.getInfo(member);
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

    public MemberInfoPanel(MainWindow mainWindow, ExternalResource externalResource) {
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
        
        FormPanel parentPanel = new ParentPanel();
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        add(parentPanel, c);

        panels = new ArrayList<FormPanel>();
        panels.add(briefingPanel);
        panels.add(namePanel);
        panels.add(personalPanel);
        panels.add(contactPanel);
        panels.add(schoolPanel);
        panels.add(parentPanel);
        
        memberInfoPanelListener = new MemberInfoPanelListener(this, externalResource);

        BottomPanel bottomPanel = new BottomPanel();
        c.gridx = 0; c.gridy = 7; c.gridwidth = 2;
        add(bottomPanel, c);
    }

    public MemberInfoPanel(MainWindow mainWindow, Member member, ExternalResource externalResource) {
        this(mainWindow, externalResource);
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
            member = panel.getInfo(member);
        }
    }
    
    public void changeMainWindow() {
        mainWindow.changeCenterPanelToMember(member);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getMember() {
    	return member; 
	}

    private void deletePerson() {
        PersonList personList = PersonList.getInstance();
        personList.removePerson(member);
        mainWindow.changeCenterPanelToEmpty();
    }
}