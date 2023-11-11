import UsefulFunctions.Converter;

import java.util.ArrayList;
import java.util.List;

public class PE_040 {
    public static void main(String[] args) {
        int[] indexes = {1, 10, 100, 1_000, 10_000, 100_000, 1_000_000};
        for (int i = 0; i < indexes.length; i++) {
            indexes[i]--;
        }
        System.out.println(productOfConstantDigits(indexes));
    }

    private static int[] constantDigitsUntil(int limit) {
        List<Integer> constantDigits = new ArrayList<>();
        Converter c = new Converter();

        int i = 1;
        while (constantDigits.size() < limit) {
            int[] digits = c.digitArray(i);
            for (int digit : digits) constantDigits.add(digit);
            i++;
        }

        return c.listToArrInt(constantDigits);
    }

    private static long productOfConstantDigits(int[] indexes) {
        long product = 1;

        int[] constantDigits = constantDigitsUntil(max(indexes)+1);

        for (int index : indexes) {
            product *= constantDigits[index];
            System.out.println(index + ", " + constantDigits[index] + ", " + product);
        }

        return product;
    }

    private static int max(int[] arr) {
        int max = arr[0];

        for (int a : arr) {
            if (a > max) max = a;
        }

        return max;
    }
}
