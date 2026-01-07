package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.List;

public class PE_138 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int count = 12;
        List<Long> triangles = smallestTrianglesWithProperty(count);
        return sum(triangles);
    }

    private static long sum(List<Long> list) {
        long sum = 0;
        for (Long i : list) sum += i;
        return sum;
    }

    private static List<Long> smallestTrianglesWithProperty(int count) {
        List<Long> triangles = new ArrayList<>();

        int[] cf = Diophantine.continuedFraction(5);
        for (int i = 1; i <= count; i++) {
            long[] nthTerm = Diophantine.nthTermOfContinuedFraction(cf, 2*i);
            triangles.add(nthTerm[1]);
        }

        return triangles;
    }
}
