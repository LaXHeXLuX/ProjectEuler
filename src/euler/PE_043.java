package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.List;

public class PE_043 {
    private static final int[] primes = {2, 3, 5, 7, 11, 13, 17};
    private static final List<Long> numbers = new ArrayList<>();

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        numbersWithProperty();
        return String.valueOf(sum());
    }

    private static void numbersWithProperty() {
        for (int i1 = 0; i1 < 10; i1++) {
            int used1 = 1 << i1;
            for (int i2 = 0; i2 < 10; i2++) {
                if ((used1 & (1 << i2)) != 0) continue;
                int used2 = used1 | (1 << i2);
                for (int i3 = 0; i3 < 10; i3+=2) {
                    if ((used2 & (1 << i3)) != 0) continue;
                    int used3 = used2 | (1 << i3);
                    numbersWithProperty(used3, 100*i1 + 10*i2 + i3, 1);
                }
            }
        }
    }

    private static void numbersWithProperty(int mask, long current, int i) {
        if (i == primes.length) {
            int j;
            for (j = 0; j < 10; j++) {
                if ((mask & (1 << j)) == 0) break;
            }
            numbers.add(Diophantine.pow10[9]*j + current);
            return;
        }

        for (int j = 0; j < 10; j++) {
            if ((mask & (1 << j)) != 0) continue;
            long next = current*10 + j;
            if ((next % 1000) % primes[i] != 0) continue;
            numbersWithProperty(mask | (1 << j), next, i+1);
        }
    }

    private static long sum() {
        long sum = 0;
        for (long l : numbers) sum += l;
        return sum;
    }
}
