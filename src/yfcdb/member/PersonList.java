package yfcdb.member;

/**
 * Created by janaldoustorres on 02/06/15.
 */

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by janaldoustorres on 29/05/15.
 */
public class PersonList extends Observable {
    private static List<Member> personArrayList;
    private static PersonList personList = new PersonList();

    public PersonList() {
        personArrayList = new ArrayList<Member>();
        //uploadFromFile();
    }

    public List<Member> getPersonArrayList() {
        return personArrayList;
    }

    public static PersonList getInstance() {
        return personList;
    }

    public void addPerson(Member person) {
        personArrayList.add(person);
        setChanged();
        notifyObservers();
    }

    public boolean contains(Member person) {
        if (personArrayList.contains(person)) return true;
        return false;
    }

    public boolean removePerson(Member person) {
        setChanged();
        notifyObservers();
        return personArrayList.remove(person);
    }

    public void print() {
        for (Person person: personArrayList) {
            System.out.println(person);
        }

        System.out.println(personArrayList.size());
    }

    public String getChapterLeaders() {
        String out = "";
        for (Person person: personArrayList) {
            if (person instanceof ChapterHead) {
                out += person.getShortName() + "/";
            }
        }
        if (!out.isEmpty()) {
            out = out.substring(0, out.length()-1);
        } else {
            out = "none chosen";
        }
        return out;
    }

    public String getCoordinators() {
        String out = "";
        for (Person person: personArrayList) {
            if (person instanceof Coordinator) {
                out += person.getShortName() + "/";
            }
        }
        if (!out.isEmpty()) {
            out = out.substring(0, out.length()-1);
        } else {
            out = "none chosen";
        }
        return out;
    }

	public void setPersonArrayList(List<Member> list) {
		personArrayList = list;
	}


}

