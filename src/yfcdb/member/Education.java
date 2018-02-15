package yfcdb.member;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class Education {
    private String school, level, course;
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
