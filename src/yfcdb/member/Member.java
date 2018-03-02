package yfcdb.member;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Creates a member.
 * 
 * @author Jat Torres
 * @version 22.02.2018
 */
@Entity
@Table(name="member")
public class Member extends Person {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7751742315937853289L;
	
	@Column(name="position")
	@Enumerated(EnumType.STRING)
	protected Position position;
	
	@Column(name="yfc_entry_year")
	protected int yfcEntryYear;
	
	@Temporal(TemporalType.DATE)
	@Column(name="birthday")
	protected Date birthday;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	protected YFCGroup group;
	
	@Column(name="shirt_size")
	@Enumerated(EnumType.ORDINAL)
	protected ShirtSize shirtSize;
	
	@Column(name="kfc_to_yfc")
    private boolean kfcToYfc;
	
	@Column(name="blood_type")
	@Enumerated(EnumType.ORDINAL)
    private BloodType bloodType;
	
	protected Address address;
	
    private Education education;
    
    @Embedded
    @AttributeOverrides( {
        @AttributeOverride(name="firstname", column = @Column(name="m_firstname") ),
        @AttributeOverride(name="lastname", column = @Column(name="m_lastname") ),
        @AttributeOverride(name="cellphoneNumber", column = @Column(name="m_cellphoneNumber") ),
        @AttributeOverride(name="email", column = @Column(name="m_email") ),
        @AttributeOverride(name="occupation", column = @Column(name="m_occupation") ),
        @AttributeOverride(name="relationship", column = @Column(name="m_relationship") ),
        @AttributeOverride(name="cfc", column = @Column(name="m_cfc") ),
        @AttributeOverride(name="otherOrganizations", column = @Column(name="m_otherOrganizations") )
    } )
    private Parent mother;
    
    @Embedded
    @AttributeOverrides( {
        @AttributeOverride(name="firstname", column = @Column(name="f_firstname") ),
        @AttributeOverride(name="lastname", column = @Column(name="f_lastname") ),
        @AttributeOverride(name="cellphoneNumber", column = @Column(name="f_cellphoneNumber") ),
        @AttributeOverride(name="email", column = @Column(name="f_email") ),
        @AttributeOverride(name="occupation", column = @Column(name="f_occupation") ),
        @AttributeOverride(name="relationship", column = @Column(name="f_relationship") ),
        @AttributeOverride(name="cfc", column = @Column(name="f_cfc") ),
        @AttributeOverride(name="otherOrganizations", column = @Column(name="f_otherOrganizations") )
    } )
    private Parent father;
    
	@OneToMany(mappedBy="member")
    private List<EmergencyContact> emergencyContactList;
    
	@Column(name="special_skills")
    private String specialSkills;
    
	@Column(name="illnesses")
    private String illnesses;
    
	//@Column(name="seminar_retreat_list")
    //private ArrayList<SeminarRetreat> seminarRetreatList;
		
    public Member() {
        super();
        position = Position.MEMBER;
        emergencyContactList = new ArrayList<EmergencyContact>();

        education = new Education();
        
        /*seminarRetreatList = new ArrayList<SeminarRetreat>();*/
    }
    
    /**
     * Creates a member.
     * @param pos Position
     * @param fn First name
     * @param mn Middle name
     * @param ln Last name
     * @param nn Nickname
     * @param group YFCGroup
     * @param address Address
     */
    public Member(Position pos, String fn, String mn, String ln, String nn, YFCGroup group,
    		String address1, String address2, String addressCity, String addressProvince,
    		String postcode) {
    	this();
    	this.position = pos;
    	this.firstname = fn;
    	this.middlename = mn;
    	this.lastname = ln;
    	this.nickname = nn;
    	this.address = new Address(address1, address2, addressCity, addressProvince, postcode);
    }
    
    /**
     * 
     * @param fn
     * @param mn
     * @param ln
     * @param nn
     * @param contact
     */
    public Member(String fn, String mn, String ln, String nn, String contact) {
    	this();
    	this.firstname = fn;
    	this.middlename = mn;
    	this.lastname = ln;
    	this.nickname = nn;
    	this.cellphoneNumber = contact;
    }
    
//    public Member(Position position, String firstname, String middlename,
//			String lastname, String nickname, YFCGroup group) {
//    	this();
//		this.position = position;
//		this.firstname = firstname;
//		this.middlename = middlename;
//		this.lastname = lastname;
//		this.nickname = nickname;
//		this.group = group;
//	}

    public Parent getMother() {
        return mother;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }

    public Parent getFather() {
        return father;
    }

    public void setFather(Parent father) {
        this.father = father;
    }

    public void addEmergencyContact(EmergencyContact ec) {
        emergencyContactList.add(ec);
    }

    public List<EmergencyContact> getEmergencyContactList() { return emergencyContactList; }
    
    

	public boolean isKfcTransfer() {
        return kfcToYfc;
    }

    public void setKfcToYfc(boolean kfcToYfc) {
        this.kfcToYfc = kfcToYfc;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
    
    /**
     * @return position position of member
     */
    public Position getPosition() {

        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    /**
     * 
     * @param month
     * @param day
     * @param year
     */
    public void setBirthday(int month, int day, int year) {
        cal.set(year, month-1, day);
        this.birthday = cal.getTime();
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public int getAge() {
        Calendar calBirthday = Calendar.getInstance();
        Calendar calToday = Calendar.getInstance();
        calBirthday.setTime(birthday);

        int age = calToday.get(Calendar.YEAR) - calBirthday.get(Calendar.YEAR);

        if (calToday.get(Calendar.MONTH) < calBirthday.get(Calendar.MONTH)) {
            age--;
        } else if ((calToday.get(Calendar.MONTH) == calBirthday.get(Calendar.MONTH))
                && (calToday.get(Calendar.DAY_OF_MONTH) < calBirthday.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        return age;
    }
    
    public int getYfcAge() {
        return cal.get(Calendar.YEAR) - yfcEntryYear;
    }

    public int getYfcEntryYear() {
        return this.yfcEntryYear;
    }

    public void setYfcEntryYear(int yfcEntryYear) {
        this.yfcEntryYear = yfcEntryYear;
    }
	
	public YFCGroup getGroup() {
		return group;
	}

	public void setGroup(YFCGroup group) {
		this.group = group;
	}
	
    public ShirtSize getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(ShirtSize shirtSize) {
        this.shirtSize = shirtSize;
    }
    
    public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }


    public String getSpecialSkills() {
        return specialSkills;
    }

    public void setSpecialSkills(String specialSkills) {
        this.specialSkills = specialSkills;
    }

    public String getIllness() {
        return illnesses;
    }

    public void setIllness(String illness) {
        this.illnesses = illness;
    }

    /*public void addSeminarRetreatList(SeminarRetreat seminarRetreat) {
        this.seminarRetreatList.add(seminarRetreat);
    }

    public void setSeminarRetreatList(ArrayList<SeminarRetreat> seminarRetreatList) {
        this.seminarRetreatList = seminarRetreatList;
    }

    public ArrayList<SeminarRetreat> getSeminarRetreatList() {
        return seminarRetreatList;
    }*/
    
    @Override
    public String toString() {
    	return nickname + " " + lastname + " (" + position + ")";
    }
    
    public Object[] toArray() {
        return new Object[] {this, position, lastname, firstname, birthday, cellphoneNumber};
    }
    
    public boolean equals(Object obj) {
    	if (this == obj) return true;
        if ( !(obj instanceof Member) ) return false;

        final Member other = (Member) obj;

        if ( other.getId() != getId() ) return false;

        return true;
    }
}