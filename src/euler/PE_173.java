package euler;

public class PE_173 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long tiles = 1_000_000;
        return countOfSquareLaminaeWithTiles(tiles);
    }

    private static long countOfSquareLaminaeWithTiles(long tiles) {
        long count = 0;

        long kLimit = (long) (Math.sqrt(tiles) / 2);
        for (long k = 1; k < kLimit; k++) {
            long nStart = 2*k;
            long nEnd = tiles / (4*k) + k;
            long diff = nEnd - nStart;
            if (diff < 0) continue;
            count += diff;
        }

        return count;
    }
}
