package yfcdb.member;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class Coordinator extends Person {
    private Prefix prefix;
    private ShirtSize shirtSize;

    public Coordinator() { super(); }

    public Coordinator(Prefix prefix, String fn, String mn, String ln, String nn, YFCGroup group) {
        setID(numberOfMembers++);
        this.prefix = prefix;
        position = Position.COORDINATOR;
        firstname = fn;
        middlename = mn;
        lastname = ln;
        nickname = nn;
        this.group = group;
        setUsername();
        setDateUpdated();
    }

    @Override
    public String getShortName() { return prefix + " " +  nickname + " " + lastname; }

    public Parent toParent() {
        String relationship;
        if (prefix == Prefix.TITA) {
            relationship = "Mother";
        } else {
            relationship = "Father";
        }
        return new Parent(relationship, this);
    }

    public Parent toParent(String occupation) {
        String parent;
        if (prefix == Prefix.TITA) {
            parent = "Mother";
        } else {
            parent = "Father";
        }
        return new Parent(parent, this, occupation);
    }

    public EmergencyContact toEmergencyContact() {
        String relationship;
        if (prefix == Prefix.TITA) {
            relationship = "Mother";
        } else {
            relationship = "Father";
        }
        return new EmergencyContact(this.toParent(), getFullName(), relationship, getCellphoneNumber());
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }
}
