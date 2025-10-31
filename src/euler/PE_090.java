package src.euler;

import java.util.*;

public class PE_090 {
    private static final List<List<Integer>> combinations = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        generate(new ArrayList<>(), 6, 0, 9);
        long count = 0;
        for (int i = 0; i < combinations.size(); i++) {
            List<Integer> c1 = combinations.get(i);
            for (int j = i; j < combinations.size(); j++) {
                List<Integer> c2 = combinations.get(j);
                if (diceWork(c1, c2)) count++;
            }
        }
        return count;
    }

    private static void generate(List<Integer> current, int k, int start, int max) {
        if (current.size() == k) {
            combinations.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i <= max; i++) {
            current.add(i);
            generate(current, k, i+1, max);
            current.removeLast();
        }
    }

    private static boolean diceWork(List<Integer> dice1, List<Integer> dice2) {
        Set<Integer> set1 = new HashSet<>(dice1);
        if (set1.contains(6)) set1.add(9);
        if (set1.contains(9)) set1.add(6);
        Set<Integer> set2 = new HashSet<>(dice2);
        if (set2.contains(6)) set2.add(9);
        if (set2.contains(9)) set2.add(6);
        int[][] squarePairs = {{0, 1}, {0, 4}, {0, 9}, {1, 6}, {2, 5}, {3, 6}, {4, 9}, {6, 4}, {8, 1}};
        for (int[] squarePair : squarePairs) {
            if (!diceWork(set1, set2, squarePair)) return false;
        }
        return true;
    }

    private static boolean diceWork(Set<Integer> dice1, Set<Integer> dice2, int[] squarePair) {
        return (dice1.contains(squarePair[0]) && dice2.contains(squarePair[1])) ||
                (dice1.contains(squarePair[1]) && dice2.contains(squarePair[0]));
    }
}
