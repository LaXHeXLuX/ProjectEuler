package euler;

public class PE_014 {
    private static boolean[] skip;
    private static int[] firstCollatz;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 1_000_000;
        makeInitialCollatz();
        return String.valueOf(longestCollatzChainLength(limit));
    }

    private static void makeInitialCollatz() {
        int limit = 1_000;
        firstCollatz = new int[limit];
        for (int i = 1; i < limit; i++) {
            firstCollatz[i] = collatzLength(i);
        }
    }

    private static long longestCollatzChainLength(int limit) {
        long biggestChainLength = 0;
        long biggestI = 0;
        skip = new boolean[limit];
        for (int i = limit/2; i < limit; i++) {
            if (skip[i] || (i % 6 == 4)) continue;
            long chainLength = collatzChainLength(i);
            if (chainLength > biggestChainLength) {
                biggestChainLength = chainLength;
                biggestI = i;
            }
        }
        return biggestI;
    }

    private static int collatzLength(long n) {
        int c = 1;
        while (n > 1) {
            if ((n & 1) == 0) n = n >> 1;
            else n = 3*n + 1;
            c++;
        }
        return c;
    }

    private static int collatzChainLength(long n) {
        int chainLength = 0;
        while (n >= firstCollatz.length) {
            if (n < skip.length) skip[(int) n] = true;
            int mod = (int) (n & 7);
            switch (mod) {
                case 0 -> {
                    n = n >> 3;
                    chainLength += 3;
                }
                case 1 -> {
                    n = 9*(n >> 3) + 2;
                    chainLength += 5;
                }
                case 2 -> {
                    n = 3*(n >> 3) + 1;
                    chainLength += 4;
                }
                case 3 -> {
                    n = 9*(n >> 3) + 4;
                    chainLength += 5;
                }
                case 4, 5 -> {
                    n = 3*(n >> 3) + 2;
                    chainLength += 4;
                }
                case 6 -> {
                    n = 9*(n >> 3) + 8;
                    chainLength += 5;
                }
                case 7 -> {
                    n = 27*(n >> 3) + 26;
                    chainLength += 6;
                }
            }
        }
        return chainLength + firstCollatz[(int) n];
    }
}
