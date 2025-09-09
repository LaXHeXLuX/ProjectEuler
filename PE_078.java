import util.PolygonalNumber;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PE_078 {
    private static Map<Integer, BigInteger> eulersFunction;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        eulersFunction = new HashMap<>();

        int n = 1_000_000;
        return firstToDivideN(n);
    }

    private static int firstToDivideN(int n) {
        int answer = 1;
        BigInteger ways = eulersFunction(answer);

        while (!divides(ways, n)) {
            answer++;
            ways = eulersFunction(answer);
        }

        return answer;
    }

    private static boolean divides(BigInteger n, int divider) {
        BigInteger dividerBigInteger = BigInteger.valueOf(divider);
        return n.remainder(dividerBigInteger).equals(BigInteger.ZERO);
    }

    private static int getNthStep(int n) {
        return (int) PolygonalNumber.polygonalNumberLong(5, n);
    }

    private static BigInteger eulersFunction(int n) {
        if (n < 0) return BigInteger.ZERO;
        if (n == 0) return BigInteger.ONE;

        if (eulersFunction.containsKey(n)) return eulersFunction.get(n);

        int step = 1;
        BigInteger sum = BigInteger.ZERO;
        while (step <= n) {
            int nthStep = getNthStep(step);
            if (nthStep > n) break;
            if (step % 2 == 1) {
                sum = sum.add(eulersFunction(n-nthStep));
                sum = sum.add(eulersFunction(n-nthStep-step));
            } else {
                sum = sum.subtract(eulersFunction(n-nthStep));
                sum = sum.subtract(eulersFunction(n-nthStep-step));
            }
            step++;
        }

        eulersFunction.put(n, sum);

        return sum;
    }
}
