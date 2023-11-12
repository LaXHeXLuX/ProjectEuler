import UsefulFunctions.Converter;
import UsefulFunctions.Pandigital;

import java.util.*;

public class PE_032 {
    public static void main(String[] args) {
        int[][] pandigitalProductGroups = findPandigitalProductGroups();
        Set<Integer> products = new HashSet<>();
        for (int[] pandigitalProductGroup : pandigitalProductGroups) {
            System.out.println(Arrays.toString(pandigitalProductGroup));
            products.add(pandigitalProductGroup[2]);
        }

        int sum = 0;
        for (int product : products) sum += product;
        System.out.println(sum);
    }

    private static int[][] findPandigitalProductGroups() {
        List<int[]> pandigitalProductGroups = new ArrayList<>();

        for (int i = 0; i < 1_000; i++) {
            for (int j = i+1; j < 10_000; j++) {
                int[] productGroup = {i, j, i*j};
                if (Pandigital.groupIsPandigital(productGroup)) pandigitalProductGroups.add(productGroup);
            }
        }

        return Converter.arrListToArrInt(pandigitalProductGroups);
    }
}
