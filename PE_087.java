import java.util.Arrays;

public class PE_087 {
    private static int[] primes;
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int limit = 50_000_000;
        primes = makePrimes(limit);

        System.out.println(Arrays.toString(primes));

        int[] powerTriplets = getPowerTriplets(limit);
        System.out.println(powerTriplets.length);

        long end = System.currentTimeMillis();
        System.out.println(STR."Time: \{end - start} ms");
    }

    private static int[] makePrimes(int limit) {
        boolean[] primes = Primes.sieveOfPrimes((int) Math.sqrt(limit));
        return Converter.booleanArrToIntArr(primes);
    }

    private static int[] getPowerTriplets(int limit) {
        boolean[] powerTriplets = new boolean[limit];
        int i2 = 0;
        int square = primes[i2] * primes[i2];

        while (square < limit) {
            int i3 = 0;
            int cube = primes[i3] * primes[i3] * primes[i3];

            while (square + cube < limit) {
                int i4 = 0;
                int tetra = primes[i4] * primes[i4] * primes[i4] * primes[i4];
                int result = square + cube + tetra;

                while (result < limit) {
                    powerTriplets[result] = true;
                    i4++;
                    if (i4 >= primes.length) break;
                    tetra = primes[i4] * primes[i4] * primes[i4] * primes[i4];
                    result = square + cube + tetra;
                }

                i3++;
                if (i3 >= primes.length) break;
                cube = primes[i3] * primes[i3] * primes[i3];
            }

            i2++;
            if (i2 >= primes.length) break;
            square = primes[i2] * primes[i2];
        }

        return Converter.booleanArrToIntArr(powerTriplets);
    }
}
