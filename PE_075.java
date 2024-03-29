import java.util.*;

public class PE_075 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int perimeterLimit = 1_500_000;

        int n = 1;
        int[][] rightTriangles = getRightTriangles(perimeterLimit);
        System.out.println("Time for right triangles " + (System.currentTimeMillis()-start) + " ms");
        Map<Integer, int[][]> perimeters = getPerimeters(rightTriangles);
        System.out.println("Time for perimeters " + (System.currentTimeMillis()-start) + " ms");

        int[] oneSolutionPerimeters = ArrayFunctions.mergeSort(getNSolutionPerimeters(n, perimeters));
        System.out.println(oneSolutionPerimeters.length);

        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end-start) + " ms");
    }

    private static int[] getNSolutionPerimeters(int n, Map<Integer, int[][]> perimeters) {
        List<Integer> oneSolutionPerimeters = new ArrayList<>();

        for (Integer integer : perimeters.keySet()) {
            if (perimeters.get(integer).length == n) oneSolutionPerimeters.add(integer);
        }

        if (oneSolutionPerimeters.size() == 0) return new int[0];
        return Converter.listToArr(oneSolutionPerimeters);
    }

    private static int perimeter(int[] triangle) {
        int sum = 0;
        for (int i : triangle) {
            sum += i;
        }
        return sum;
    }

    private static int[][] getRightTriangles(int perimeterLimit) {
        List<int[]> rightTriangles = new ArrayList<>();

        for (int n = 1; n < Math.sqrt(perimeterLimit)/2; n++) {
            int step = (n % 2 == 1) ? 2 : 1;
            for (int m = n+1; 2*m*(m+n) < perimeterLimit; m += step) {
                if (Divisors.greatestCommonDivisor(n, m) != 1) continue;
                for (int k = 1; k <= perimeterLimit/(2*m*(m+n)); k++) {
                    int m2 = m * m;
                    int n2 = n * n;
                    int a = k*(m2 - n2);
                    int b = k*(2*m*n);
                    int c = k*(m2 + n2);
                    int[] triangle = {a, b, c};
                    if (a + b + c > perimeterLimit) System.out.println("ERROR: " + Arrays.toString(triangle) + ". n,m,k: " + Arrays.toString(new int[]{n, m, k}));
                    rightTriangles.add(triangle);
                }
            }
        }
        
        if (rightTriangles.size() == 0) return new int[0][];
        return Converter.listToArr(rightTriangles);
    }

    private static Map<Integer, int[][]> getPerimeters(int[][] rightTriangles) {
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

        Map<Integer, int[][]> betterPerimeters = new HashMap<>();
        for (Integer integer : perimeters.keySet()) {
            betterPerimeters.put(integer, Converter.listToArr(perimeters.get(integer)));
        }
        return betterPerimeters;
    }
}
