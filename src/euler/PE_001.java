package src.euler;

public class PE_001 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] dividers = {3, 5};
        int n = 1000;
        return sumOfMultiplesOf(dividers, n);
    }

    private static long sumOfMultiplesOf(int[] dividers, long limit) {
        long sum = 0;
        for (int i = 1; i < limit; i++) {
            if (isMultipleOf(dividers, i)) sum += i;
        }
        return sum;
    }

    private static boolean isMultipleOf(int[] dividers, long n) {
        for (int divider : dividers) {
            if (n % divider == 0) return true;
        }
        return false;
    }
}
