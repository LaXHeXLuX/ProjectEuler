package euler;

public class PE_120 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000;
        return rMaxSum(limit);
    }

    private static long rMaxSum(int limit) {
        long sum = 0;
        for (int a = 3; a <= limit; a++) {
            sum += rMax(a);
        }
        return sum;
    }

    private static int rMax(int a) {
        return a*a - (2 - a%2) * a;
    }
}
