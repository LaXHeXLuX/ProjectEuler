package euler;

import utils.Divisors;

import java.util.*;

public class PE_075 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int perimeterLimit = 1_500_000;

        Map<Integer, Integer> perimeters = getPerimeters(perimeterLimit);

        int n = 1;
        List<Integer> oneSolutionPerimeters = getNSolutionPerimeters(n, perimeters);
        return oneSolutionPerimeters.size();
    }

    private static List<Integer> getNSolutionPerimeters(int n, Map<Integer, Integer> perimeters) {
        List<Integer> nSolutionPerimeters = new ArrayList<>();

        for (Integer integer : perimeters.keySet()) {
            if (perimeters.get(integer) == n) nSolutionPerimeters.add(integer);
        }

        return nSolutionPerimeters;
    }

    private static Map<Integer, Integer> getPerimeters(int perimeterLimit) {
        Map<Integer, Integer> perimeters = new HashMap<>();

        int limitN = (int) Math.sqrt(perimeterLimit)/2;
        for (int n = 1; n < limitN; n++) {
            int step = n % 2 + 1;
            for (int m = n+1; 2*m*(m+n) < perimeterLimit; m += step) {
                if (Divisors.greatestCommonDivisor(n, m) != 1) continue;
                int limitK = perimeterLimit/(2*m*(m+n));
                for (int k = 1; k <= limitK; k++) {
                    int m2 = m * m;
                    int n2 = n * n;
                    int a = k*(m2 - n2);
                    int b = k*(2*m*n);
                    int c = k*(m2 + n2);
                    int perimeter = a + b + c;
                    perimeters.merge(perimeter, 1, Integer::sum);
                }
            }
        }

        return perimeters;
    }
}
