package euler;

import java.util.*;

public class PE_103 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 7;
        List<Integer> ssssN = smallestSpecialSumSet(n);
        return setString(ssssN);
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
            if (ssss != null) return ssss;
        }
    }

    private static List<Integer> smallestSpecialSumSet(List<Integer> current, int size, int sum) {
        if (!current.isEmpty() && !isSpecialSumSet(current)) return null;
        if (size == 1) {
            if (current.isEmpty() || sum > current.getLast()) {
                current.add(sum);
                if (isSpecialSumSet(current)) {
                    return current;
                }
                current.removeLast();
            }
            return null;
        }
        int start = 1;
        if (!current.isEmpty()) start += current.getLast();

        int tri = (size-1)*(size) / 2;
        int end = (sum-tri)/size + 1;
        if (current.size() >= 2) {
            int startSum = current.get(0) + current.get(1);
            if (startSum < end) end = startSum;
        }

        for (int i = start; i < end; i++) {
            current.add(i);
            List<Integer> ssss = smallestSpecialSumSet(current, size-1, sum-i);
            if (ssss != null) return ssss;
            current.removeLast();
        }
        return null;
    }

    private static boolean isSpecialSumSet(List<Integer> set) {
        if (set.size() < 3) return true;
        if (set.size() == 3) return set.get(0) + set.get(1) > set.get(2);
        boolean b1 = isSumOrdered(set);
        if (!b1) return false;
        return noDisjointSubSetsWithSameSum(set);
    }

    private static boolean noDisjointSubSetsWithSameSum(List<Integer> set) {
        int n = set.size();
        int x = set.getLast();
        Set<Integer> setSums = new HashSet<>();
        setSums.add(0);
        for (int i = 0; i < n - 1; i++) {
            int el = set.get(i);
            List<Integer> newSums = new ArrayList<>();
            for (int s : setSums) newSums.add(s + el);
            setSums.addAll(newSums);
        }
        Set<Integer> diffs = new HashSet<>();
        for (Integer setSum : setSums) {
            if (diffs.contains(setSum)) {
                return false;
            }
            diffs.add(setSum + x);
            if (setSum > x) diffs.add(setSum - x);
        }
        return true;
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
