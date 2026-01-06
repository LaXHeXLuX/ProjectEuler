package euler;

import java.util.ArrayList;
import java.util.List;

public class PE_135 {
    private static int[] solutions;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        int target = 10;
        List<Integer> solutions = sameDifferenceSolutions(target, limit);
        return solutions.size();
    }

    private static List<Integer> sameDifferenceSolutions(int target, int limit) {
        sieveSolutions(limit);
        List<Integer> sameDifferenceSolutions = new ArrayList<>();
        for (int i = 1; i < limit; i++) {
            if (solutions[i] == target) sameDifferenceSolutions.add(i);
        }
        return sameDifferenceSolutions;
    }

    private static void sieveSolutions(int limit) {
        solutions = new int[limit];
        int sqrt = (int) Math.sqrt(limit);
        for (int i = 1; i < sqrt; i++) {
            int jLimit = (limit-1) / i;
            int jStart;
            if (i % 2 == 0) {
                solutions[i*i]++;
                jStart = i + 4;
            }
            else jStart = i + 2;
            for (int j = jStart; j <= jLimit; j+=4) {
                solutions[i*j] += solutionCount(i, j);
            }
        }
    }

    private static int solutionCount(int i, int j) {
        if (3*i > j) return 2;
        return 1;
    }
}
