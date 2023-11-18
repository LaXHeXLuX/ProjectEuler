import UsefulFunctions.ArrayFunctions;
import UsefulFunctions.Converter;
import UsefulFunctions.PolygonalNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_061 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int n = 6;
        int[][] combinations = generateNWorkingNumbers(n);

        System.out.println("Answer:");
        for (int[] comb : combinations) System.out.println(Arrays.toString(comb));

        int sum = 0;
        for (int num : combinations[0]) sum += num;
        System.out.println(sum);

        long end = System.currentTimeMillis();
        System.out.println("TIME: " + (end-start));
    }

    private static int[][] generateNWorkingNumbers(int n) {
        int digits = 4;
        int[][] allPolygons = generate4DigitPolygonalNumbers(n, digits);
        int[][] currentCombinations = new int[allPolygons[0].length][];

        for (int i = 0; i < currentCombinations.length; i++) {
            currentCombinations[i] = new int[] {allPolygons[0][i]};
        }
        
        allPolygons = ArrayFunctions.removeIndex(allPolygons, 0);
        System.out.println(Arrays.deepToString(currentCombinations));
        List<int[]> workingCombinations = new ArrayList<>();

        for (int[] currentCombination : currentCombinations) {
            int[][] newCombinations = generateWorkingNumbersRec(currentCombination, allPolygons);
            workingCombinations.addAll(Arrays.stream(newCombinations).toList());
        }

        System.out.println();
        List<int[]> finalCombinations = new ArrayList<>();
        for (int[] workingCombination : workingCombinations) {
            if (pairIsCyclic(workingCombination[workingCombination.length-1], workingCombination[0])) {
                finalCombinations.add(workingCombination);
            }
        }

        return Converter.arrListToArrInt(finalCombinations);
    }

    private static int[][] generateWorkingNumbersRec(int[] currentCombination, int[][] usablePolygons) {
        if (usablePolygons.length == 1) return generateWorkingNumbersLast(currentCombination, usablePolygons[0]);
        List<int[]> newCombinations = new ArrayList<>();

        for (int i = 0; i < usablePolygons.length; i++) {
            int[][] nextUsable = ArrayFunctions.removeIndex(usablePolygons, i);


            for (int usablePolygon : usablePolygons[i]) {
                if (!pairIsCyclic(currentCombination[currentCombination.length-1], usablePolygon)) continue;
                int[][] nextCombinations = generateWorkingNumbersRec(ArrayFunctions.concatenate(currentCombination, new int[] {usablePolygon}), nextUsable);
                newCombinations.addAll(Arrays.stream(nextCombinations).toList());
            }
        }

        return Converter.arrListToArrInt(newCombinations);
    }

    private static int[][] generateWorkingNumbersLast(int[] currentCombination, int[] usablePolygons) {
        List<int[]> newCombinations = new ArrayList<>();

        for (int usablePolygon : usablePolygons) {
            if (pairIsCyclic(currentCombination[currentCombination.length-1], usablePolygon) && pairIsCyclic(usablePolygon, currentCombination[0])) {
                int[] newCombination = ArrayFunctions.concatenate(currentCombination, new int[] {usablePolygon});
                newCombinations.add(newCombination);
            }
        }

        return Converter.arrListToArrInt(newCombinations);
    }

    private static int[][] generate4DigitPolygonalNumbers(int firstPolygonals, int digits) {
        int[][] polygonalNumbers = new int[firstPolygonals][];

        for (int i = 0; i < polygonalNumbers.length; i++) {
            polygonalNumbers[i] = generate4DigitPolygonalNumbersWithSides(i+3, digits);
        }

        return polygonalNumbers;
    }

    private static int[] generate4DigitPolygonalNumbersWithSides(int sides, int digits) {
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

        return Converter.listToArrInt(polygonalNumbers);
    }

    private static boolean pairIsCyclic(int a, int b) {
        int[] digitsA = Converter.digitArray(a);
        int[] digitsB = Converter.digitArray(b);
        if (digitsA.length != digitsB.length || digitsA.length % 2 != 0) return false;

        for (int indexB = 0; indexB < digitsA.length/2; indexB++) {
            int indexA = indexB + digitsA.length/2;
            if (digitsA[indexA] != digitsB[indexB]) return false;
        }

        return true;
    }
}
