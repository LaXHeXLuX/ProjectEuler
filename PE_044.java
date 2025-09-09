import util.PolygonalNumber;

public class PE_044 {
    public static void main(String[] args) {
        System.out.println(PE());
    } // help here

    public static long PE() {
        return findSmallestDifferenceWithProperty();
        //return smallestDifferenceWithProperty();
    }

    private static long smallestDifferenceWithProperty() {
        int n0 = 1;
        long p0 = PolygonalNumber.polygonalNumberLong(5, n0);

        while (p0 > 0) {
            if (n0 % 100 == 0) System.out.print("n0: " + n0 + "\r");
            for (int n1 = n0+1; 3L*n1 + 1 <= p0; n1++) {

                long p1 = PolygonalNumber.polygonalNumberLong(5, n1);
                int a = 3;
                int[] xContenders = quadraticSolutions(a, 6*n1-1, -n0*(3*n0-1));
                //System.out.println(n0 + ": " + p0 + ", " + n1 + ": " + p1 + ", " + Arrays.toString(xContenders));
                for (int x : xContenders) {
                    if (x <= 0) continue;
                    int n2 = n1 + x;
                    long p2 = PolygonalNumber.polygonalNumberLong(5, n2);
                    if (p0 + p1 != p2) continue;//throw new RuntimeException(p0 + " + " + p1 + " = " + (p0+p1) + " != " + p2);
                    long p3 = p1 + p2;
                    if (PolygonalNumber.isPolygonalNumber(5, p3)) return p0;
                }
            }
            n0++;
            p0 = PolygonalNumber.polygonalNumberLong(5, n0);
        }

        return -1;
    }

    private static int[] quadraticSolutions(int a, int b, int c) {
        int sqrt = b*b - 4*a*c;
        if (sqrt < 0) return new int[0];
        int sqrtResult = (int) Math.sqrt(sqrt);
        if (sqrtResult*sqrtResult != sqrt) return new int[0];
        return new int[] {(-b + sqrtResult)/(2*a), (-b - sqrtResult)/(2*a)};
    }

    private static long findSmallestDifferenceWithProperty() {
        int differenceIndex = 1;
        long difference = PolygonalNumber.polygonalNumberLong(5, differenceIndex);

        while (difference > 0) {
            long starterIndex = 1;
            long Pa = PolygonalNumber.polygonalNumberLong(5, starterIndex);

            while (difference > 3*starterIndex + 1) {
                if (everythingWorks(Pa, difference)) {
                    return difference;
                }

                starterIndex++;
                Pa = PolygonalNumber.polygonalNumberLong(5, starterIndex);
            }

            differenceIndex++;
            difference = PolygonalNumber.polygonalNumberLong(5, differenceIndex);
        }

        return -1;
    }

    private static boolean everythingWorks(long Pa, long PDifference) {
        long Pb = Pa + PDifference;
        long Pc = Pb + Pa;

        return PolygonalNumber.isPolygonalNumber(5, Pb) && PolygonalNumber.isPolygonalNumber(5, Pc);
    }
}
