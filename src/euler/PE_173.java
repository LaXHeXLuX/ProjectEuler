package euler;

public class PE_173 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long L = 1_000_000;
        return String.valueOf(countOfSquareLaminaeWithTiles(L));
    }

    private static long countOfSquareLaminaeWithTiles(long L) {
        long count = 0;

        long nLimit = (long) (Math.sqrt(L - 4) / 2);
        for (long n = 1; n <= nLimit; n++) {
            count += L/(4*n);
        }

        return count - nLimit*(nLimit+1)/2;
    }
}
