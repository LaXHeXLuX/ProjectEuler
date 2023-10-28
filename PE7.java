import UsefulFunctions.SieveOfPrimes;

public class PE7 {
    public static void main(String[] args) {
        int n = 10_001;
        System.out.println(nthPrime(n));
    }
    private static int nthPrime(int n) {
        int upperBound = upperBoundForNthPrime(n);
        SieveOfPrimes sop = new SieveOfPrimes();
        boolean[] primes = sop.sieveOfPrimes(upperBound);
        int counter = 0;
        for (int i = 0; i <= upperBound; i++) {
            if (primes[i]) counter++;
            if (counter == n) return i;
        }
        return -1;
    }
    private static int upperBoundForNthPrime(int n) {
        if (n < 6) return 12;
        double logN = Math.log(n);
        return (int) (n*logN + n*Math.log(logN));
    }
}
