package euler;

public class PE_009 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int sum = 1000;
        int[] specialTriplet = findPythagoreanTripletWithSumOf(sum);
        return (long) specialTriplet[0] * specialTriplet[1] * specialTriplet[2];
    }

    private static int[] findPythagoreanTripletWithSumOf(int sum) {
        for (int a = 1; a < sum/2; a++) {
            for (int b = 1; b < Math.min(a, sum-2*a); b++) {
                int c = 1000-a-b;
                if (c < 1) continue;
                if (isPythagoreanTriplet(a, b, c)) return new int[] {a, b, c};
            }
        }
        return new int[] {-1};
    }

    private static boolean isPythagoreanTriplet(int a, int b, int c) {
        return a*a + b*b == c*c;
    }
}
