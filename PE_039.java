import util.Converter;

import java.util.ArrayList;
import java.util.List;

public class PE_039 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_001;
        return sumWitMostTriangles(limit);
    }

    private static boolean isTriangle(int a, int b, int c) {
        return a*a + b*b == c*c;
    }

    private static int[][] findAllTrianglesForSum(int sum) {
        List<int[]> triangles = new ArrayList<>();

        for (int a = 1; a < sum/3; a++) {
            for (int b = a+1; b < sum/2; b++) {
                int c = sum - a - b;
                if (isTriangle(a, b, c)) triangles.add(new int[] {a, b, c});
            }
        }

        return Converter.listToArr(triangles, int[].class);
    }

    private static int sumWitMostTriangles(int limit) {
        int mostTriangles = 0;
        int bestSum = 0;

        for (int i = 0; i < limit; i++) {
            int[][] triangles = findAllTrianglesForSum(i);
            if (triangles.length > mostTriangles) {
                mostTriangles = triangles.length;
                bestSum = i;
            }
        }

        return bestSum;
    }
}
