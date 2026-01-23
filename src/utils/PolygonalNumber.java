package utils;

import java.util.ArrayList;
import java.util.List;

public class PolygonalNumber {
    public static long polygonalNumber(long sides, long n) {
        return ((sides - 2)*n*n - (sides - 4)*n)/2;
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

        int limit = Math.toIntExact(Math.min(x/3, Integer.MAX_VALUE));
        for (int sides = 3; sides < limit + 2; sides++) {
            if (isPolygonalNumber(sides, x)) possibleSides.add(sides);
        }
        if (possibleSides.isEmpty()) return new int[0];
        return Converter.listToArr(possibleSides);
    }
}
