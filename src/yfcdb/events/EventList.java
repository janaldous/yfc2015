package yfcdb.events;

import yfcdb.member.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

/**
 * Created by janaldoustorres on 01/06/15.
 */
public class EventList extends Observable {
    private static List<Event> eventArrayList;
    private static EventList eventList = new EventList();

    public EventList() {
        eventArrayList = new ArrayList<Event>();
    }

    public static EventList getInstance() {
        return eventList;
    }

    public void setEventArrayList(List<Event> list) {
        this.eventArrayList = list;
    }

    public void addEvent(Event event) {
        if (!eventArrayList.contains(event)) {
            eventArrayList.add(event);
            setChanged();
            notifyObservers();
        }
    }

    public boolean contains(Event event) {
        return eventArrayList.contains(event);
    }

    public boolean removeEvent(Event event) {
        setChanged();
        notifyObservers();
        return eventArrayList.remove(event);
    }

    public void print() {
        for (Event event: eventArrayList) {
            System.out.println(event);
        }

        System.out.println(eventArrayList.size());
    }

    //TODO potentially dangerous to do this. should not reveal insides
    public List<Event> getEventArrayList() {
        return eventArrayList;
    }

    public ArrayList<Event> getEventsOn(int month, int year) {
        ArrayList<Event> list = new ArrayList<Event>();
        for (Event event: eventArrayList) {
            if (event.wasOn(month, year)) {
                list.add(event);
            }
        }
        return list;
    }

    public ArrayList<Event> getEventsOfType(EventType type, Date start, Date end) {
        ArrayList<Event> list = new ArrayList<Event>();
        for (Event event: eventArrayList) {
            if (event.getType().equals(type)
                    && event.getStartDate().after(start) && event.getEndDate().before(end)) {
                list.add(event);
            }
        }
        return list;
    }

    public ArrayList<Event> getEventsOfType(EventType type) {
        ArrayList<yfcdb.events.Event> list = new ArrayList<yfcdb.events.Event>();
        for (yfcdb.events.Event event: eventArrayList) {
            if (event.getType().equals(type)) {
                list.add(event);
            }
        }
        return list;
    }

    public List<Event> getPastoralFormationEvents() {
        List<Event> list = new ArrayList<Event>();
        for (Event event: eventArrayList) {
            if (event.getType().isPastoralFormation()) {
                list.add(event);
            }
        }
        return list;
    }

    /*public List<Event> getEventsAttendedBy(Person person) {
        List<Event> list = new ArrayList<Event>();
        for (Event event: eventArrayList) {
            if (event.wasAttendedBy(person)) {
                list.add(event);
            }
        }

        return list;
    }*/
}
