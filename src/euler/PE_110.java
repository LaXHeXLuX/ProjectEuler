package euler;

public class PE_110 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 4_000_000;
        return PE_108.smallestNumberWithSolutionCountAbove(limit);
    }
}
