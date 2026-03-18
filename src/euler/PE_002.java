package euler;

public class PE_002 {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PE() {
        long n = 4_000_000;
        return String.valueOf(sumOfFibonacciNumbers(n));
    }

    private static long sumOfFibonacciNumbers(long limit) {
        long f1 = 1L;
        long f2 = 2L;
        long sum = 0;

        while (f2 < limit) {
            sum += f2;
            long oldF1 = f1;
            long oldF2 = f2;
            f1 = oldF1 + 2*oldF2;
            f2 = 2*oldF1 + 3*oldF2;
        }

        return sum;
    }
}
