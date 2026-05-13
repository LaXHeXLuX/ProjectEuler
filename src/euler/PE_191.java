package euler;

public class PE_191 {
    private static long[][] memo;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int days = 30;
        return String.valueOf(prizeStringCount(days));
    }

    private static long prizeStringCount(int days) {
        memo = new long[days+1][days+1];
        return prizeStringCount(days, 0);
    }

    private static long prizeStringCount(int days, int O) {
        if (days <= 2) {
            return (long) days * days + (1L << days) * (O+1);
        }
        if (memo[days][O] > 0) return memo[days][O];
        long sum =
                prizeStringCount(days-1, O+1) +
                prizeStringCount(days-2, O+1) +
                prizeStringCount(days-3, O+1);
        memo[days][O] = sum;
        return sum;
    }
}
