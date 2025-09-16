import java.util.ArrayList;
import java.util.List;

public class PE_094 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000_000;
        long sum = 0;
        List<Long> perimeters = perimeters(limit);
        System.out.println(perimeters.size() + " - " + perimeters);
        for (long p : perimeters) {
            sum += p;
        }
        return sum;
    }

    private static List<Long> perimeters(int perimeterLimit) {
        List<Long> perimeters = new ArrayList<>();
        int baseLimit = perimeterLimit / 3 / 2;
        long n;
        for (n = 2; n < baseLimit; n+=2) { // help here
            if (isSquare(3*n*n + 4*n + 1)) {
                //System.out.println("3*n*n + 4*n + 1: " + n);
                perimeters.add(6*n + 2);
            }
            if (isSquare(3*n*n + 2*n)) {
                //System.out.println("3*n*n + 2*n: " + n);
                perimeters.add(6*n + 4);
            }
        }
        if (isSquare(3*n*n + 4*n + 1)) perimeters.add(6*n + 2);

        return perimeters;
    }

    private static boolean isSquare(long n2) {
        long n = (long) Math.sqrt(n2);
        return n*n == n2;
    }
}
