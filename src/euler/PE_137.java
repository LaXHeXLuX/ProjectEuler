package euler;

public class PE_137 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int n = 15;
        return nthGoldenNugget(n);
    }

    private static long nthGoldenNugget(int n) {
        long a = 1;
        long b = 1;
        long num = a*b;
        long den = b*b - a*b - a*a;
        while (n > 0) {
            long temp = b;
            b += a;
            a = temp;
            num = a*b;
            den = b*b - a*b - a*a;
            if (den < 0) continue;
            n--;
        }

        return num/den;
    }
}
