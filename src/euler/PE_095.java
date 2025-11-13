package euler;

import java.util.*;

public class PE_095 {
    private static int[] divisorSums;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        makeDivisorSums(limit);
        List<Integer> longestChain = longestChain();
        return Collections.min(longestChain);
    }

    private static void makeDivisorSums(int limit) {
        divisorSums = new int[limit+1];
        Arrays.fill(divisorSums, 1);
        for (int i = 2; i < divisorSums.length/2; i++) {
            int temp = i*2;
            while (temp < divisorSums.length) {
                divisorSums[temp] += i;
                temp += i;
            }
        }
    }

    private static List<Integer> longestChain() {
        List<Integer> longestChain = List.of();
        int limit = divisorSums.length;
        int[] visited = new int[limit];
        int[] chain = new int[limit];
        for (int i = 2; i < limit; i++) {
            int chainLen = 0;
            int temp = i;

            while (temp < limit && visited[temp] == 0) {
                chain[chainLen] = temp;
                chainLen++;
                visited[temp] = i;
                temp = divisorSums[temp];
            }

            if (temp >= limit || visited[temp] != i) continue;

            int start = 0;
            while (chain[start] != temp) start++;
            if (chainLen - start > longestChain.size()) {
                longestChain = new ArrayList<>();
                for (int j = start; j < chainLen; j++) {
                    longestChain.add(chain[j]);
                }
            }
        }
        return longestChain;
    }
}
