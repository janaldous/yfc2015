package yfcdb.member;

/**
 * Created by janaldoustorres on 25/05/15.
 */
public class SeminarRetreat {
    private String organization, role;

    public SeminarRetreat(String organization, String role) {
        this.organization = organization;
        this.role = role;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] toArray() {
        return new String[] {organization, role};
    }
}
