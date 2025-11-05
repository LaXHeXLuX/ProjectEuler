package euler;

import java.util.*;

public class PE_103 {
    private static Set<Integer> setSums;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int n = 7;
        for (int i = 1; i < n; i++) {
            System.out.println(i + ": " + smallestSpecialSumSet(i));
        }
        return setString(smallestSpecialSumSet(n));
    }

    private static long setString(List<Integer> set) {
        StringBuilder s = new StringBuilder();
        for (Integer i : set) {
            s.append(i.toString());
        }
        return Long.parseLong(s.toString());
    }

    private static List<Integer> smallestSpecialSumSet(int size) {
        for (int sum = 1; true; sum++) {
            List<Integer> ssss = smallestSpecialSumSet(new ArrayList<>(), size, sum);
            if (!ssss.isEmpty()) return ssss;
        }
    }

    private static List<Integer> smallestSpecialSumSet(List<Integer> current, int size, int sum) {
        if (!current.isEmpty() && !isSpecialSumSet(current)) return new ArrayList<>();
        if (size == 1) {
            if (current.isEmpty() || sum > current.getLast()) {
                List<Integer> copy = new ArrayList<>(current);
                copy.add(sum);
                if (isSpecialSumSet(copy)) {
                    return copy;
                }
            }
            return new ArrayList<>();
        }
        int start = 1;
        if (!current.isEmpty()) start += current.getLast();
        int end = sum/size+1;
        if (current.size() >= 2) {
            int startSum = current.get(0) + current.get(1);
            if (startSum < end) end = startSum;
        }
        for (int i = start; i < end; i++) {
            current.add(i);
            List<Integer> ssss = smallestSpecialSumSet(current, size-1, sum-i);
            current.removeLast();
            if (!ssss.isEmpty()) return ssss;
        }
        return new ArrayList<>();
    }

    private static boolean isSpecialSumSet(List<Integer> set) {
        if (set.size() < 3) return true;
        boolean b1 = isSumOrdered(set);
        if (!b1) return false;
        return noDisjointSubSetsWithSameSum(set);
    }

    private static boolean noDisjointSubSetsWithSameSum(List<Integer> set) {
        setSums = new HashSet<>();
        makeSetSums(set.subList(0, set.size()-1), 0, 0);
        int x = set.getLast();
        Set<Integer> diffs = new HashSet<>();
        for (Integer setSum : setSums) {
            if (x == setSum || diffs.contains(setSum)) {
                return false;
            }
            diffs.add(setSum + x);
            if (setSum > x) diffs.add(setSum - x);
        }
        return true;
    }

    private static void makeSetSums(List<Integer> set, int sum, int index) {
        if (index == set.size()) {
            if (sum > 0) setSums.add(sum);
            setSums.add(sum);
            return;
        }
        int el = set.get(index);
        index++;
        makeSetSums(set, sum, index);
        makeSetSums(set, sum+el, index);
    }

    private static boolean isSumOrdered(List<Integer> set) {
        int n = set.size();
        int forwardSums = set.getFirst();
        int backwardSums = 0;
        for (int i = 1; i < n; i++) {
            forwardSums += set.get(i);
            backwardSums += set.get(n-i);
            if (forwardSums < backwardSums) return false;
        }
        return true;
    }
}
