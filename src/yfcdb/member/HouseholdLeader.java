package yfcdb.member;

import yfcdb.household.Household;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class HouseholdLeader extends Person {
    private Household householdGroup;

    public HouseholdLeader(String firstname, String middlename, String lastname, String nickname, YFCGroup group) {
        super(Position.HOUSEHOLD_HEAD, firstname, middlename, lastname, nickname, group);
    }

    public Household getHouseholdGroup() {
        return householdGroup;
    }

    public void setHouseholdGroup(Household householdGroup) {
        this.householdGroup = householdGroup;
    }
}
