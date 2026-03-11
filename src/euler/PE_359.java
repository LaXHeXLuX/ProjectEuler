package euler;

import utils.Diophantine;
import utils.Primes;

import java.util.Arrays;

public class PE_359 {
    private static int[][] hotel;

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PE() {
        long fr = 71328803586048L;
        double s = System.currentTimeMillis();
        Primes.PF[] pfs = Primes.primeFactors(fr);
        double e = System.currentTimeMillis();
        System.out.println("pfs: " + (e - s) + " ms");
        System.out.println(Arrays.toString(pfs));


        int n = 100;
        populateHotel(n);
        for (int i = 1; i <= hotel[0][0]; i++) {
            System.out.print(i + ":\t[");
            int[] floor = hotel[i];
            for (int j = 1; j < floor[0]; j++) {
                System.out.print(floor[j] + ", ");
            }
            System.out.println(floor[floor[0]] + "]");
        }

        System.out.println();
        System.out.println(P(1, 1));
        System.out.println(P(1, 2));
        System.out.println(P(2, 1));
        System.out.println(P(10, 20));
        System.out.println(P(25, 75));
        System.out.println(P(99, 100));
        return null;
    }

    private static void populateHotel(int n) {
        hotel = new int[n + 1][n + 1];
        hotel[1][1] = 1;
        hotel[0][0] = 1;
        hotel[1][0] = 1;
        for (int i = 2; i <= n; i++) {
            // assign room
            int floor = floor(i);
            int room = hotel[floor][0]+1;
            if (floor > hotel[0][0]) {
                hotel[0][0]++;
            }
            hotel[floor][0]++;
            //System.out.println("guest " + i + " at " + floor + "-" + room);
            hotel[floor][room] = i;
        }
    }

    private static long P(long f, long r) {
        if (f == 1) return r*(r+1)/2;
        long startStep1 = 1;
        if (f % 2 == 0) startStep1 = 2*f + 1;
        long startStep2 = 2*f;
        if (f % 2 == 0) startStep2 = 2;
        long p = f*f / 2;
        if (f % 2 == 0) p = (f-1)*(f-1) / 2 + f;
        for (int i = 1; i < r; i++) {
            if (i % 2 == 1) p += startStep1;
            else p += startStep2;
        }
        return p;
    }

    private static int floor(int n) {
        for (int floor = 1; floor <= hotel[0][0]; floor++) {
            int last = hotel[floor][hotel[floor][0]];
            if (Diophantine.root(last + n) > 0) return floor;
        }
        return hotel[0][0]+1;
    }
}