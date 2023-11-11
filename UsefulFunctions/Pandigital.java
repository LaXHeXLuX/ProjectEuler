package UsefulFunctions;

public class Pandigital {
    public boolean isPandigital(long n) {
        Converter c = new Converter();
        int[] digits = c.digitArray(n);

        return isPandigital(digits);
    }
    public boolean isPandigital(int[] digits) {
        ArrayFunctions af = new ArrayFunctions();
        digits = af.mergeSort(digits);

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != i+1) return false;
        }
        return true;
    }
    public boolean groupIsPandigital(int[] numbers) {
        int[][] groupDigits = new int[numbers.length][];
        Converter c = new Converter();
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            groupDigits[i] = c.digitArray(numbers[i]);
            sum += groupDigits[i].length;
        }

        return groupIsPandigitalHelper(groupDigits, sum);
    }
    public boolean groupIsPandigital(long[] numbers) {
        int[][] groupDigits = new int[numbers.length][];
        Converter c = new Converter();
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            groupDigits[i] = c.digitArray(numbers[i]);
            sum += groupDigits[i].length;
        }

        return groupIsPandigitalHelper(groupDigits, sum);
    }
    private boolean groupIsPandigitalHelper(int[][] groupDigits, int sum) {
        if (sum != 9) return false;
        int[] digits = new int[sum];
        int index = 0;
        for (int[] groupDigit : groupDigits) {
            for (int i : groupDigit) {
                digits[index] = i;
                index++;
            }
        }

        ArrayFunctions af = new ArrayFunctions();
        digits = af.mergeSort(digits);

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != i+1) return false;
        }
        return true;
    }
}
