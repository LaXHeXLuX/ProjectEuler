package euler;

public class PE_085 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long limit = 2_000_000L;
        int[] result = closestForLimit(limit);
        return String.valueOf((long) result[0] * result[1]);
    }

    private static int[] closestForLimit(long limit) {
        int closestFor1 = limitFor2(1, limit);
        int[] closest = {1, closestFor1};
        long closestDifference = Math.abs(rectangleCount(1, closestFor1) - limit);

        for (int m = 2; m <= closestFor1; m++) {
            int closestN = limitFor2(m, limit);
            long count = rectangleCount(m, closestN);

            if (Math.abs(count - limit) < closestDifference) {
                closestDifference = Math.abs(count - limit);
                closest = new int[] {m, closestN};
            }
        }

        return closest;
    }

    private static int limitFor2(int m, long limit) {
        int n1 = (int) Math.sqrt((double) (limit << 2) / (m*(m+1)));
        int n2 = n1 +1;
        long diff1 = limit - rectangleCount(m, n1);
        long diff2 = rectangleCount(m, n2) - limit;
        int result = n1;
        if (diff1 > diff2) result = n2;
        return result;
    }

    private static long rectangleCount(int m, int n) {
        return ((long) n * m * (n+1) * (m+1)) >> 2;
    }
}
