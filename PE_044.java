import UsefulFunctions.PolygonalNumber;

public class PE_044 {
    public static void main(String[] args) {
        int n = 5;

        long start = System.currentTimeMillis();

        System.out.println(findSmallestDifferenceWithProperty(n));

        long end = System.currentTimeMillis();
        System.out.println("AEG: " + (end-start) + " ms");
    }

    private static long findSmallestDifferenceWithProperty(int sides) {
        long differenceIndex = 1;
        long difference = PolygonalNumber.polygonalNumberLong(sides, differenceIndex);

        while (difference > 0 && differenceIndex > 0) {
            long starterIndex = 1;
            long Pa = PolygonalNumber.polygonalNumberLong(sides, starterIndex);
            long previousPa = 1;

            while (difference > (Pa - previousPa)) {
                if (everythingWorks(Pa, difference, sides)) {
                    System.out.println("Difference: " + difference);
                    System.out.println("Pa: " + Pa);
                    System.out.println("Pb: " + (Pa+difference));
                    System.out.println("Pc: " + (2*Pa+difference));
                    System.out.println();
                    return difference;
                }

                starterIndex++;
                previousPa = Pa;
                Pa = PolygonalNumber.polygonalNumberLong(sides, starterIndex);
            }

            differenceIndex++;
            difference = PolygonalNumber.polygonalNumberLong(sides, differenceIndex);
        }

        return -1;
    }

    private static boolean everythingWorks(long Pa, long Pdifference, long sides) {
        long Pb = Pa + Pdifference;
        long Pc = Pb + Pa;

        return PolygonalNumber.isPolygonalNumber(sides, Pb) && PolygonalNumber.isPolygonalNumber(sides, Pc);
    }
}
