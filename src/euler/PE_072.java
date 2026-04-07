package euler;

public class PE_072 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 1_000_000;
        return String.valueOf(reducedProperFractionCount(limit));
    }

    private static long reducedProperFractionCount(int limitD) {
        int[] phi = new int[limitD+1];

        for (int i = 0; i <= limitD; i++) {
            phi[i] = i;
        }

        long result = 0;

        for (int p = 2; p <= limitD; p++) {
            if (phi[p] == p) {
                for (int k = p; k <= limitD; k += p) {
                    phi[k] -= phi[k] / p;
                }
            }
            result += phi[p];
        }

        return result;
    }
}
