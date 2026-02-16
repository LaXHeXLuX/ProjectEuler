package euler;

import utils.Diophantine;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PE_140 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int step = 4;
        int targetCount = 30;
        List<Long> nuggets = goldenNuggets(step);
        return sumFirst(nuggets, targetCount);
    }

    private static long sumFirst(List<Long> list, int n) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += list.get(i);
        }
        return sum;
    }

    private static List<Long> goldenNuggets(int step) {
        int D = 5;

        List<Long> nuggets = new ArrayList<>();
        long[] f = Diophantine.pell(D);
        List<long[]> fundamentals = Diophantine.pell(D, 4*(step*step - step - 1));
        for (long[] fundamental : fundamentals) {
            long[] current = {fundamental[0], fundamental[1]};
            while (true) {
                long x = current[0];
                if (x < 0) x = -x;
                long k5 = x - 2L*step + 1;
                if (k5 > 0 && k5 % 5 == 0) nuggets.add(k5/5);

                BigInteger[] bigF = {BigInteger.valueOf(f[0]), BigInteger.valueOf(f[1])};
                BigInteger[] bigCurrent = {BigInteger.valueOf(current[0]), BigInteger.valueOf(current[1])};
                bigCurrent = pellNext(bigF, bigCurrent, BigInteger.valueOf(D));
                try {
                    current = new long[] {bigCurrent[0].longValueExact(), bigCurrent[1].longValueExact()};
                }
                catch (ArithmeticException e) {
                    break;
                }
            }
        }
        nuggets.sort(Long::compareTo);
        return nuggets;
    }

    private static BigInteger[] pellNext(BigInteger[] fundamental, BigInteger[] current, BigInteger D) {
        return new BigInteger[] {
                fundamental[0].multiply(current[0]).add(D.multiply(fundamental[1]).multiply(current[1])),
                fundamental[1].multiply(current[0]).add(fundamental[0].multiply(current[1]))
        };
    }
}
