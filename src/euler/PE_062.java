package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PE_062 {
    private static final Map<Long, List<Long>> powerPermutations = new HashMap<>();

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int power = 3;
        int count = 5;
        long result = smallestWithPowerPermutations(power, count);
        return String.valueOf(result);
    }

    private static long smallestWithPowerPermutations(int power, int count) {
        int limit = (int) Math.pow(Long.MAX_VALUE, 1.0/power);
        long smallest = Long.MAX_VALUE;
        for (int i = 1; i < limit; i++) {
            long p = Diophantine.pow(i, power);
            long digits = digits(p);
            if (!powerPermutations.containsKey(digits)) {
                powerPermutations.put(digits, new ArrayList<>());
            }
            List<Long> list = powerPermutations.get(digits);
            list.add(p);
            if (list.size() >= count) {
                long s = list.getFirst();
                if (s < smallest) {
                    smallest = s;
                    limit = (int) Math.pow(Diophantine.pow10[(int) (Math.log10(s) + 2)], 1.0/power);
                }
            }
        }
        return smallest;
    }

    private static long digits(long p) {
        long fingerprint = 0;
        while (p > 0) {
            fingerprint += Diophantine.pow10[(int) (p % 10)];
            p /= 10;
        }
        return fingerprint;
    }
}