package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_336 {
    private static final List<char[]> maximixes = new ArrayList<>();

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int size = 11;
        int n = 2011;
        return nthSorted(n-1, size);
    }

    private static String nthSorted(int n, int size) {
        maximixes(size);
        maximixes.sort(Arrays::compare);
        return String.valueOf(maximixes.get(n));
    }

    private static void maximixes(int n) {
        char A = 'A';
        char[] s = new char[n];
        for (int i = 0; i < n; i++) {
            s[i] = (char) (A + i);
        }
        char last2 = s[n-2];
        s[n-2] = s[n-3];
        s[n-3] = last2;
        maximixes(s, n-4);
    }

    private static void maximixes(char[] s, int place) {
        if (place < 0) {
            maximixes.add(s);
            return;
        }
        for (int i = 1; i <= s.length - place - 2; i++) {
            char[] s2 = s.clone();
            for (int j = place; j < place+i; j++) {
                s2[j] = s[s.length-j+place-1];
            }
            s2[place+i] = s[place];
            System.arraycopy(s, place + i + 1 - i, s2, place + i + 1, s.length - (place + i + 1));
            maximixes(s2, place-1);
        }
    }
}
