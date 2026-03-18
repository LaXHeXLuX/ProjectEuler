package euler;

public class PE_018 {
    public static final String INPUT = """
                75
                95 64
                17 47 82
                18 35 87 10
                20 04 82 47 65
                19 01 23 75 03 34
                88 02 77 73 07 63 67
                99 65 04 28 06 16 70 92
                41 41 26 56 83 40 80 70 33
                41 48 72 33 47 32 37 16 94 29
                53 71 44 65 25 43 91 52 97 51 14
                70 11 33 28 77 73 17 78 39 68 17 57
                91 71 52 38 17 14 91 43 58 50 27 29 48
                63 66 04 68 89 53 67 30 73 16 69 87 40 31
                04 62 98 27 23 09 70 98 73 93 38 53 60 04 23""";

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[][] triangle = parse();
        return String.valueOf(maximumSum(triangle));
    }

    private static int[][] parse() {
        String[] triangleRows = PE_018.INPUT.split("\n");
        String[][] triangleCells = new String[triangleRows.length][];
        for (int i = 0; i < triangleRows.length; i++) {
            triangleCells[i] = triangleRows[i].split(" ");
        }
        int[][] triangleNumbers = new int[triangleCells.length][];
        for (int i = 0; i < triangleCells.length; i++) {
            triangleNumbers[i] = new int[triangleCells[i].length];
            for (int j = 0; j < triangleCells[i].length; j++) {
                triangleNumbers[i][j] = Integer.parseInt(triangleCells[i][j]);
            }
        }
        return triangleNumbers;
    }

    private static int maximumSum(int[][] triangle) {
        for (int i = triangle.length-2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                int v1 = triangle[i+1][j];
                int v2 = triangle[i+1][j+1];
                triangle[i][j] += Math.max(v1, v2);
            }
        }
        return triangle[0][0];
    }
}