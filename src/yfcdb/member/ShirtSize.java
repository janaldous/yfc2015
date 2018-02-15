package yfcdb.member;

/**
 * Created by janaldoustorres on 24/05/15.
 */
public enum ShirtSize {
    XXS, XS, S, M, L, XL;

    public String toString() {
        switch (this) {
            case XXS: return "XXS";
            case XS: return "XS";
            case S: return "S";
            case M: return "M";
            case L: return "L";
            case XL: return "XL";
            default: return null;
        }
    }

    public static String[] getShirtSizes() {
        ShirtSize[] shirtSizes = values();
        String[] names = new String[shirtSizes.length];

        for (int i = 0; i < shirtSizes.length; i++) {
            names[i] = shirtSizes[i].toString();
        }

        return names;
    }

    public static ShirtSize convertToShirtSize(String shirtSize) {
        if (shirtSize.equals("XXS")) return XXS;
        else if (shirtSize.equals("XS")) return XS;
        else if (shirtSize.equals("S")) return S;
        else if (shirtSize.equals("M")) return M;
        else if (shirtSize.equals("L")) return L;
        else if (shirtSize.equals("XL")) return XL;
        else return null;
    }
}
