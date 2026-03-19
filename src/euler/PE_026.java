package euler;

public class PE_026 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 1000;
        return String.valueOf(longestReciprocalCycleUnder(limit));
    }

    private static int longestReciprocalCycleUnder(int limit) {
        int longest = 0;
        int longestNumber = 0;
        for (int i = 2; i < limit; i++) {
            int reciprocalCycleLength = cycleLength(i);
            if (reciprocalCycleLength > longest) {
                longest = reciprocalCycleLength;
                longestNumber = i;
            }
        }
        return longestNumber;
    }

    private static int cycleLength(int n) {
        while (n % 2 == 0) n /= 2;
        while (n % 5 == 0) n /= 5;
        if (n == 1) return 0;

        int remainder = 1;
        int length = 0;
        do {
            remainder = (remainder * 10) % n;
            length++;
        } while (remainder != 1);
        return length;
    }
}
