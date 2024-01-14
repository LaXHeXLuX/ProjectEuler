public class PE_012 {
    public static void main(String[] args) {
        int n = 1;
        long triangleNumber = PolygonalNumber.polygonalNumberLong(3, n);
        long[] divisors = Divisors.divisors(triangleNumber);
        while (divisors.length <= 500) {
            n++;
            triangleNumber = PolygonalNumber.polygonalNumberLong(3, n);
            divisors = Divisors.divisors(triangleNumber);
        }
        System.out.println(triangleNumber);
    }

}
