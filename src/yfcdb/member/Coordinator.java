package yfcdb.member;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class Coordinator extends Member {
    private Prefix prefix;

    public Coordinator() { super(); }

    public Coordinator(Prefix prefix, String fn, String mn, String ln, String nn, YFCGroup group) {
        //setID(numberOfMembers++);
        this.prefix = prefix;
        //position = Position.COORDINATOR;
        firstname = fn;
        middlename = mn;
        lastname = ln;
        nickname = nn;
        //this.group = group;
    }

    @Override
    public String getShortName() { return prefix + " " +  nickname + " " + lastname; }
    
    public Parent toParent() {
    	Relationship relationship = Prefix.toRelationship(prefix);
        return new Parent(relationship, this);
    }

    public Parent toParent(String occupation) {
    	Relationship relationship = Prefix.toRelationship(prefix);
        return new Parent(relationship, this, occupation);
    }

    public EmergencyContact toEmergencyContact() {
    	Relationship relationship = Prefix.toRelationship(prefix);
        return new EmergencyContact(firstname, lastname, relationship, cellphoneNumber);
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }
}
