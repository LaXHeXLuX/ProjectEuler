import java.math.BigInteger;

public class PE_025 {
    public static void main(String[] args) {
        long n = 1_000L;
        System.out.println(firstFibonacciWithNDigits(n));
    }
    private static long firstFibonacciWithNDigits(long n) {
        BigInteger fibo1 = BigInteger.ONE;
        BigInteger fibo2 = BigInteger.ONE;
        long indexOfFibo2 = 2;
        while (numberOfDigits(fibo2) < n) {
            BigInteger temp = fibo2;
            fibo2 = fibo2.add(fibo1);
            fibo1 = temp;
            indexOfFibo2++;
        }
        System.out.println(fibo2);
        return indexOfFibo2;
    }
    private static long numberOfDigits(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) == 0) return 0;
        long digitCount = 1;
        while(n.compareTo(BigInteger.TEN) >= 0) {
            digitCount++;
            n = n.divide(BigInteger.TEN);
        }
        return digitCount;
    }
}
