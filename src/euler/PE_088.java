package euler;

public class PE_088 {
    private static int[] factors;
    private static int[] bestScore;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 12_000;
        bestScore = new int[limit+1];
        for (int i = 2; i < bestScore.length; i++) {
            bestScore[i] = 2*i;
        }
        factors = new int[limit+1];
        fillBestScores();
        return String.valueOf(setSum());
    }

    private static long setSum() {
        boolean[] skip = new boolean[bestScore.length*2];
        long sum = 0;
        for (int i : bestScore) {
            if (skip[i]) continue;
            sum += i;
            skip[i] = true;
        }
        return sum;
    }

    private static void fillBestScores() {
        fillBestScores(0, 1, 0);
    }

    private static void fillBestScores(int length, int product, int sum) {
        int limit = (bestScore.length-1)*2 / product;
        int start = 2;
        if (length > 0) start = factors[length -1];
        for (int i = start; i <= limit; i++) {
            factors[length++] = i;
            int newProduct = product * i;
            int newSum = sum + i;
            int size = newProduct - newSum + length;
            if (size < bestScore.length && bestScore[size] > newProduct) {
                bestScore[size] = newProduct;
            }
            fillBestScores(length, newProduct, newSum);
            length--;
        }
    }
}
