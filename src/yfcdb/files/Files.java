package yfcdb.files;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import yfcdb.events.Attendee;
import yfcdb.events.Event;
import yfcdb.events.EventList;
import yfcdb.member.Member;
import yfcdb.member.PersonList;

import javax.swing.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by janaldoustorres on 03/06/15.
 */
public class Files extends ExternalResource {
    private final static String filename = "yfc.json";

    public void saveToFile() throws IOException {
        FileWriter file = new FileWriter(filename);
        file.write(JsonWriter.objectToJson(new YFCFiles()));
        file.flush();
        file.close();
    }

    public void uploadFromFile() throws FileNotFoundException, IOException {
        JsonReader jr = new JsonReader(new FileInputStream(filename));
        YFCFiles yfcFiles = (YFCFiles)jr.readObject();
        PersonList personList = PersonList.getInstance();
        personList.setPersonArrayList(yfcFiles.getPersonArrayList());
        EventList eventList = EventList.getInstance();
        eventList.setEventArrayList(yfcFiles.getEventArrayList());
    }

    public static void uploadFromFile(File file) throws FileNotFoundException, IOException {
        JsonReader jr = new JsonReader(new FileInputStream(file));
        YFCFiles yfcFiles = (YFCFiles)jr.readObject();
        PersonList personList = PersonList.getInstance();
        personList.setPersonArrayList(yfcFiles.getPersonArrayList());
        EventList eventList = EventList.getInstance();
        eventList.setEventArrayList(yfcFiles.getEventArrayList());
    }

    public static void chooseUpload() throws FileNotFoundException, IOException {
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println(file);

            uploadFromFile(file);

            JOptionPane.showMessageDialog(null, "Uploaded");
        }
    }

    public static void downloadJSON() throws IOException {
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser("Downloads");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println(file);
            //This is where a real application would open the file.

            FileWriter fw = new FileWriter(file+"/"+filename);
            fw.write(JsonWriter.objectToJson(new YFCFiles()));
            fw.flush();
            fw.close();

            JOptionPane.showMessageDialog(null, "Saved as yfc.json");
        }
    }

	@Override
	public void updateOrSaveMember(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrSaveEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<Attendee> getEventAttendeesIterator(Event event) {
		return event.getAttendees().iterator();
	}

	@Override
	public int getNoOfEventAttendees(Event event) {
		return event.getAttendees().size();
	}

	@Override
	public List<Attendee> getEventAttendees(Event event) {
		return event.getAttendees();
	}
}
