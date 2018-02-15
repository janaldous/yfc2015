package yfcdb.view.coordinatorView;

import yfcdb.member.Person;
import yfcdb.member.Position;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Calendar;

/**
 * Created by janaldoustorres on 22/05/15.
 */
@Deprecated
public class PersonalInfoPanel extends JPanel {
    private static final int ageRange = 9;
    private static final String[] gradeList = {"-","G6","G7","G8","G9","G10","G11","G12","HS Y1","HS Y2","HS Y3",
            "HS Y4","C Y1","C Y2","C Y3","C Y4","C Y5","Work","Wala"};
    private static String[] villageList = {"-", "SRV I","SRV II","SRV III", "SJI", "SJII", "SJIII", "SREI",
            "SREII", "BelAir", "La Residencia", "Bgy. Don Jose", "Inchikan", "Other"};
    private static final String[] shirtSizeList = {"-","XXS", "XS", "S", "M", "L", "XL"};

    /**private class FieldItem extends JPanel {
        private JLabel label;
        private Component input;
        private FieldItem(String field) {
            setLayout(new GridLayout(1,2));
            label = new JLabel(field);
            input = new JTextField();
            add(label);
            add(input);
        }

        private FieldItem(String field, Component component) {
            setLayout(new GridLayout(1,2));
            label = new JLabel(field);
            input = component;
            add(label);
            add(input);
        }
    }*/

    public PersonalInfoPanel() {
        //setLayout(new GridLayout(1, 3));
        //first column
        JLabel jlFirstName = new JLabel("First Name *:");
        JTextField jtfFirstName = new JTextField();

        JLabel jlMiddleName = new JLabel("Middle Name:");
        JTextField jtfMiddleName = new JTextField();

        JLabel jlLastName = new JLabel("Last Name *:");
        JTextField jtfLastName = new JTextField();

        JLabel jlNickname = new JLabel("Nick Name *:");
        JTextField jtfNickname = new JTextField();

        JLabel jlPosition = new JLabel("YFC Position:");
        JComboBox jcbPositions = new JComboBox(Position.getPositions());

        String[] yfcAgeList = new String[ageRange];
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < yfcAgeList.length; i++) { yfcAgeList[i] = Integer.toString(currentYear-i); }

        JLabel jlEntryYear = new JLabel("Year joined:");
        JComboBox jcbEntryYear = new JComboBox(yfcAgeList);

        JLabel jlBirthday = new JLabel("Birthday *:");
        DatePanel dpBirthday = new DatePanel();

        JLabel jlHHLeader = new JLabel("HH Leader:");
        JTextField jtfHHLeader = new JTextField();

        JLabel jlSchool = new JLabel("School:");
        JTextField jtfSchool = new JTextField();

        JLabel jlGradeLevel = new JLabel("Grade Level");
        JComboBox jcbGradeLevel = new JComboBox(gradeList);

        JLabel jlCollegeCourse = new JLabel("College Course:");
        JTextField jtfCollegeCourse = new JTextField();

        JPanel jpFirst = new JPanel(new GridLayout(11, 1));
        jpFirst.add(jlFirstName);
        jpFirst.add(jtfFirstName);
        jpFirst.add(jlMiddleName);
        jpFirst.add(jtfMiddleName);
        jpFirst.add(jlLastName);
        jpFirst.add(jtfLastName);
        jpFirst.add(jlNickname);
        jpFirst.add(jtfNickname);
        jpFirst.add(jlPosition);
        jpFirst.add(jcbPositions);
        jpFirst.add(jlEntryYear);
        jpFirst.add(jcbEntryYear);
        jpFirst.add(jlBirthday);
        jpFirst.add(dpBirthday);
        jpFirst.add(jlHHLeader);
        jpFirst.add(jtfHHLeader);
        jpFirst.add(jlSchool);
        jpFirst.add(jtfSchool);
        jpFirst.add(jlGradeLevel);
        jpFirst.add(jcbGradeLevel);
        jpFirst.add(jlCollegeCourse);
        jpFirst.add(jtfCollegeCourse);

        add(jpFirst);

        //second column
        JLabel jlContact = new JLabel("Contact No *:");
        JTextField jtfContact = new JTextField();

        JLabel jlEmail = new JLabel("Email:");
        JTextField jtfEmail = new JTextField();

        JLabel jlAddress1 = new JLabel("Address Ln 1 *:");
        JTextField jtfAddress1 = new JTextField();

