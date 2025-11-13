package euler;

public class PE_039 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_001;
        return sumWithMostTriangles(limit);
    }

    private static boolean isTriangle(int a, int b, int c) {
        return a*a + b*b == c*c;
    }

    private static int triangleCountForPerimeter(int perimeter) {
        int count = 0;

        for (int a = 1; a < perimeter /3; a++) {
            for (int b = a+1; b < perimeter /2; b++) {
                int c = perimeter - a - b;
                if (isTriangle(a, b, c)) count++;
            }
        }

        return count;
    }

    private static int sumWithMostTriangles(int limit) {
        int mostTriangles = 0;
        int bestSum = 0;

        for (int i = 0; i < limit; i+=2) {
            int count = triangleCountForPerimeter(i);
            if (count > mostTriangles) {
                mostTriangles = count;
                bestSum = i;
            }
        }

        return bestSum;
    }
}
