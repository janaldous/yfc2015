package yfcdb.member;

import yfcdb.household.Household;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class HouseholdLeader extends Member {
    private Household householdGroup;

    public HouseholdLeader(String firstname, String middlename, String lastname, String nickname, 
    		String contactNo) {
        super(firstname, middlename, lastname, nickname, contactNo);
        position = Position.HOUSEHOLD_HEAD;
    }

    public Household getHouseholdGroup() {
        return householdGroup;
    }

    public void setHouseholdGroup(Household householdGroup) {
        this.householdGroup = householdGroup;
    }
}
