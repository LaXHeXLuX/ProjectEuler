package euler;

public class PE_122 {
    // might use this: https://projecteuler.net/thread=122#4237

    private static int[] smallestExpCounts;

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PE() {
        int limit = 200;
        makeSmallestExpCounts2(limit);
        int sum = 0;
        for (int smallestExpCount : smallestExpCounts) sum += smallestExpCount;
        return String.valueOf(sum);
    }

    private static void makeSmallestExpCounts2(int limit) {
        smallestExpCounts = new int[limit+1];
        smallestExpCounts[0] = 0;
        smallestExpCounts[1] = 0;
        smallestExpCounts[2] = 1;
        int biggest = 0;
        for (int n = 3; n <= limit; n++) {
            smallestExpCounts[n] = binaryExpCount(n)+1;
            if (smallestExpCounts[n] > biggest) biggest = smallestExpCounts[n];
        }

        int[] nodePowers = new int[biggest];
        int[] nextLevel = new int[biggest]; // 0 not used
        nodePowers[0] = 1;
        nodePowers[1] = 2;
        nextLevel[1] = 1;

        int n = 1;
        while (true) {
            int i = nextLevel[n];
            if (i < 0) {
                n--;
                if (n == 0) break;
                continue;
            }

            int k = nodePowers[i] + nodePowers[n];
            nextLevel[n]--;

            if (k > limit) continue;
            n++;
            if (smallestExpCounts[k] < n) {
                n--;
                continue;
            }

            smallestExpCounts[k] = n;
            nodePowers[n] = k;
            nextLevel[n] = n;
        }
    }

    private static int binaryExpCount(int n) {
        return 32 - Integer.numberOfLeadingZeros(n) + Integer.bitCount(n) - 2;
    }
}