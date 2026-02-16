package euler;

public class PE_206 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int[] form = {1, -1, 2, -1, 3, -1, 4, -1, 5, -1, 6, -1, 7, -1, 8, -1, 9, -1, 0};
        return smallestWithSquareForm(form);
    }

    private static long replace(int[] form, int digit) {
        long n = 0;
        for (int i : form) {
            if (i == -1) n = n*10 + digit;
            else n = n*10 + i;
        }
        return n;
    }

    private static int[] lastDigitFromForm(int[] form) {
        int last = form[form.length-1];
        if (last == -1) return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[][] lookup = {
                {0},
                {1, 9},
                {},
                {},
                {2, 8},
                {5},
                {4, 6},
                {},
                {},
                {3, 7}
        };
        return lookup[last];
    }

    private static long smallestWithSquareForm(int[] form) {
        int[] lastDigit = lastDigitFromForm(form);
        if (lastDigit.length == 0) return -1;
        long min = (long) Math.sqrt(replace(form, 0)) / 10;
        long max = (long) Math.sqrt(replace(form, 9)) / 10;
        for (long i = min; i <= max; i++) {
            for (int i1 : lastDigit) {
                long n = i*10 + i1;
                long square = n*n;
                if (hasForm(square, form)) return n;
            }
        }
        return -1;
    }

    private static boolean hasForm(long n, int[] form) {
        for (int i = form.length-1; i >= 0; i--) {
            if (n == 0) return false;
            int lastDigit = (int) (n % 10);
            n /= 10;
            if (form[i] != -1 && form[i] != lastDigit) return false;
        }
        return n == 0;
    }
}
