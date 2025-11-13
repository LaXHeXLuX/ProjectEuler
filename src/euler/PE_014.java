package euler;

public class PE_014 {
    private static boolean[] skip;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        return longestCollatzChainLength(limit);
    }

    private static long longestCollatzChainLength(int limit) {
        long biggestChainLength = 0;
        long biggestI = 0;
        skip = new boolean[limit];
        int start = limit/2 - 1;
        for (int i = start; i < limit; i++) {
            if (skip[i] || (i % 3 == 1 && i % 2 == 0)) continue;
            long chainLength = collatzChainLength(i);
            if (chainLength > biggestChainLength) {
                biggestChainLength = chainLength;
                biggestI = i;
            }
        }
        return biggestI;
    }

    private static int collatzChainLength(int n) {
        int chainLength = 1;
        while (n > 1) {
            if (n < skip.length) skip[n] = true;
            if ((n & 1) == 0) n = n >> 1;
            else {
                n = 3*(n >> 1) + 2;
                chainLength++;
            }
            chainLength++;
        }
        return chainLength;
    }
}
