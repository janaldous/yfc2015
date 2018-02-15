package yfcdb.files;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import yfcdb.events.EventList;
import yfcdb.member.PersonList;

import javax.swing.*;
import java.io.*;

/**
 * Created by janaldoustorres on 03/06/15.
 */
public class Files {
    private final static String filename = "yfc.json";

    public static void saveToFile() throws IOException {
        FileWriter file = new FileWriter(filename);
        file.write(JsonWriter.objectToJson(new YFCFiles()));
        file.flush();
        file.close();
    }

    public static void uploadFromFile() throws FileNotFoundException, IOException {
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
}
