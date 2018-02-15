package yfcdb.events;

import yfcdb.member.Member;
import yfcdb.member.Person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class Event {
    private static int id = 0;
    private String name, venue, notes;
    private EventType type;
    private int regFee;
    private Date startDate, endDate;
    //private ArrayList<Attendee> attendeesList = new ArrayList<Attendee>();
    private HashMap<Person, Role> attendeesMap = new HashMap<Person, Role>();
    protected final static SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");

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
    public int getRegFee() {
        return regFee;
    }

    /**
     * @param regFee the regFee to set
     */
    public void setRegFee(int regFee) {
        this.regFee = regFee;
    }

    /**
     * gets noOfAttendees
     * @return the noOfAttendees
     */
    public int getNoOfAttendees() {
        return attendeesMap.size();
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

    /**
     * @param id the id to set
     */
    public static void setId(int id) {
        Event.id = id;
    }

    public String getId() {
        return Integer.toString(id);
    }

    public void addAttendee(Person p, Role r) {
        attendeesMap.put(p, r);
    }

    public String toString() {
        if (name.isEmpty()) {
            return type + " (" + dt.format(startDate) + ")";
        }
        return name + " (" + type + ")";
    }

    public Object[] toArray() {
        return new Object[] {dt.format(startDate), this, type, venue, notes, getNoOfAttendees()};
    }

    public Object[] toArray(Person person) {
        return new Object[] {dt.format(startDate), this, attendeesMap.get(person)};
    }

    public HashMap<Person, Role> getAttendees() {
        return attendeesMap;
    }

    public boolean wasAttendedBy(Person person) {
        return attendeesMap.containsKey(person);
    }

    public boolean wasOn(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        if (cal.get(Calendar.MONTH) == month && cal.get(Calendar.YEAR) == year) {
            return true;
        }
        return false;
    }

    public int getDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public ArrayList<Person> getPersonsWhoAttended() {
        ArrayList<Person> personArrayList = new ArrayList<Person>();
        personArrayList.addAll(attendeesMap.keySet());
        return personArrayList;
    }
}
