package euler;

public class PE_014 {
    private static boolean[] skip;
    private static int limit;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long biggestChainLength = 0;
        long biggestI = 0;
        limit = 1_000_000;
        skip = new boolean[limit];
        for (int i = 1; i < limit; i++) {
            if (skip[i]) continue;
            long chainLength = collatzChainLength(i);
            if (chainLength > biggestChainLength) {
                biggestChainLength = chainLength;
                biggestI = i;
            }
        }
        return biggestI;
    }

    private static int collatzChainLength(long n) {
        int chainLength = 1;
        while (n > 1) {
            if (n < limit) skip[Math.toIntExact(n)] = true;
            if (n % 2 == 0) n /= 2;
            else n = 3*n+1;
            chainLength++;
        }
        return chainLength;
    }
}
