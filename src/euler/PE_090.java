package euler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PE_090 {
    private static final List<Set<Integer>> combinations = new ArrayList<>();

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        generate(new ArrayList<>(), 6, 0, 9);
        long count = 0;
        for (int i = 0; i < combinations.size(); i++) {
            Set<Integer> set1 = combinations.get(i);
            for (int j = i; j < combinations.size(); j++) {
                Set<Integer> set2 = combinations.get(j);
                if (diceWork(set1, set2)) count++;
            }
        }
        return String.valueOf(count);
    }

    private static void generate(List<Integer> current, int k, int start, int max) {
        if (current.size() == k) {
            combinations.add(new HashSet<>(current));
            return;
        }
        for (int i = start; i <= max; i++) {
            current.add(i);
            generate(current, k, i+1, max);
            current.removeLast();
        }
    }

    private static boolean diceWork(Set<Integer> set1, Set<Integer> set2) {
        if (set1.contains(6)) set1.add(9);
        if (set1.contains(9)) set1.add(6);
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
