package yfcdb.member;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by janaldoustorres on 29/05/15.
 */
@Deprecated
public class MemberList {
    private static ArrayList<Member> memberArrayList;
    private static MemberList memberList = new MemberList();

    public MemberList() {
        memberArrayList = new ArrayList<Member>();
        //uploadFromFile();
    }

    public static ArrayList<Member> getMemberArrayList() {
        return memberArrayList;
    }

    public static MemberList getInstance() {
        return memberList;
    }

    public static void addMember(Member member) {
        memberArrayList.add(member);
    }

    @Deprecated
    public void populateList() {
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

        memberArrayList.add(jat);
        memberArrayList.add(chelsie);
    }

    public boolean contains(Member member) {
        if (memberArrayList.contains(member)) return true;
        return false;
    }

    public void print() {
        for (Member member: memberArrayList) {
            System.out.println(member);
        }

        System.out.println(memberArrayList.size());
    }

    public static void saveToFile() throws IOException {
        FileWriter file = null;
            file = new FileWriter("members.json");

            file.write(JsonWriter.objectToJson(memberArrayList));

            file.flush();
            file.close();

    }

    public static void uploadFromFile() throws IOException {
            BufferedReader br = new BufferedReader(new FileReader(new File("members.json")));
            String line;
            int c = 0;
            while ((line = br.readLine()) != null) {
                memberArrayList = (ArrayList<Member>)JsonReader.jsonToJava(line);
                c++;
            }
            System.out.println(c);
    }
}
