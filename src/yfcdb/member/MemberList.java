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
