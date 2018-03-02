package yfcdb.member;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by janaldoustorres on 19/05/15.
 */
@Embeddable
public class Parent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6240157025593537347L;
	
	private String firstname;
	
	private String lastname;
	
	private String cellphoneNumber;
	
	private String email;
	
    private String occupation;
    
    @Enumerated(EnumType.STRING)
    private Relationship relationship;
    
    private boolean cfc;
    
    private String otherOrganizations;
    
    public Parent() {

    }

    public Parent(Relationship relationship, Coordinator coordinator) {
        this.relationship = relationship;
        this.firstname = coordinator.getFirstName();
        this.lastname = coordinator.getLastname();
    }

    public Parent(Relationship relationship, Coordinator coordinator, String occupation) {
        this.relationship = relationship;
        this.firstname = coordinator.getFirstName();
        this.lastname = coordinator.getLastname();
        this.occupation = occupation;
        this.cellphoneNumber = coordinator.getCellphoneNumber();
        this.email = coordinator.getEmail();
        this.setCfc(false);
    }

    public Parent(Relationship relationship, String firstname, String middlename,
    		String lastname, String nickname, String occupation, 
    		String cellphoneNumber, String email) {
        this.relationship = relationship;
        this.firstname = firstname;
        this.lastname = lastname;
        this.occupation = occupation;
        this.cellphoneNumber = cellphoneNumber;
        this.email = email;
        this.setCfc(false);
    }

    public Parent(Relationship relationship, String firstname, String middlename,
    		String lastname, String nickname, String occupation, String cellphoneNumber, 
    		String email, boolean cfc) {
        this.relationship = relationship;
        this.firstname = firstname;
        this.lastname = lastname;
        this.occupation = occupation;
        this.cellphoneNumber = cellphoneNumber;
        this.email = email;
        this.setCfc(cfc);
    }
    
    public Parent(Relationship relationship, String firstname, String lastname, 
    		String occupation, String cellphoneNumber, String email) {
        this.relationship = relationship;
        this.firstname = firstname;
        this.lastname = lastname;
        this.occupation = occupation;
        this.cellphoneNumber = cellphoneNumber;
        this.email = email;
    }

    public void setOrganizations(String organization) {
    	otherOrganizations = organization;
    }
    
    public String getOrganizations() {
    	return otherOrganizations;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Relationship getRelationship() {
        return relationship;
    }

	public boolean isCfc() {
		return cfc;
	}

	public void setCfc(boolean cfc) {
		this.cfc = cfc;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCellphoneNumber() {
		return cellphoneNumber;
	}

	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}
}
