package yfcdb.member;

import java.util.Comparator;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class EmergencyContact {
    private String name, relationship, contactNo;
    private Parent parent;

    public EmergencyContact(Parent p) {
        this.parent = p;
    }

    public EmergencyContact(Parent p, String name, String relationship, String contactNo) {
        this.parent = p;
        this.name = name;
        this.relationship = relationship;
        this.contactNo = contactNo;
    }

    public EmergencyContact(String name, String relationship, String contactNo) {
        this.name = name;
        this.relationship = relationship;
        this.contactNo = contactNo;
        this.parent = null;
    }

    public String getName() {
        return (name == null) ? parent.getFullName(): name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return (relationship == null) ? parent.getRelationship(): relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getContactNo() {
        return (contactNo == null) ? parent.getContact(): contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Parent getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Parent && parent != null && parent.equals(obj)) {
            return true;
        }
        return false;
    }
}
