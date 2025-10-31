package src.euler;

import util.PolygonalNumber;

public class PE_044 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        return smallestDifferenceWithProperty();
    }

    private static long smallestDifferenceWithProperty() {
        int n0 = 1;
        long p0 = PolygonalNumber.polygonalNumberLong(5, n0);

        while (p0 > 0) {
            int maxN1 = (3*n0*n0 - n0 - 2)/6;
            for (int n1 = 3; n1 <= maxN1 ; n1+=3) {
                if (indexesWork(n0, n1)) {
                    return p0;
                }
            }

            n0+=3;
            p0 = PolygonalNumber.polygonalNumberLong(5, n0);
        }

        return -1;
    }

    private static boolean indexesWork(int n0, int n1) {
        long n0Step = n0*(3L*n0-1);
        long n1Step = n1*(3L*n1-1);
        long n2sqrt = 1 + 12*n0Step + 12*n1Step;
        long n3sqrt = 1 + 12*n0Step + 24*n1Step;
        long n2s = (long) Math.sqrt(n2sqrt);
        long n3s = (long) Math.sqrt(n3sqrt);
        if (n2s * n2s != n2sqrt || n3s * n3s != n3sqrt) return false;
        return (1 + n2s) % 6 == 0 && (1 + n3s) % 6 == 0;
    }
}
