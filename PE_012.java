import UsefulFunctions.Divisors;
import UsefulFunctions.PolygonalNumber;

public class PE_012 {
    public static void main(String[] args) {
        PolygonalNumber pn = new PolygonalNumber();
        int n = 1;
        long triangleNumber = pn.polygonalNumber(3, n);
        Divisors d = new Divisors();
        long[] divisors = d.divisors(triangleNumber);
        while (divisors.length <= 500) {
            n++;
            triangleNumber = pn.polygonalNumber(3, n);
            divisors = d.divisors(triangleNumber);
        }
        System.out.println(triangleNumber);
    }

}
