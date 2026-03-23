package euler;

import utils.Combinations;
import utils.Diophantine;

public class PE_052 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[] multiples = {2, 3, 4, 5, 6};
        return String.valueOf(smallestNumberWithPermutedMultiples2(multiples));
    }

    private static int smallestNumberWithPermutedMultiples2(int[] multiples) {
        int limit = 10/(multiples[multiples.length-1]+1);

        for (int digits = 1; digits < 10; digits++) {
            int pow10 = (int) Diophantine.pow10[digits];
            for (int first = 1; first <= limit; first++) {
                for (int i = 0; i < pow10; i++) {
                    int x = pow10*first + i;
                    if (hasPermutedMultiples(x, multiples)) return x;
                }
            }
        }

        return -1;
    }

    private static boolean hasPermutedMultiples(int n, int[] multiples) {
        for (int j : multiples) {
            int multiple = n * j;
            if (!Combinations.isPermutation(n, multiple)) return false;
        }
        return true;
    }
}