        JLabel jlAddress2 = new JLabel("Address (Village) *:");
        JComboBox jcbAddress2 = new JComboBox(villageList);

        JLabel jlFatherName = new JLabel("Father Name:");
        JTextField jtfFatherName = new JTextField();

        JLabel jlFatherContact = new JLabel("Father Contact No:");
        JTextField jtfFatherContact = new JTextField();

        JLabel jlFatherEmail = new JLabel("Father Email:");
        JTextField jtfFatherEmail = new JTextField();

        JLabel jlFatherOccupation = new JLabel("Father Occupation:");
        JTextField jtfFatherOccupation = new JTextField();

        JLabel jlMotherName = new JLabel("Mother Name:");
        JTextField jtfMotherName = new JTextField();

        JLabel jlMotherContact = new JLabel("Mother Contact No:");
        JTextField jtfMotherContact = new JTextField();

        JLabel jlMotherEmail = new JLabel("Mother Email:");
        JTextField jtfMotherEmail = new JTextField();

        JLabel jlMotherOccupation = new JLabel("Mother Occupation:");
        JTextField jtfMotherOccupation = new JTextField();


        JPanel jpSecond = new JPanel(new GridLayout(12, 1));
        jpSecond.add(jlContact);
        jpSecond.add(jtfContact);
        jpSecond.add(jlEmail);
        jpSecond.add(jtfEmail);
        jpSecond.add(jlAddress1);
        jpSecond.add(jtfAddress1);
        jpSecond.add(jlAddress2);
        jpSecond.add(jcbAddress2);
        jpSecond.add(jlFatherName);
        jpSecond.add(jtfFatherName);
        jpSecond.add(jlFatherContact);
        jpSecond.add(jtfFatherContact);
        jpSecond.add(jlFatherEmail);
        jpSecond.add(jtfFatherEmail);
        jpSecond.add(jlFatherOccupation);
        jpSecond.add(jtfFatherOccupation);
        jpSecond.add(jlMotherName);
        jpSecond.add(jtfMotherName);
        jpSecond.add(jlMotherContact);
        jpSecond.add(jtfMotherContact);
        jpSecond.add(jlMotherEmail);
        jpSecond.add(jtfMotherEmail);
        jpSecond.add(jlMotherOccupation);
        jpSecond.add(jtfMotherOccupation);

        add(jpSecond);

        //third column
        JLabel jlId = new JLabel("Member ID:");
        JTextField jtfId = new JTextField();
        jtfId.setEditable(false);

        JLabel jlDateLastUpdated = new JLabel("Date Last Updated:");
        JTextField jtfDateLastUpdated = new JTextField("jo");
        jtfDateLastUpdated.setEditable(false);

        JLabel jlAge = new JLabel("Age:");
        JTextField jtfAge = new JTextField("");
        jtfAge.setEditable(false);

        JLabel jlYfcAge = new JLabel("Years in YFC:");
        JTextField jtfYfcAge = new JTextField("");
        jtfYfcAge.setEditable(false);

        JLabel jlGender = new JLabel("Gender *:");
        JComboBox jcbGender = new JComboBox(new String[] {"M", "F"});

        JLabel jlKfcTransfer = new JLabel("KFC to YFC:");
        JComboBox jcbKfcTransfer = new JComboBox(new String[] {"Y", "N"});

        JLabel jlShirtSize = new JLabel("Shirt Size:");
        JComboBox jcbShirtSize = new JComboBox(shirtSizeList);

        JLabel jlAllergies = new JLabel("Allergies:");
        JTextField jtfAllergies = new JTextField();

        JPanel jpThird = new JPanel(new GridLayout(8, 2));
        jpThird.add(jlId);
        jpThird.add(jtfId);
        jpThird.add(jlDateLastUpdated);
        jpThird.add(jtfDateLastUpdated);
        jpThird.add(jlAge);
        jpThird.add(jtfAge);
        jpThird.add(jlYfcAge);
        jpThird.add(jtfYfcAge);
        jpThird.add(jlGender);
        jpThird.add(jcbGender);
        jpThird.add(jlKfcTransfer);
        jpThird.add(jcbKfcTransfer);
        jpThird.add(jlShirtSize);
        jpThird.add(jcbShirtSize);
        jpThird.add(jlAllergies);
        jpThird.add(jtfAllergies);

        add(jpThird);
    }
}
