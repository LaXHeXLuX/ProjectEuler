package euler;

public class PE_110 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 4_000_000;
        return String.valueOf(PE_108.smallestNumberWithSolutionCountAbove(limit));
    }
}
