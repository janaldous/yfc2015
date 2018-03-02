package yfcdb.files;

import yfcdb.events.Event;
import yfcdb.events.EventList;
import yfcdb.member.Member;
import yfcdb.member.Person;
import yfcdb.member.PersonList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janaldoustorres on 03/06/15.
 */
public class YFCFiles {
    //private PersonList personList;
    //private EventList eventList;
    private List<Member> personArrayList;
    private List<Event> eventArrayList;


    public YFCFiles() {
        PersonList personList = PersonList.getInstance();
        EventList eventList = EventList.getInstance();
        personArrayList = personList.getPersonArrayList();
        eventArrayList = eventList.getEventArrayList();
    }

    public List<Member> getPersonArrayList() {
        return personArrayList;
    }

    public List<Event> getEventArrayList() {
        return eventArrayList;
    }



    /*public PersonList getPersonList() {
        return personList;
    }

    public EventList getEventList() {
        return eventList;
    }*/
}
