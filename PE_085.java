public class PE_085 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        long limit = 2_000_000L;
        int[] result = closestForLimit(limit);
        System.out.println(STR."closest area and limit: \{result[0] * result[1]} - \{rectangleCount(result[0], result[1])}");

        long end = System.currentTimeMillis();
        System.out.println(STR."Time: \{end - start} ms");
    }
    private static int[] closestForLimit(long limit) {
        int closestFor1 = findLimitFor(1, limit);
        int[] closest = {1, closestFor1};
        long closestDifference = Math.abs(rectangleCount(1, closestFor1) - limit);

        for (int x = 2; x <= closestFor1; x++) {
            if (rectangleCount(x, x+1) < limit)
                continue;

            int closestY = findLimitFor(x, limit);
            long count = rectangleCount(x, closestY);

            if (Math.abs(count - limit) < closestDifference) {
                closestDifference = Math.abs(count - limit);
                closest = new int[] {x, closestY};
            }
        }

        return closest;
    }
    private static int findLimitFor(int x, long limit) {
        int y = 0;
        long count = rectangleCount(x, y);
        long lastCount = count;

        while (count < limit) {
            lastCount = count;
            y++;
            count = rectangleCount(x, y);
        }

        if (count - limit > limit - lastCount)
            y--;

        return y;
    }
    private static long rectangleCount(int x, int y) {
        if (x > y) {
            int temp = y;
            y = x;
            x = temp;
        }
        return rectangleCountInGrid(x, y);
    }
    private static long rectangleCountInGrid(int x, int y) {
        if (x <= 0)
            return 0;
        if (x == 1)
            return (long) y * (y + 1) / 2;

        return (long) y * x * (y+1) * (x+1) / 4;
    }
}
