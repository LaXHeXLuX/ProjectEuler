package euler;

public class PE_094 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000_000;
        return perimeterSum(limit);
    }

    private static long perimeterSum(int perimeterLimit) {
        long sum = 0;
        int baseLimit = perimeterLimit / 3 / 2;
        long n;
        for (n = 2; n < baseLimit; n++) { // help here
            if (isSquare(3*n*n + 4*n + 1)) {
                sum += 6*n + 2;
            }
            if (isSquare(3*n*n + 2*n)) {
                sum += 6*n + 4;
            }
        }
        if (isSquare(3*n*n + 4*n + 1)) sum += 6*n + 2;

        return sum;
    }

    private static boolean isSquare(long n2) {
        long n = (long) Math.sqrt(n2);
        return n*n == n2;
    }
}
