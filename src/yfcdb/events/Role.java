package yfcdb.events;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public enum Role {
    PRESENT, SPEAKER, SHARER, HOUSEHOLD_LEADER, SERVICE_TEAM, TEAM_LEADER, ASS_TEAM_LEADER,
    HEAD_SERVANT, ASS_HEAD_SERVANT, PRAYER_WARRIOR, FOOD_COMMITTEE, MUSIC_MINISTRY;

    @Override
    public String toString() {
        switch (this) {
            case PRESENT: return "P";
            case SPEAKER: return "Speaker";
            case SHARER: return "Sharer";
            case HOUSEHOLD_LEADER: return "Household Leader";
            case SERVICE_TEAM: return "Service Team";
            case TEAM_LEADER: return "Team Leader";
            case ASS_TEAM_LEADER: return "Ass. Team Leader";
            case HEAD_SERVANT: return "Head Servant";
            case ASS_HEAD_SERVANT: return "Ass Head Servant";
            case PRAYER_WARRIOR: return "Prayer Warrior";
            case FOOD_COMMITTEE: return "Food Committee";
            case MUSIC_MINISTRY: return "Music Ministry";
            default: return null;
        }
    }

    public static String[] getRoles() {
        Role[] roles = values();
        String[] names = new String[roles.length];

        for (int i = 0; i < roles.length; i++) {
            names[i] = roles[i].toString();
        }

        return names;
    }
}
