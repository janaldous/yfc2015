package yfcdb.member;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public enum BloodType {
    Opos, Oneg, Apos, Aneg, Bpos, Bneg, ABpos, ABneg, IDK;

    public String toString() {
        switch (this) {
            case Opos: return "O+";
            case Oneg: return "O-";
            case Apos: return "A+";
            case Aneg: return "A-";
            case Bpos: return "B+";
            case Bneg: return "B-";
            case ABpos: return "AB+";
            case ABneg: return "AB-";
            default: return null;
        }
    }

    public static String[] getBloodTypes() {
        BloodType[] bloodTypes = values();
        String[] names = new String[bloodTypes.length];

        for (int i = 0; i < bloodTypes.length; i++) {
            names[i] = bloodTypes[i].toString();
        }

        return names;
    }

    public static BloodType convertToBloodType(String bloodType) {
        if (bloodType.equals("O+")) return Opos;
        else if (bloodType.equals("O-")) return Oneg;
        else if (bloodType.equals("A+")) return Apos;
        else if (bloodType.equals("A-")) return Aneg;
        else if (bloodType.equals("B+")) return Bpos;
        else if (bloodType.equals("B-")) return Bneg;
        else if (bloodType.equals("AB+")) return ABpos;
        else if (bloodType.equals("AB-")) return ABneg;
        else return null;
    }
}
