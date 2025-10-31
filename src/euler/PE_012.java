package euler;

import utils.Divisors;

public class PE_012 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int count = 500;
        return leastTriangleNumber(count);
    }

    private static long leastTriangleNumber(int divisorCount) {
        int n = 1;
        int divisors = Divisors.divisors(n).length;
        int nextDivisors = Divisors.divisors((n+1)/2).length;
        int totalDivisorCount = divisors * nextDivisors;
        while (totalDivisorCount <= divisorCount) {
            n++;
            divisors = nextDivisors;
            nextDivisors = Divisors.divisors((n+1)/(n % 2 + 1)).length;
            totalDivisorCount = divisors * nextDivisors;
        }
        return (long) n * (n+1) / 2;
    }
}
