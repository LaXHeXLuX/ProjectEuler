package euler;

import java.util.ArrayList;
import java.util.List;

public class PE_040 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] indexes = {1, 10, 100, 1_000, 10_000, 100_000, 1_000_000};
        return productOfConstantDigits(indexes);
    }

    private static List<Integer> digitsAtIndexes(int[] indexes) {
        List<Integer> digitsAtIndexes = new ArrayList<>();

        int i = 1;
        int currentIndex = 0;
        int indexesIndex = 0;
        while (true) {
            int temp = i;
            List<Integer> digits = new ArrayList<>();
            while (temp >= 10) {
                digits.add(temp % 10);
                temp /= 10;
            }
            digits.add(temp);
            for (int j = digits.size()-1; j >= 0; j--) {
                currentIndex++;
                if (currentIndex == indexes[indexesIndex]) {
                    digitsAtIndexes.add(digits.get(j));
                    indexesIndex++;
                    if (indexesIndex >= indexes.length) return digitsAtIndexes;
                }
            }
            i++;
        }
    }

    private static long productOfConstantDigits(int[] indexes) {
        List<Integer> digitsAtIndexes = digitsAtIndexes(indexes);

        long product = 1;
        for (Integer digitsAtIndex : digitsAtIndexes) {
            product *= digitsAtIndex;
        }

        return product;
    }
}
