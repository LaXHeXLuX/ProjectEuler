package euler;

import utils.Diophantine;

public class PE_001 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[] numbers = {3, 5};
        long limit = 1000;
        return String.valueOf(sumOfMultiples(numbers, limit-1));
    }

    private static long sumOfMultiples(int[] multiples, long limit) {
        long sum = 0;
        for (int i = 1; i <= multiples.length; i++) {
            long tempSum = sumOfCombinations(multiples, i, limit);
            if (i % 2 == 1) sum += tempSum;
            else sum -= tempSum;
        }
        return sum;
    }

    private static long sumOfCombinations(int[] multiples, int count, long limit) {
        return sumOfCombinations(multiples, 0, count, 1, limit);
    }

    private static long sumOfCombinations(int[] multiples, int start, int count, long lcm, long limit) {
        if (count == 0) return sumOfMultiples(lcm, limit);
        long sum = 0;
        int end = multiples.length - count;
        for (int i = start; i <= end; i++) {
            sum += sumOfCombinations(multiples,i+1, count-1, Diophantine.lcm(lcm, multiples[i]), limit);
        }
        return sum;
    }

    private static long sumOfMultiples(long multiple, long limit) {
        long base = limit/multiple;
        return base * (base+1) / 2 * multiple;
    }
}
