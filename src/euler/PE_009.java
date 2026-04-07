package euler;

import utils.Diophantine;
import utils.Divisors;

public class PE_009 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int sum = 1000;
        int[] specialTriplet = pythagoreanTripletWithSumOf(sum);
        return String.valueOf((long) specialTriplet[0] * specialTriplet[1] * specialTriplet[2]);
    }

    private static int[] pythagoreanTripletWithSumOf(int sum) {
        if (sum % 2 != 0) return new int[0];
        sum /= 2;
        int[] divs = Divisors.divisors(sum);
        for (int k : divs) {
            int mmPlusMn = sum / k;
            int mStart = (int) Math.sqrt(mmPlusMn / 2.0);
            if (mStart % 2 != mmPlusMn % 2) mStart++;
            int mLimit = (int) Math.sqrt(mmPlusMn - 1);
            for (int m = mStart; m <= mLimit; m+=2) {
                if (mmPlusMn % m != 0) continue;
                int n = mmPlusMn / m - m;
                if (Diophantine.gcd(m, n) > 1) continue;
                return new int[] {k*(m*m - n*n), k*2*m*n, k*(m*m + n*n)};
            }
        }
        return new int[0];
    }
}
