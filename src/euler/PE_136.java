package euler;

public class PE_136 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 50_000_000;
        return countSingleSolutions(limit);
    }

    private static int countSingleSolutions(int limit) {
        int count = 2;
        boolean[] composites = new boolean[limit >> 1];
        int l = (int) Math.sqrt(limit) >> 1;
        for (int i = 1; i < l; i++) {
            if (composites[i]) continue;
            for (int j = 2*i*i + 2*i; j < limit >> 1; j+=2*i+1) {
                composites[j] = true;
            }
        }

        for (int i = 1; i < limit >> 1; i++) {
            if (composites[i]) continue;
            int n = 2*i + 1;
            if (n % 4 == 3) count++;
            if (n < (limit >> 2)) count++;
            if (n < (limit >> 4)) count++;
        }
        return count;
    }
}
