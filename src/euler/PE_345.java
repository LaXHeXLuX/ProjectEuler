package euler;

import java.util.*;

public class PE_345 {
    private static final String input = """
              7  53 183 439 863 497 383 563  79 973 287  63 343 169 583
            627 343 773 959 943 767 473 103 699 303 957 703 583 639 913
            447 283 463  29  23 487 463 993 119 883 327 493 423 159 743
            217 623   3 399 853 407 103 983  89 463 290 516 212 462 350
            960 376 682 962 300 780 486 502 912 800 250 346 172 812 350
            870 456 192 162 593 473 915  45 989 873 823 965 425 329 803
            973 965 905 919 133 673 665 235 509 613 673 815 165 992 326
            322 148 972 962 286 255 941 541 265 323 925 281 601  95 973
            445 721  11 525 473  65 511 164 138 672  18 428 154 448 848
            414 456 310 312 798 104 566 520 302 248 694 976 430 392 198
            184 829 373 181 631 101 969 613 840 740 778 458 284 760 390
            821 461 843 513  17 901 711 993 293 157 274  94 192 156 574
             34 124   4 878 450 476 712 914 838 669 875 299 823 329 699
            815 559 813 459 522 788 168 586 966 232 308 833 251 631 107
            813 883 451 509 615  77 281 613 459 205 380 274 302  35 805"""; // 3 0 1 2
private static Map<List<Integer>, Integer> bestSum;
private static Map<Integer, Set<List<Integer>>> reverseLookUp;

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int[][] matrix = parseMatrix();
        return String.valueOf(maxMatrixSum(matrix));
    }

    private static int maxMatrixSum(int[][] matrix) {
        bestSum = new HashMap<>();
        reverseLookUp = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < matrix[0].length; i++) {
            bestSum.put(List.of(i), matrix[0][i]);
            if (!reverseLookUp.containsKey(matrix[0][i])) {
                reverseLookUp.put(matrix[0][i], new HashSet<>());
            }
            reverseLookUp.get(matrix[0][i]).add(List.of(i));
        }
        for (int i = 1; i < matrix.length; i++) bestSumForRow(matrix[i]);

        List<Integer> finalList = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) finalList.add(i);
        return bestSum.get(finalList);
    }

    private static void bestSumForRow(int[] row) {
        Map<List<Integer>, Integer> newBestSum = new HashMap<>();
        Map<Integer, Set<List<Integer>>> newReverseLookUp = new TreeMap<>(Comparator.reverseOrder());
        for (Integer sum : reverseLookUp.keySet()) {
            for (List<Integer> list : reverseLookUp.get(sum)) {
                boolean[] used = new boolean[row.length];
                for (Integer integer : list) used[integer] = true;
                for (int i = 0; i < row.length; i++) {
                    if (used[i]) continue;
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(i);
                    Collections.sort(newList);
                    int newSum = sum + row[i];
                    if (newBestSum.containsKey(newList)) {
                        if (newSum <= newBestSum.get(newList)) continue;
                        newReverseLookUp.get(newBestSum.get(newList)).remove(newList);
                        if (newReverseLookUp.get(newBestSum.get(newList)).isEmpty()) {
                            newReverseLookUp.remove(newBestSum.get(newList));
                        }
                    }
                    newBestSum.put(newList, newSum);
                    if (!newReverseLookUp.containsKey(newSum)) {
                        newReverseLookUp.put(newSum, new HashSet<>());
                    }
                    newReverseLookUp.get(newSum).add(newList);
                }
            }
        }

        bestSum = newBestSum;
        reverseLookUp = newReverseLookUp;
    }

    private static int[][] parseMatrix() {
        String[] rows = input.split("\n");
        int[][] matrix = new int[rows.length][];
        for (int i = 0; i < matrix.length; i++) {
            String[] values = rows[i].strip().split(" +");
            matrix[i] = new int[values.length];
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Integer.parseInt(values[j]);
            }
        }
        return matrix;
    }
}
