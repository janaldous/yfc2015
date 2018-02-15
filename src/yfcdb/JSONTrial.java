package yfcdb;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import yfcdb.member.*;

import java.io.*;

/**
 * Created by janaldoustorres on 01/06/15.
 */
public class JSONTrial {
    public static void main(String[] args) throws IOException {
        YFCGroup chapterC = new YFCGroup("C", "C");
        Address address = new Address("109 Mahogany", "Sta Rosa", "SRVI", "4026");
        Member jat = new Member(Position.MEMBER, "Jan Aldous", "Turla", "Torres", "Jat", chapterC, address);
        jat.setBirthday(8,13,1995);
        //jat.setGender("M");
        //jat.setKfcToYfc(true);
        //jat.setBloodType(BloodType.Oneg);
        jat.setShirtSize(ShirtSize.M);
        jat.setCellphoneNumber("0924090");
        jat.setEmail("jat.torres@gmail.com");
        jat.setEducation(new Education("Brent", "G12", "Comp sci"));
        jat.setFather(new Parent("Father", "Mike", "Supervisor", "089423", "mikelsie05@gmail.com"));
        Coordinator mom = new Coordinator(Prefix.TITA, "Elsie", "Turla", "Torres", "Elsie", chapterC);
        mom.setEmail("etorres@brent.edu.ph");
        mom.setCellphoneNumber("083423432");
        jat.setMother(mom.toParent("Secretary"));
        jat.setYfcEntryYear(2008);
        jat.addEmergencyContact(jat.getMother().toEmergencyContact());
        jat.setSpecialSkills("viola, swimming, programming");
        //jat.setIllness();
        jat.addSeminarRetreatList(new SeminarRetreat("yfc camp", "participant"));

        Member chelsie = new Member(Position.MEMBER, "chelsie", "Erica", "Torres", "Chelsie", chapterC, address);
        chelsie.setBirthday(9,26,1998);
        chelsie.setShirtSize(ShirtSize.M);
        chelsie.setCellphoneNumber("0924090");
        chelsie.setEmail("jat.torres@gmail.com");
        chelsie.setEducation(new Education("Brent", "G12", "Comp sci"));
        chelsie.setFather(new Parent("Father", "Mike", "Supervisor", "089423", "mikelsie05@gmail.com"));
        //Coordinator mom = new Coordinator(Prefix.TITA, "Elsie", "Turla", "Torres", "Elsie", chapterC);
        chelsie.setEmail("etorres@brent.edu.ph");
        chelsie.setCellphoneNumber("083423432");
        chelsie.setMother(mom.toParent("Secretary"));
        chelsie.setYfcEntryYear(2008);
        chelsie.addEmergencyContact(jat.getMother().toEmergencyContact());
        chelsie.setSpecialSkills("viola, swimming, programming");


//        String jatJson = JsonWriter.objectToJson(jat);
//        String chelsieJson = JsonWriter.objectToJson(chelsie);
//        System.out.println(jatJson);
//        System.out.println(chelsieJson);
//
//        Member member = (Member)JsonReader.jsonToJava(jatJson);
//        Member member1 = (Member)JsonReader.jsonToJava(chelsieJson);
//        System.out.println(member);
//        System.out.println(member1);


        FileWriter file = new FileWriter("members.json");
        file.write(JsonWriter.objectToJson(jat));
        file.write("\n");
        file.write(JsonWriter.objectToJson(chelsie));
        file.flush();
        file.close();

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("members.json")));
            String line;
            int c = 0;
            while ((line = br.readLine()) != null) {

                Member member1 = (Member)JsonReader.jsonToJava(line);
                System.out.println(member1);
                c++;
            }
            System.out.println(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
