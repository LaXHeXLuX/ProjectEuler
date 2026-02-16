package euler;

import utils.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PE_105 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        String filename = "src/euler/inputs/PE_105_sets.txt";
        List<List<Integer>> sets = parse(filename);
        long sum = 0;
        for (List<Integer> set : sets) {
            if (isSpecialSumSet(set)) {
                sum += sum(set);
            }
        }
        return sum;
    }

    private static long sum(List<Integer> set) {
        long sum = 0;
        for (Integer i : set) sum += i;
        return sum;
    }

    private static List<List<Integer>> parse(String filename) {
        int[][] setsArr = Parser.parseManyInts(filename, ",");
        List<List<Integer>> sets = new ArrayList<>();
        for (int[] setArr : setsArr) {
            List<Integer> set = new ArrayList<>();
            for (int i : setArr) {
                set.add(i);
            }
            Collections.sort(set);
            sets.add(set);
        }
        return sets;
    }

    public static boolean noDisjointSubSetsWithSameSum(List<Integer> set) {
        if (set.size() < 3) return true;
        List<Integer> clone = new ArrayList<>(set.subList(0, 2));
        for (int i = 2; i < set.size(); i++) {
            clone.add(set.get(i));
            if (!PE_103.noDisjointSubSetsWithSameSum(clone)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSpecialSumSet(List<Integer> set) {
        return PE_103.isSumOrdered(set) && noDisjointSubSetsWithSameSum(set);
    }
}
