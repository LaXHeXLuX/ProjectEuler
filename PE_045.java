public class PE_045 {
    public static void main(String[] args) {
        int[] sides = {3, 5, 6};
        findNumbersPolygonalIn(sides);
    }

    private static void findNumbersPolygonalIn(int[] sides) {
        sides = ArrayFunctions.mergeSort(sides);

        int i = 1;
        long maxPolygonal;
        while (true) {
            maxPolygonal = PolygonalNumber.polygonalNumberLong(sides[sides.length-1], i);
            if (maxPolygonal <= 0) break;
            boolean isPolygonal = true;

            for (int j = sides.length-2; j >= 0; j--) {
                if (!PolygonalNumber.isPolygonalNumber(sides[j], maxPolygonal)) {
                    isPolygonal = false;
                    break;
                }
            }

            if (isPolygonal) {
                System.out.println(maxPolygonal);
            }
            i++;
        }
    }
}
