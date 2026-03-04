package euler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PE_336 {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PE() {
        int size = 11;
        int n = 2011;
        Maximix maximix = nthSorted(n-1, size);
        return maximix.s;
    }

    private static Maximix nthSorted(int n, int size) {
        List<Maximix> m = maximixes(size);
        m.sort(Comparator.comparing(Maximix::s));
        return m.get(n);
    }

    private record Maximix(String s, List<Integer> path) {}

    private static List<Maximix> maximixes(int n) {
        String alph = "ABCDEFGHIJKLMNOPQRSTU";
        String s = alph.substring(0, n);
        s = s.substring(0, n - 3) + s.charAt(n-2) + s.charAt(n-3) + s.charAt(n-1);
        return maximixes(s, n-4);
    }

    private static List<Maximix> maximixes(String s, int place) {
        if (place < 0) return List.of(new Maximix(s, new ArrayList<>()));
        List<Maximix> m = new ArrayList<>();
        for (int i = 1; i <= s.length() - place - 2; i++) {
            String s2 = s.substring(0, place) + new StringBuilder(s.substring(place)).reverse();
            s2 = s2.substring(0, place+i) + new StringBuilder(s2.substring(place+i)).reverse();
            List<Maximix> previous = maximixes(s2, place-1);
            for (Maximix maximix : previous) {
                maximix.path.add(i);
                m.add(maximix);
            }
        }
        return m;
    }
}
