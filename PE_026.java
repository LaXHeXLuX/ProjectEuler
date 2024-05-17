import java.util.Arrays;

public class PE_026 {
    public static void main(String[] args) {
        int limit = 1001;
        System.out.println(longestReciprocalCycleUnder(limit));
    }

    private static int longestReciprocalCycleUnder(int limit) {
        int longest = 0;
        int longestNumber = 0;
        for (int i = 2; i < limit; i++) {
            int a = 1;
            LongFraction fraction = new LongFraction(a, i);
            int[][] reciprocal = fraction.getCycle();
            if (reciprocal[1].length > longest) {
                longest = reciprocal[1].length;
                longestNumber = i;
                System.out.println(i + ", " + longest + ": " + Arrays.deepToString(reciprocal));
            }
        }
        return longestNumber;
    }
}
