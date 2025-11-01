package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PolygonalNumber {
    public static long polygonalNumberLong(long sides, long n) {
        return ((sides - 2)*n*n - (sides - 4)*n)/2;
    }
    public static BigInteger polygonalNumberBigInteger(long sides, long n) {
        BigInteger s = BigInteger.valueOf(sides);
        BigInteger bigN = BigInteger.valueOf(n);
        return bigN.multiply(
                s.subtract(BigInteger.valueOf(2))
                        .multiply(bigN.subtract(BigInteger.ONE))
                        .divide(BigInteger.TWO)
                        .add(BigInteger.ONE)
        );
    }
    public static boolean isPolygonalNumber(long sides, long x) {
        if (sides == x) return true;
        long a = sides-2;
        long b = 4-sides;
        long c = -2*x;
        double n = (-b + Math.sqrt(b*b - 4*a*c))/(2*a);
        return n == Math.floor(n);
    }
    public static int[] isPolygonalNumber(long x) {
        List<Integer> possibleSides = new ArrayList<>();

        for (int sides = 3; sides < x/3 + 2; sides++) {
            if (sides < 0) break;
            if (isPolygonalNumber(sides, x)) possibleSides.add(sides);
        }
        if (possibleSides.isEmpty()) return new int[0];
        return Converter.listToArr(possibleSides);
    }
}
