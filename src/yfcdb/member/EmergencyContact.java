package yfcdb.member;

import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by janaldoustorres on 19/05/15.
 */
@Entity
@Table(name="emergencycontact")
public class EmergencyContact extends Person {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5710861727852943157L;
    
	@ManyToOne
    @JoinColumn(name="member_id", nullable=false)
	Member member;
	
    @Enumerated(EnumType.STRING)
	Relationship relationship;
    
//    @OneToOne(cascade = {CascadeType.PERSIST})
//	@JoinColumn(name="parent_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Parent parent;
    
    /**
     * Default constructor
     */
    public EmergencyContact() {
    	
    }

//    public EmergencyContact(Parent p) {
//        this.parent = p;
//    }
//    
//    /**
//     * 
//     * @param p
//     * @param fn
//     * @param ln
//     * @param relationship
//     * @param contactNo
//     */
//    public EmergencyContact(Parent p, String fn, String ln, Relationship relationship, String contactNo) {
//        this(fn, ln, relationship, contactNo);
//        this.parent = p;
//    }
//    
    /**
     * 
     * @param fn
     * @param ln
     * @param relationship
     * @param contactNo
     */
    public EmergencyContact(String fn, String ln, Relationship relationship, String contactNo) {
        this.firstname = fn;
        this.lastname = ln;
        this.relationship = relationship;
        this.cellphoneNumber = contactNo;
    }

//    public Relationship getRelationship() {
//        return (relationship == null) ? parent.getRelationship(): relationship;
//    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

//    public Parent getParent() {
//        return parent;
//    }
}
