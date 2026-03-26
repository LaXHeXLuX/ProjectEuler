package euler;

import utils.ArrayFunctions;
import utils.PolygonalNumber;

import java.util.ArrayList;
import java.util.List;

public class PE_061 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 6;
        List<int[]> combinations = generateWorkingNumbers(n);
        return String.valueOf(sum(combinations));
    }
    
    private static int sum(List<int[]> list) {
        int sum = 0;
        for (int i : list.getFirst()) sum += i;
        return sum;
    }

    private static List<int[]> generateWorkingNumbers(int n) {
        List<List<Integer>> allPolygons = generate4DigitPolygonalNumbers(n);
        int[][] currentCombinations = new int[allPolygons.getFirst().size()][];

        for (int i = 0; i < currentCombinations.length; i++) {
            currentCombinations[i] = new int[] {allPolygons.getFirst().get(i)};
        }

        allPolygons.removeFirst();
        List<int[]> workingCombinations = new ArrayList<>();

        for (int[] currentCombination : currentCombinations) {
            List<int[]> newCombinations = generateWorkingNumbersRec(currentCombination, allPolygons);
            workingCombinations.addAll(newCombinations);
        }

        List<int[]> finalCombinations = new ArrayList<>();
        for (int[] workingCombination : workingCombinations) {
            if (pairIsCyclic(workingCombination[workingCombination.length-1], workingCombination[0])) {
                finalCombinations.add(workingCombination);
            }
        }

        return finalCombinations;
    }

    private static List<int[]> generateWorkingNumbersRec(int[] currentCombination, List<List<Integer>> usablePolygons) {
        if (usablePolygons.size() == 1) return generateWorkingNumbersLast(currentCombination, usablePolygons.getFirst());
        List<int[]> newCombinations = new ArrayList<>();

        for (int i = 0; i < usablePolygons.size(); i++) {
            List<List<Integer>> nextUsable = new ArrayList<>();
            for (int j = 0; j < usablePolygons.size(); j++) {
                if (j == i) continue;
                nextUsable.add(new ArrayList<>(usablePolygons.get(j)));
            }

            for (int usablePolygon : usablePolygons.get(i)) {
                if (!pairIsCyclic(currentCombination[currentCombination.length-1], usablePolygon)) continue;
                List<int[]> nextCombinations = generateWorkingNumbersRec(ArrayFunctions.concatenate(currentCombination, new int[] {usablePolygon}), nextUsable);
                newCombinations.addAll(nextCombinations);
            }
        }

        return newCombinations;
    }

    private static List<int[]> generateWorkingNumbersLast(int[] currentCombination, List<Integer> usablePolygons) {
        List<int[]> newCombinations = new ArrayList<>();

        for (int usablePolygon : usablePolygons) {
            if (pairIsCyclic(currentCombination[currentCombination.length-1], usablePolygon) && pairIsCyclic(usablePolygon, currentCombination[0])) {
                int[] newCombination = ArrayFunctions.concatenate(currentCombination, new int[] {usablePolygon});
                newCombinations.add(newCombination);
            }
        }

        return newCombinations;
    }

    private static List<List<Integer>> generate4DigitPolygonalNumbers(int firstPolygonals) {
        List<List<Integer>> polygonalNumbers = new ArrayList<>();

        for (int i = 0; i < firstPolygonals; i++) {
            polygonalNumbers.add(generate4DigitPolygonalNumbersWithSides(i+3));
        }

        return polygonalNumbers;
    }

    private static List<Integer> generate4DigitPolygonalNumbersWithSides(int sides) {
        List<Integer> polygonalNumbers = new ArrayList<>();
        int index = 1;
        int polygonalNumber = (int) PolygonalNumber.polygonalNumber(sides, index);

        while (polygonalNumber < 1_000) {
            index++;
            polygonalNumber = (int) PolygonalNumber.polygonalNumber(sides, index);
        }

        while (polygonalNumber < 10_000) {
            polygonalNumbers.add(polygonalNumber);
            index++;
            polygonalNumber = (int) PolygonalNumber.polygonalNumber(sides, index);
        }

        return polygonalNumbers;
    }

    private static boolean pairIsCyclic(int a, int b) {
        return a % 100 == b / 100;
    }
}
