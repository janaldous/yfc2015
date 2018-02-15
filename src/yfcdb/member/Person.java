package yfcdb.member;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class Person {
    protected Date dateLastUpdated;
    protected String username, password;
    protected static int numberOfMembers = 0;

    protected Address address;
    protected String id;
    protected Position position;
    protected String firstname, middlename, lastname, nickname;
    protected YFCGroup group;
    protected String gender;
    protected String cellphoneNumber;
    protected String email;
    protected Date birthday;
    protected final static SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
    protected int yfcEntryYear;
    protected ShirtSize shirtSize;

    public Person() {
        setID(numberOfMembers++);
        setUsername();
        setDateUpdated();

        address = new Address();
    }

    public Person(Position pos, String fn, String mn, String ln, String nn, YFCGroup group) {
        this();
        position = pos;
        firstname = fn;
        middlename = mn;
        lastname = ln;
        nickname = nn;
        this.group = group;
    }

    /**
     * overrides toString method of Object superclass
     */
    @Override
    public String toString() {
        return nickname + " " + lastname + " (" + position + ")";
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
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
     * sets username and timestamps update
     */
    public void setUsername() {
        username = getID();
        setDateUpdated();
    }

    /**
     * @return username username of member
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * sets password and timestamps update
     */
    public void setPassword(String pw) {
        password = pw;
        setDateUpdated();
    }

    /**
     * @return password password of member
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * sets id of memeber and timestamps update
     */
    public void setID(int rec) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String memberNo = String.format("%04d", (rec));
        id = year + "-" + memberNo;
        setDateUpdated();
    }

    /**
     * @return id id of member
     */
    public String getID()
    {
        return id;
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

    /**
     * timestamps method whenever there is a change to member fields
     */
    public void setDateUpdated() {
        //get current date time with Date()
        Date date = new Date();
        dateLastUpdated = date;
    }

    /**
     * @return date last date record was changed
     */
    public String getDateUpdated() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String date = dateFormat.format(this.dateLastUpdated);
        return date;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(int month, int day, int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, day);
        this.birthday = c.getTime();
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

    public Object[] toArray() {
        return new Object[] {this, position, lastname, firstname, dt.format(birthday), cellphoneNumber};
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getYfcAge() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) - yfcEntryYear;
    }

    public int getYfcEntryYear() {
        return this.yfcEntryYear;
    }

    public void setYfcEntryYear(int yfcEntryYear) {
        this.yfcEntryYear = yfcEntryYear;
    }

    public ShirtSize getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(ShirtSize shirtSize) {
        this.shirtSize = shirtSize;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) {
//            return true;
//        } else if (obj instanceof Person && ((Person)obj).id == this.id) {
//            return true;
//        }
//        return false;
//    }
}
