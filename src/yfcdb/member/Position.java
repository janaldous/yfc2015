package yfcdb.member;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public enum Position {
    MEMBER, CHAPTER_LEADER, COORDINATOR, HOUSEHOLD_HEAD, LIE_LOW, RETIRED;

    public String toString() {
        switch (this) {
            case MEMBER: return "Member";
            case CHAPTER_LEADER: return "Chapter leader";
            case COORDINATOR: return "Coordinator";
            case HOUSEHOLD_HEAD: return "Household head";
            case LIE_LOW: return "Lie low";
            case RETIRED: return "Retired";
            default: return null;
        }
    }

    public static String[] getPositions() {
        Position[] positions = values();
        String[] names = new String[positions.length];

        for (int i = 0; i < positions.length; i++) {
            names[i] = positions[i].toString();
        }

        return names;
    }

    public static Position convertToPosition(String position) {
        if (position.equals("Member")) return MEMBER;
        else if (position.equals("Chapter head")) return CHAPTER_LEADER;
        else if (position.equals("Coordinator")) return COORDINATOR;
        else if (position.equals("Household head")) return HOUSEHOLD_HEAD;
        else return null;
    }
}
