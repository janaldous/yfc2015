package yfcdb.household;

import yfcdb.member.HouseholdLeader;
import yfcdb.member.Person;

import java.util.ArrayList;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class Household {
    private static int groupCtr = 0;
    private int groupNumber = 0;
    private HouseholdLeader hhLeader;
    private ArrayList<Person> memberList = new ArrayList<Person>();

    public Household(HouseholdLeader hhl)
    {
        groupNumber = ++groupCtr;
        hhLeader = hhl;
        //HouseholdList.addHousehold(this);
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setLeader(HouseholdLeader hhl)
    {
        hhLeader = hhl;
    }

    public void addMember(Person m)
    {
        memberList.add(m);
    }

    public void removeMember(Person m)
    {
        memberList.remove(m);
    }

    public String getMembers()
    {
        String stringList = "\n";
        for(Person item: memberList){
            stringList += item + "\n";
        }
        return stringList;
    }

    public String getContactInfo()
    {
        String stringList = "";
        for(Person person: memberList){
            stringList += person + ":" + person.getCellphoneNumber() + "\n";
        }
        return stringList;
    }

    public String toString() {
        String message = "";
        message += "===================";
        message += "\nHousehold";
        message += "\nGroup number: " + groupNumber
                + "\nLeader: " + hhLeader
                + "\nMembers:";
        message += getMembers();
        message += "===================";
        return message;
    }
}
