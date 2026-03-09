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
private static int[] bestSum;
private static Map<Integer, Set<Integer>> reverseLookUp;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[][] matrix = parseMatrix();
        return String.valueOf(maxMatrixSum(matrix));
    }

    private static int maxMatrixSum(int[][] matrix) {
        bestSum = new int[1 << matrix[0].length];
        reverseLookUp = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < matrix[0].length; i++) {
            bestSum[1 << i] = matrix[0][i];
            if (!reverseLookUp.containsKey(matrix[0][i])) {
                reverseLookUp.put(matrix[0][i], new HashSet<>());
            }
            reverseLookUp.get(matrix[0][i]).add(1 << i);
        }
        for (int i = 1; i < matrix.length; i++) {
            bestSumForRow(matrix[i]);
        }

        return bestSum[(1 << matrix.length) - 1];
    }

    private static void bestSumForRow(int[] row) {
        int[] newBestSum = new int[1 << row.length];
        Map<Integer, Set<Integer>> newReverseLookUp = new TreeMap<>(Comparator.reverseOrder());
        for (Integer sum : reverseLookUp.keySet()) {
            for (Integer mask : reverseLookUp.get(sum)) {
                for (int i = 0; i < row.length; i++) {
                    if ((mask & (1 << i)) != 0) continue;
                    int newMask = mask | (1 << i);
                    int newSum = sum + row[i];
                    if (newBestSum[newMask] > 0) {
                        if (newSum <= newBestSum[newMask]) continue;
                        newReverseLookUp.get(newBestSum[newMask]).remove(newMask);
                        if (newReverseLookUp.get(newBestSum[newMask]).isEmpty()) {
                            newReverseLookUp.remove(newBestSum[newMask]);
                        }
                    }
                    newBestSum[newMask] = newSum;
                    if (!newReverseLookUp.containsKey(newSum)) {
                        newReverseLookUp.put(newSum, new HashSet<>());
                    }
                    newReverseLookUp.get(newSum).add(newMask);
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
