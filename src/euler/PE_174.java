package euler;

public class PE_174 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int tiles = 1_000_000;
        return sumOfTypes(tiles);
    }

    private static long sumOfTypes(int tiles) {
        long[] laminaeCounts = new long[tiles+1];

        int kLimit = (int) (Math.sqrt(tiles) / 2);
        for (int k = 1; k < kLimit; k++) {
            int nStart = 2*k;
            int nEnd = tiles / (4*k) + k;
            for (int n = nStart+1; n <= nEnd; n++) {
                int neededTiles = 4*k*(n-k);
                laminaeCounts[neededTiles]++;
            }
        }

        long count = 0;

        for (int i = 1; i < laminaeCounts.length; i++) {
            if (laminaeCounts[i] > 0 && laminaeCounts[i] <= 10) count++;
        }

        return count;
    }
}
