import util.Divisors;
import util.PolygonalNumber;

public class PE_012 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 1;
        long triangleNumber = PolygonalNumber.polygonalNumberLong(3, n);
        long[] divisors = Divisors.divisors(triangleNumber);
        while (divisors.length <= 500) {
            n++;
            triangleNumber = PolygonalNumber.polygonalNumberLong(3, n);
            divisors = Divisors.divisors(triangleNumber);
        }
        return triangleNumber;
    }
}
