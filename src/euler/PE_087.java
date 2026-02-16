package euler;

import utils.Primes;

public class PE_087 {
    private static int[] primes;

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 50_000_000;
        primes = Primes.primes((int) Math.sqrt(limit));
        return powerTripletsCount(limit);
    }

    private static int powerTripletsCount(int limit) {
        boolean[] powerTriplets = new boolean[limit];
        int i2 = 0;
        int p2 = primes[i2];
        int square = p2 * p2;
        int count = 0;

        while (square < limit) {
            int i3 = 0;
            int p3 = primes[i3];
            int cube = p3 * p3 * p3;

            while (square + cube < limit) {
                int i4 = 0;
                int p4 = primes[i4];
                int tetra = p4 * p4 * p4 * p4;
                int result = square + cube + tetra;

                while (result < limit) {
                    if (!powerTriplets[result]) count++;
                    powerTriplets[result] = true;
                    i4++;
                    if (i4 >= primes.length) break;
                    p4 = primes[i4];
                    tetra = p4 * p4 * p4 * p4;
                    result = square + cube + tetra;
                }

                i3++;
                if (i3 >= primes.length) break;
                p3 = primes[i3];
                cube = p3 * p3 * p3;
            }

            i2++;
            if (i2 >= primes.length) break;
            p2 = primes[i2];
            square = p2 * p2;
        }

        return count;
    }
}
