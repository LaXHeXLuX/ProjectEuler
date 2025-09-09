import util.Primes;

public class PE_010 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 2_000_000;
        return sumOfAllTrueElements(Primes.sieveOfPrimes(n));
    }

    private static long sumOfAllTrueElements(boolean[] elements) {
        long sum = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i]) sum += i;
        }
        return sum;
    }
}
