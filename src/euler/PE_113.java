package euler;

public class PE_113 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int digits = 100;
        return nonBouncyCount(digits);
    }

    private static long nonBouncyCount(int digits) {
        long increasing = increasingCount(digits);
        long decreasing = decreasingCount(digits);
        long same = 9L * digits;
        return increasing + decreasing + same;
    }

    private static long increasingCount(int digits) {
        if (digits == 1) return 0;
        long sum = increasingCount(digits-1);
        for (int startDigit = 1; startDigit < 10; startDigit++) {
            for (int endDigit = startDigit+1; endDigit < 10; endDigit++) {
                int n = endDigit-startDigit;
                int m = digits-1;
                sum += nChooseM(n + m - 1, m - 1);
            }
        }
        return sum;
    }

    private static long decreasingCount(int digits) {
        if (digits == 1) return 0;
        long sum = decreasingCount(digits-1);
        for (int startDigit = 1; startDigit < 10; startDigit++) {
            for (int endDigit = 0; endDigit < startDigit; endDigit++) {
                int n = startDigit-endDigit;
                int m = digits-1;
                sum += nChooseM(n + m - 1, m - 1);
            }
        }
        return sum;
    }

    public static long nChooseM(int n, int m) {
        if (m > n-m) m = n-m;
        long prod = 1;
        for (int i = 1; i <= m; i++) {
            prod = prod * (n-m+i) / i;
        }
        return prod;
    }
}
