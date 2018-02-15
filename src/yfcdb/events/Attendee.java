package yfcdb.events;

import yfcdb.member.Person;

/**
 * Created by janaldoustorres on 19/05/15.
 */
@Deprecated
public class Attendee {
    private Person person;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private Role role;

    public Attendee(Person person, Role role) {
        this.person = person;
        this.role = role;
    }



    public String toString() {
        return person.getID() + ":" + person.toString() + " - " + role.toString();
    }
}
