package euler;

public class PE_028 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 1001;
        return String.valueOf(numberSpiralDiagonalSum(limit));
    }

    private static long numberSpiralDiagonalSum(long n) {
        return 1 + 2*(n-1)*n*(n+1)/3 + (n-1)*(n+1)/2 + 2*(n-1);
    }
}
