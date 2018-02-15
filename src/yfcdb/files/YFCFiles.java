package yfcdb.files;

import yfcdb.events.Event;
import yfcdb.events.EventList;
import yfcdb.member.Person;
import yfcdb.member.PersonList;

import java.util.ArrayList;

/**
 * Created by janaldoustorres on 03/06/15.
 */
public class YFCFiles {
    //private PersonList personList;
    //private EventList eventList;
    private ArrayList<Person> personArrayList;
    private ArrayList<Event> eventArrayList;


    public YFCFiles() {
        PersonList personList = PersonList.getInstance();
        EventList eventList = EventList.getInstance();
        personArrayList = personList.getPersonArrayList();
        eventArrayList = eventList.getEventArrayList();
    }

    public ArrayList<Person> getPersonArrayList() {
        return personArrayList;
    }

    public ArrayList<Event> getEventArrayList() {
        return eventArrayList;
    }



    /*public PersonList getPersonList() {
        return personList;
    }

    public EventList getEventList() {
        return eventList;
    }*/
}
