import util.Converter;
import util.Pandigital;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PE_032 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[][] pandigitalProductGroups = findPandigitalProductGroups();
        Set<Integer> products = new HashSet<>();
        for (int[] pandigitalProductGroup : pandigitalProductGroups) {
            products.add(pandigitalProductGroup[2]);
        }

        int sum = 0;
        for (int product : products) sum += product;
        return sum;
    }

    private static int[][] findPandigitalProductGroups() {
        List<int[]> pandigitalProductGroups = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int jLimit = i < 10 ? 10_000 : 1_000;
            for (int j = i+1; j < jLimit; j++) {
                int[] productGroup = {i, j, i*j};
                if (Pandigital.groupIsPandigital(productGroup)) pandigitalProductGroups.add(productGroup);
            }
        }

        return Converter.listToArr(pandigitalProductGroups);
    }
}
