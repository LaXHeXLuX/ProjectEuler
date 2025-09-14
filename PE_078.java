import util.PolygonalNumber;

import java.util.HashMap;
import java.util.Map;

public class PE_078 {
    private static Map<Integer, Long> eulersFunction;

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
        long ways = eulersFunction(answer, n);

        while (ways != 0) {
            answer++;
            ways = eulersFunction(answer, n);
        }

        return answer;
    }

    private static long eulersFunction(int n, int mod) {
        if (n < 0) return 0;
        if (n == 0) return 1;

        if (eulersFunction.containsKey(n)) return eulersFunction.get(n);

        int step = 1;
        long sum = 0;
        while (step <= n) {
            int nthStep = (int) PolygonalNumber.polygonalNumberLong(5, step);
            if (nthStep > n) break;
            int coefficient = (step % 2) * 2 - 1;
            sum += coefficient * eulersFunction(n-nthStep, mod);
            sum += coefficient * eulersFunction(n-nthStep-step, mod);
            sum = sum % mod;
            step++;
        }

        eulersFunction.put(n, sum);

        return sum;
    }
}
