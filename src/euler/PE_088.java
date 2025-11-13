package euler;

import java.util.*;

public class PE_088 {
    private static int[] bestScore;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 12_000;
        bestScore = new int[limit+1];
        for (int i = 2; i < bestScore.length; i++) {
            bestScore[i] = 2*i;
        }
        fillBestScores();
        return setSum();
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
        fillBestScores(new ArrayList<>(), 1, 0);
    }

    private static void fillBestScores(List<Integer> factors, int product, int sum) {
        int limit = (bestScore.length-1)*2 / product;
        int start;
        if (!factors.isEmpty()) start = factors.getLast();
        else start = 2;
        for (int i = start; i <= limit; i++) {
            factors.add(i);
            int newProduct = product * i;
            int newSum = sum + i;
            // 2 3. * => 6, + => 5
            int size = newProduct - newSum + factors.size();
            if (size < bestScore.length && bestScore[size] > newProduct) {
                bestScore[size] = newProduct;
            }
            fillBestScores(factors, newProduct, newSum);
            factors.removeLast();
        }
    }
}
