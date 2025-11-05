package euler;

import java.util.*;

public class PE_103 {
    private static Map<Integer, List<List<Integer>>> setSums = new HashMap<>();

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int n = 7;
        return setString(smallestSpecialSumSet2(n));
    }

    private static long setString(List<Integer> set) {
        StringBuilder s = new StringBuilder();
        for (Integer i : set) {
            s.append(i.toString());
        }
        return Long.parseLong(s.toString());
    }

    private static int sum(List<Integer> set) {
        int sum = 0;
        for (Integer i : set) sum += i;
        return sum;
    }

    private static List<Integer> smallestSpecialSumSet2(int size) {
        int minSum = sum(smallestSpecialSumSet(size-1, 1));
        return smallestSpecialSumSet(size, minSum);
    }

    private static List<Integer> smallestSpecialSumSet(int size, int minSum) {
        for (int sum = minSum; true; sum++) {
            List<Integer> ssss = smallestSpecialSumSet(new ArrayList<>(), size, sum);
            if (!ssss.isEmpty()) return ssss;
        }
    }

    private static List<Integer> smallestSpecialSumSet(List<Integer> current, int size, int sum) {
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
        if (!current.isEmpty() && !isSpecialSumSet(current)) return new ArrayList<>();
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
        boolean b1 = isSumOrdered(set);
        if (!b1) return false;
        return noDisjointSubSetsWithSameSum(set);
    }

    private static boolean noDisjointSubSetsWithSameSum(List<Integer> set) {
        setSums = new HashMap<>();
        return noDisjointSubSetsWithSameSum(set, new ArrayList<>(), 0, 0);
    }

    private static boolean noDisjointSubSetsWithSameSum(List<Integer> set, List<Integer> current, int sum, int index) {
        if (index == set.size()) {
            if (current.isEmpty()) return true;
            if (!setSums.containsKey(sum)) {
                setSums.put(sum, new ArrayList<>());
                setSums.get(sum).add(List.copyOf(current));
                return true;
            }
            return false;
        }
        int el = set.get(index);
        index++;
        if (!noDisjointSubSetsWithSameSum(set, current, sum, index)) return false;
        current.add(el);
        if (!noDisjointSubSetsWithSameSum(set, current, sum + el, index)) return false;
        current.removeLast();
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
