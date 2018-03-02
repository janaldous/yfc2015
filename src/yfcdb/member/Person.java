package yfcdb.member;

import java.io.Serializable;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * An abstract class. For the creation of Person objects
 * @author Jat Torres
 * @version 20.02.2018
 */
@MappedSuperclass
public abstract class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8025959945849268362L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    protected int id;
	
	@Column(name="date_created")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateLastCreated;
	
	@Column(name="date_last_updated")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateLastUpdated;
    
	@Column(name="first_name")
	protected String firstname;
    
	@Column(name="middle_name")
	protected String middlename;
    
	@Column(name="last_name")
	protected String lastname;
	
	@Column(name="nickname")
	protected String nickname;
	
	@Enumerated(EnumType.ORDINAL)
	protected Gender gender;
	
	@Column(name="cell_number")
	protected String cellphoneNumber;
	
	@Column(name="email")
	protected String email;
	
    protected final static SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
    
    protected static Calendar cal = Calendar.getInstance();
        
    public Person() {
    }
    
    /**
     * Creates a person.
     * @param fn
     * @param mn
     * @param ln
     * @param nn
     */
    public Person(String fn, String mn, String ln, String nn) {
	    this();
	    firstname = fn;
	    middlename = mn;
	    lastname = ln;
	    nickname = nn;
    }

    /**
     * overrides toString method of Object superclass
     */
    @Override
    public String toString() {
        return nickname + " " + lastname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }
    
    public int getId() {
		return id;
	}

	/**
     * @return	firstname first name of member
     */
    public String getFirstName() {
        return firstname;
    }
    /**
     * @return	middlename middle name of member
     */

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getMiddlename() {
        return middlename;
    }
    /**
     * @return	lastname last name of member
     */

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }
    /**
     * @return	nickname nickname of member
     */

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    /**
     * @return date last date record was changed
     */
    public Date getDateUpdated() {
        return dateLastUpdated;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return firstname + " '" + nickname + "' " + middlename + " " + lastname;
    }

    public String getShortName() { return nickname + " " + lastname; }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
