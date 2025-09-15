import java.math.BigInteger;

public class PE_065 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 10_000;
        BigInteger[] fraction = computeNthConvergentOfE(n);
        BigInteger first = fraction[0];

        int sum = 0;
        while (first.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = first.divideAndRemainder(BigInteger.TEN);
            sum += divRem[1].intValue();
            first = divRem[0];
        }
        return sum;
    }

    private static BigInteger[] computeNthConvergentOfE(int n) {
        n--;
        if (n == 0) return new BigInteger[] {BigInteger.TWO, BigInteger.ONE};
        BigInteger[] currentFraction = {BigInteger.ONE, BigInteger.valueOf(getNthConvergentTerm(n))};

        for (int i = 1; i < n; i++) {
            currentFraction = addToFraction(currentFraction, getNthConvergentTerm(n-i));
            fractionUpsideDown(currentFraction);
        }

        currentFraction = addToFraction(currentFraction, getNthConvergentTerm(0));
        return currentFraction;
    }

    private static BigInteger[] addToFraction(BigInteger[] currentFraction, int adder) {
        BigInteger[] newFraction = new BigInteger[2];
        newFraction[1] = currentFraction[1];
        newFraction[0] = currentFraction[0].add(BigInteger.valueOf(adder).multiply(newFraction[1]));
        return newFraction;
    }

    private static void fractionUpsideDown(BigInteger[] fraction) {
        BigInteger temp = fraction[0];
        fraction[0] = fraction[1];
        fraction[1] = temp;
    }

    private static int getNthConvergentTerm(int n) {
        if (n == 0) return 2;
        if (n % 3 != 2) return 1;
        return (n/3 + 1)*2;
    }
}
