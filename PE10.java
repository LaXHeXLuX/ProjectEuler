import UsefulFunctions.SieveOfPrimes;

public class PE10 {
    public static void main(String[] args) {
        int n = 2_000_000;
        SieveOfPrimes sop = new SieveOfPrimes();
        System.out.println(sumOfAllTrueElements(sop.sieveOfPrimes(n)));
    }
    private static long sumOfAllTrueElements(boolean[] elements) {
        long sum = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i]) sum += i;
        }
        return sum;
    }
}
