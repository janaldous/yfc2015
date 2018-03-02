package yfcdb.member;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

/**
 * @author Jat Torres
 * @version 21.02.2018
 */
@Embeddable
public class Education implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3338285871547069507L;
	
	@Basic
	private String school;
	
	@Basic
	private String level;
	
	@Basic
	private String course;
	
    private final static String blank = "";

    public Education() {

    }

    public Education(String school, String level, String course) {
        this.school = school;
        this.level = level;
        this.course = course;
    }

    public String getSchool() {
        return (school == null) ? blank: school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLevel() {
        return (level == null) ? blank: level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCourse() {
        return (course == null) ? blank: course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
