//package yfcdb;
//
//import yfcdb.member.*;
//
//import java.util.ArrayList;
//
///**
// * Created by janaldoustorres on 01/06/15.
// */
//public abstract class YFCList<T> {
//    private static ArrayList<T> arrayList;
//    private static YFCList<T> yfcList = new YFCList<T>();
//
//    public YFCList() {
//        arrayList = new ArrayList<T>();
//    }
//
//    public static ArrayList<T> getArrayList() {
//        return arrayList;
//    }
//
//    public static YFCList<T> getInstance() {
//        return yfcList;
//    }
//
//    public static void addItem(T thing) {
//        arrayList.add(thing);
//    }
//
//    public boolean contains(T member) {
//        if (arrayList.contains(member)) return true;
//        return false;
//    }
//
//    public void print() {
//        for (T thing: arrayList) {
//            System.out.println(thing);
//        }
//
//        System.out.println(arrayList.size());
//    }
//}
