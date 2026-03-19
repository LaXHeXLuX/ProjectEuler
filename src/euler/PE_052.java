package euler;

import utils.Combinations;

import java.util.Arrays;

public class PE_052 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[] multiples = {2, 3, 4, 5, 6};
        return String.valueOf(smallestNumberWithPermutedMultiples(multiples));
    }

    private static int smallestNumberWithPermutedMultiples(int[] multiples) {
        Arrays.sort(multiples);
        int biggestMultiple = multiples[multiples.length-1];
        int limit = 10/biggestMultiple;

        int x = 0;
        while (x >= 0) {
            x++;
            if (firstDigit(x) > limit) continue;
            if (hasPermutedMultiples(x, multiples)) return x;
        }

        return -1;
    }

    private static int firstDigit(int n) {
        while (n >= 10) {
            n /= 10;
        }
        return n;
    }

    private static boolean hasPermutedMultiples(int n, int[] multiples) {
        for (int j : multiples) {
            int multiple = n * j;
            if (!Combinations.isPermutation(n, multiple)) return false;
        }
        return true;
    }
}
