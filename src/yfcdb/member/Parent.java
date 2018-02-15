package yfcdb.member;

import java.util.List;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class Parent extends Person {
    private String name, occupation, contact, email, relationship;
    private boolean cfc;
    private String sector, chapter;
    private List otherOrganisations;
    private Person person;
    public Parent() {

    }

    public Parent(String relationship, Coordinator coordinator) {
        this.relationship = relationship;
        this.person = coordinator;
    }

    public Parent(String relationship, Coordinator coordinator, String occupation) {
        this.relationship = relationship;
        this.person = coordinator;
        this.name = coordinator.getFullName();
        this.occupation = occupation;
        this.contact = coordinator.getCellphoneNumber();
        this.email = coordinator.getEmail();
        this.cfc = false;
    }

    public Parent(String relationship, String name, String occupation, String contact, String email) {
        this.relationship = relationship;
        this.name = name;
        this.occupation = occupation;
        this.contact = contact;
        this.email = email;
        this.cfc = false;
    }

    public Parent(String relationship, String name, String occupation, String contact, String email, boolean cfc, String sector, String chapter) {
        this.relationship = relationship;
        this.name = name;
        this.occupation = occupation;
        this.contact = contact;
        this.email = email;
        this.cfc = cfc;
        this.sector = sector;
        this.chapter = chapter;
    }

    public void addOrg(String organization) {
        otherOrganisations.add(organization);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmergencyContact toEmergencyContact() {
        return new EmergencyContact(this, name, relationship, contact);
    }

    public String getRelationship() {
        return relationship;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof EmergencyContact && (((EmergencyContact) obj).getParent() != null)
                && ((EmergencyContact) obj).getParent().equals(this)) {
            return true;
        }
        return false;
    }
}
