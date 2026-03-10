package euler.bonus;

import utils.Fraction;

public class PE_Minus1 {
    private static final Fraction<Integer> allSum = new Fraction<>(-1, 12);

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[] multiples = {3, 5};
        Fraction<Integer> result = allSum.multiply(sumOfAllMultiples(multiples));
        return result.num + "/" + result.den;
    }

    private static int sumOfAllMultiples(int[] multiples) {
        int sum = 0;
        for (int i = 1; i <= multiples.length; i++) {
            int tempSum = sumOfCombinations(multiples, i);
            if (i % 2 == 1) sum += tempSum;
            else sum -= tempSum;
        }
        return sum;
    }

    private static int sumOfCombinations(int[] multiples, int count) {
        return sumOfCombinations(multiples, 0, count);
    }

    private static int sumOfCombinations(int[] multiples, int start, int count) {
        if (count == 0) return 1;
        int sum = 0;
        int end = multiples.length - count;
        for (int i = start; i <= end; i++) {
            int nextSum = sumOfCombinations(multiples, i+1, count-1);
            sum += multiples[i] * nextSum;
        }
        return sum;
    }
}
