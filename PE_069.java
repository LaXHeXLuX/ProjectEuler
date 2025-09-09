import util.Converter;
import util.Primes;

public class PE_069 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long limit = 1_000_000L;
        return highestNumberScore(limit);
    }

    private static long highestNumberScore(long limit) {
        int[] primes = Converter.booleanArrToIntArr(Primes.sieveOfPrimes((int) Math.max(100, Math.sqrt(limit))));
        long n = primes[0];
        long lastN = 0;
        int i = 1;
        while (n < limit) {
            lastN = n;
            n *= primes[i];
            if (n < 0) return lastN;
            i++;
        }
        return lastN;
    }
}
