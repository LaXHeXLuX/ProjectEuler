package euler;

public class PE_407 {
    private static boolean[] skip;
    private static int[] biggestPF;

    void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int limit = 10_000_000;
        double s = System.currentTimeMillis();
        makeSkip(limit+1);
        double e = System.currentTimeMillis();
        System.out.println("prep " + (e-s) + " ms");
        return String.valueOf(sum(limit));
    }

    private static void makeSkip(int limit) {
        skip = new boolean[limit];
        biggestPF = new int[limit];
        boolean[] composites = new boolean[limit];
        for (int i = 4; i < composites.length; i+=2) {
            composites[i] = true;
        }
        for (int i = 2; i < skip.length; i*=2) {
            skip[i] = true;
        }
        int i;
        for (i = 3; i <= skip.length/2; i+=2) {
            if (composites[i]) continue;
            skip[i] = true;
            long k = (long) i * i;
            while (k < limit) {
                skip[Math.toIntExact(k)] = true;
                k *= i;
            }
            k = i;
            while (k < (long) i * i && k < limit) {
                biggestPF[(int) k] = i;
                k += i;
            }
            while (k < limit) {
                composites[(int) k] = true;
                biggestPF[(int) k] = i;
                k += i;
            }
        }

        for (; i < skip.length; i+=2) {
            if (!composites[i]) skip[i] = true;
        }
    }

    private static long sum(int limit) {
        long sum = 0;
        for (int i = 2; i <= limit; i++) {
            if (skip[i]) {
                sum += 1;
                continue;
            }
            sum += M(i);
        }
        return sum;
    }

    private static long M(int n) {
        for (long a = n - biggestPF[n] + 1; true; a-=biggestPF[n]) {
            if ((a*a) % n == a) return a;
            if (((a-1)*(a-1)) % n == a-1) return a-1;
        }
    }
}
