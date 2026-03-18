package euler;

public class PE_006 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 100;
        return String.valueOf(sumSquareDifference(n));
    }

    private static long sumSquareDifference(long n) {
        long squareSum = n*(n+1)*(2*n+1) / 6;
        long sumSquare = n*(n+1) / 2;
        sumSquare *= sumSquare;
        return sumSquare-squareSum;
    }
}
