import UsefulFunctions.SieveOfPrimes;

public class PE_010 {
    public static void main(String[] args) {
        int n = 2_000_000;
        System.out.println(sumOfAllTrueElements(SieveOfPrimes.sieveOfPrimes(n)));
    }
    private static long sumOfAllTrueElements(boolean[] elements) {
        long sum = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i]) sum += i;
        }
        return sum;
    }
}
