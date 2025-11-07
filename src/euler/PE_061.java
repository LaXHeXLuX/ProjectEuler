package euler;

import utils.ArrayFunctions;
import utils.PolygonalNumber;

import java.util.ArrayList;
import java.util.List;

public class PE_061 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 6;
        List<int[]> combinations = generateNWorkingNumbers(n);

        int sum = 0;
        for (int num : combinations.getFirst()) sum += num;
        return sum;
    }

    private static List<int[]> generateNWorkingNumbers(int n) {
        int digits = 4;
        List<List<Integer>> allPolygons = generate4DigitPolygonalNumbers(n, digits);
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

    private static List<List<Integer>> generate4DigitPolygonalNumbers(int firstPolygonals, int digits) {
        List<List<Integer>> polygonalNumbers = new ArrayList<>();

        for (int i = 0; i < firstPolygonals; i++) {
            polygonalNumbers.add(generate4DigitPolygonalNumbersWithSides(i+3, digits));
        }

        return polygonalNumbers;
    }

    private static List<Integer> generate4DigitPolygonalNumbersWithSides(int sides, int digits) {
        List<Integer> polygonalNumbers = new ArrayList<>();
        int index = 1;
        int polygonalNumber = (int) PolygonalNumber.polygonalNumberLong(sides, index);

        while (polygonalNumber < Math.pow(10, digits-1)) {
            index++;
            polygonalNumber = (int) PolygonalNumber.polygonalNumberLong(sides, index);
        }

        while (polygonalNumber < Math.pow(10, digits)) {
            polygonalNumbers.add(polygonalNumber);
            index++;
            polygonalNumber = (int) PolygonalNumber.polygonalNumberLong(sides, index);
        }

        return polygonalNumbers;
    }

    private static boolean pairIsCyclic(int a, int b) {
        List<Integer> digitsB = new ArrayList<>();
        while (b > 0) {
            digitsB.add(b % 10);
            b /= 10;
        }
        int n = digitsB.size();
        if (n % 2 != 0) return false;
        int i = 0;
        while (a > 0) {
            int digit = a % 10;
            if (i < n/2 && digit != digitsB.get(i + n/2)) return false;
            a /= 10;
            i++;
        }
        return i == n;
    }
}
