package yfcdb.member;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public enum Prefix {
    TITO, TITA, KUYA, ATE;

    public String toString() {
        switch (this) {
            case TITO: return "Tito";
            case TITA: return "Tita";
            case KUYA: return "Kuya";
            case ATE: return "Ate";
            default: return null;
        }
    }
}
