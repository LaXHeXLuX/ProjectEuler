import UsefulFunctions.Converter;

public class PE_017 {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            sum += lettersInNumber(i);
        }
        System.out.println(sum);
        System.out.println(sum + 3 + 8);
    }
    private static int lettersInNumber(long n) {
        int[] digits = Converter.digitArray(n);
        int hundred = 7;
        int and = 3;
        if (digits.length < 3) return lettersInNumberUnder100(n);
        if (digits[1] == 0 && digits[2] == 0) return lettersInNumberUnder100(digits[0]) + hundred;
        return lettersInNumberUnder100(digits[0]) + hundred + and + lettersInNumberUnder100(n%100);
    }
    private static int lettersInNumberUnder100(long n) {
        if (n < 0 || n > 99) return -1;
        int[] singles = {0, 3, 3, 5, 4, 4, 3, 5, 5, 4};
        int[] teens = {3, 6, 6, 8, 8, 7, 7, 9, 8, 8};
        int[] tens = {0, 3, 6, 6, 5, 5, 5, 7, 6, 6};
        if (n < 10) return singles[(int) n];
        if (n < 20) return teens[(int) n-10];
        return tens[(int) (n/10)] + singles[(int) (n%10)];
    }
}
