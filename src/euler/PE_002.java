package euler;

public class PE_002 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long n = 4_000_000;
        return String.valueOf(sumOfFibonacciNumbers(n));
    }

    private static long sumOfFibonacciNumbers(long limit) {
        long a = 1;
        long b = 1;
        long c = a+b;
        long sum = 0;

        while (c < limit) {
            sum += c;
            a = c+b;
            b = a+c;
            c = a+b;
        }

        return sum;
    }
}
