import util.Converter;
import util.Divisors;

import java.util.*;

public class PE_075 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int perimeterLimit = 1_500_000;

        int n = 1;
        List<int[]> rightTriangles = getRightTriangles(perimeterLimit);
        Map<Integer, List<int[]>> perimeters = getPerimeters(rightTriangles);

        int[] oneSolutionPerimeters = getNSolutionPerimeters(n, perimeters);
        return oneSolutionPerimeters.length;
    }

    private static int[] getNSolutionPerimeters(int n, Map<Integer, List<int[]>> perimeters) {
        List<Integer> oneSolutionPerimeters = new ArrayList<>();

        for (Integer integer : perimeters.keySet()) {
            if (perimeters.get(integer).size() == n) oneSolutionPerimeters.add(integer);
        }

        if (oneSolutionPerimeters.isEmpty()) return new int[0];
        return Converter.listToArr(oneSolutionPerimeters);
    }

    private static int perimeter(int[] triangle) {
        int sum = 0;
        for (int i : triangle) {
            sum += i;
        }
        return sum;
    }

    private static List<int[]> getRightTriangles(int perimeterLimit) {
        List<int[]> rightTriangles = new ArrayList<>();

        for (int n = 1; n < Math.sqrt(perimeterLimit)/2; n++) {
            int step = n % 2 + 1;
            for (int m = n+1; 2*m*(m+n) < perimeterLimit; m += step) {
                if (Divisors.greatestCommonDivisor(n, m) != 1) continue;
                for (int k = 1; k <= perimeterLimit/(2*m*(m+n)); k++) {
                    int m2 = m * m;
                    int n2 = n * n;
                    int a = k*(m2 - n2);
                    int b = k*(2*m*n);
                    int c = k*(m2 + n2);
                    int[] triangle = {a, b, c};
                    rightTriangles.add(triangle);
                }
            }
        }

        return rightTriangles;
    }

    private static Map<Integer, List<int[]>> getPerimeters(List<int[]> rightTriangles) {
        Map<Integer, List<int[]>> perimeters = new HashMap<>();

        for (int[] rightTriangle : rightTriangles) {
            int perimeter = perimeter(rightTriangle);
            if (!perimeters.containsKey(perimeter)) {
                List<int[]> triangles = new ArrayList<>();
                triangles.add(rightTriangle);
                perimeters.put(perimeter, triangles);
            }
            else perimeters.get(perimeter).add(rightTriangle);
        }

        Map<Integer, List<int[]>> betterPerimeters = new HashMap<>();
        for (Integer integer : perimeters.keySet()) {
            betterPerimeters.put(integer, perimeters.get(integer));
        }
        return betterPerimeters;
    }
}
