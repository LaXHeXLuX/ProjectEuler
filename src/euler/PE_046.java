package euler;

public class PE_046 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        return String.valueOf(smallestConjectureContradiction());
    }

    private static int smallestConjectureContradiction() {
        int limit = 1_000;
        int result = smallestConjectureContradiction2(limit);
        while (result == -1) {
            limit *= 10;
            result = smallestConjectureContradiction2(limit);
        }
        return result;
    }

    private static int smallestConjectureContradiction2(int limit) {
        boolean[] composites = new boolean[limit >> 1];
        boolean[] canBeWritten = new boolean[limit >> 1];
        composites[0] = true;

        for (int i = 1; i < limit >> 1; i++) {
            if (composites[i] && !canBeWritten[i]) return (i << 1) | 1;
            if (composites[i]) continue;
            long p = (i << 1) | 1;
            long k = p * p;
            while (k < limit) {
                composites[((int) k) >> 1] = true;
                k += p << 1;
            }

            long sq = 1;
            k = p + ((sq*sq) << 1);
            while (k < limit) {
                canBeWritten[(int) (k >> 1)] = true;
                sq++;
                k = p + ((sq*sq) << 1);
            }
        }

        return -1;
    }
}
