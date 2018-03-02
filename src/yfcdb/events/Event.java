package yfcdb.events;

import yfcdb.member.Member;
import yfcdb.member.Person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

/**
 * Created by janaldoustorres on 19/05/15.
 */
@Entity
@Table(name="event")
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
    private String name;
    
    private String venue;
    
    private String notes;
    
    @Enumerated(EnumType.ORDINAL)
    private EventType type;
    
    private double regFee;
    
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @ElementCollection
    private List<Attendee> attendees;

    protected final static SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
    
    protected static Calendar cal = Calendar.getInstance();
    
    public Event(String name, EventType type, String venue, String notes, int regFee, Date startDate, Date endDate)
    {
        this();
        this.name = name;
        this.type = type;
        this.venue = venue;
        this.notes = notes;
        this.regFee = regFee;
        setStartDate(startDate);
        setEndDate(endDate);
        //EventList.addEvent(this);
    }

    public Event() {
        //id++;
        attendees = new ArrayList<Attendee>();
    }

    /**
     * gets name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets type
     * @return the type
     */
    public EventType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(EventType type) {
        this.type = type;
    }

    /**
     * gets venue
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * gets notes
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * gets regFee
     * @return the regFee
     */
    public double getRegFee() {
        return regFee;
    }

    /**
     * @param regFee the regFee to set
     */
    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    /**
     * gets noOfAttendees
     * @return the noOfAttendees
     */
    
    public int getNoOfAttendees() {
        return attendees.size();
    }

    /**
     * gets timeStart
     * @return the timeStart
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the timeStart to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * gets timeEnd
     * @return the timeEnd
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the timeEnd to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void addAttendee(Member m, Role r) {
        attendees.add(new Attendee(m, r));
    }

    public String toString() {
        if (name.isEmpty()) {
            return type + " (" + dt.format(startDate) + ")";
        }
        return name + " (" + type + ")";
    }

    public Object[] toArray() {
        return new Object[] {dt.format(startDate), this, type, venue, notes, attendees.size()};
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }
    
    public void setAttendees(List<Attendee> attendees) {
    	this.attendees = attendees;
    }

    public boolean wasAttendedBy(Member member) {
        return attendees.contains(member);
    }

    public boolean wasOn(int month, int year) {
        cal.setTime(startDate);
        if (cal.get(Calendar.MONTH) == month && cal.get(Calendar.YEAR) == year) {
            return true;
        }
        return false;
    }

    public int getDay() {
        cal.setTime(startDate);
        return cal.get(Calendar.DAY_OF_MONTH);
    }
}
