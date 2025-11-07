package euler;

public class PE_109 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long sum = 0;
        for (int i = 2; i < 100; i++) {
            sum += waysToCheckout(i);
        }
        return sum;
    }

    private static int waysToCheckout(int n) {
        if (n == 1) return 0;
        int count = 0;
        int[] doubles = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 50};
        for (int d : doubles) {
            if (d > n) break;
            count += waysToPreCheckout(n - d);
        }
        return count;
    }

    private static int waysToPreCheckout(int n) {
        if (n == 0) return 1;
        if (n > 120) return 0;
        int[] scores = new int[61];
        for (int i = 1; i <= 20; i++) {
            scores[i]++;
            scores[2*i]++;
            scores[3*i]++;
        }
        scores[25]++; scores[50]++;
        int count = 0;
        if (n < scores.length) count = scores[n];
        int limit = n/2;
        if (n % 2 == 0) count += scores[n/2]*(scores[n/2]+1) / 2;
        else limit += 1;
        for (int i = Math.max(1, n-60); i < limit; i++) {
            if (scores[i] == 0 || scores[n-i] == 0) continue;
            count += scores[i] * scores[n-i];
        }
        return count;
    }
}
