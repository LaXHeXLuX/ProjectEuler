public class PE_014 {
    public static void main(String[] args) {
        long biggestChainLength = 0;
        long biggestI = 0;
        for (int i = 1; i < 1_000_000; i++) {
            long chainLength = collatzChainLength(i);
            if (chainLength > biggestChainLength) {
                biggestChainLength = chainLength;
                biggestI = i;
            }
        }
        System.out.println(biggestI);
        System.out.println(biggestChainLength);
    }
    private static long collatzChainLength(long n) {
        if (n == 1) return 1;
        if (n % 2 == 0) n /= 2;
        else n = 3*n+1;
        return 1 + collatzChainLength(n);
    }
}
