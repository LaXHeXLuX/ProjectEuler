import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_060 {
    public static void main(String[] args) {
        int size = 5;
        int[][] set = generateNthPrimeSetSize(size);
        System.out.println(Arrays.deepToString(set));
        System.out.println(set.length);
        set = mergeSortBySum(set);
        System.out.println(sum(set[0]));
    }

    private static int[][] mergeSortBySum(int[][] arr) {
        if (arr.length <= 1) return arr;
        int[][] arr1 = new int[arr.length/2][];
        int[][] arr2 = new int[arr.length - arr.length/2][];
        System.arraycopy(arr, 0, arr1, 0, arr1.length);
        System.arraycopy(arr, arr1.length, arr2, 0, arr2.length);
        arr1 = mergeSortBySum(arr1);
        arr2 = mergeSortBySum(arr2);
        int[][] sorted = new int[arr.length][];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (index2 == arr2.length || index1 < arr1.length && sum(arr1[index1]) <= sum(arr2[index2])) {
                sorted[i] = arr1[index1];
                index1++;
            } else {
                sorted[i] = arr2[index2];
                index2++;
            }
        }
        return sorted;
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for (int a : arr) sum += a;
        return sum;
    }

    private static int[][] generateNthPrimeSetSize(int n) {
        boolean[] primes = Primes.sieveOfPrimes(1_000_000_000);
        System.out.println("Primes done");
        int[] primesInt = Converter.booleanArrToIntArr(ArrayFunctions.subArray(primes, 0, (int) Math.min(Math.pow(10, n+1), 10_000)));

        List<int[]> firstPrimeSetsList = new ArrayList<>();
        for (int prime : primesInt) firstPrimeSetsList.add(new int[] {prime});
        int[][] primeSets = Converter.listToArr(firstPrimeSetsList);

        for (int i = 1; i < n; i++) {
            primeSets = generateNextPrimeSetSize(primesInt, primeSets, primes);
        }

        return primeSets;
    }

    private static int[][] generateNextPrimeSetSize(int[] primes, int[][] currentPrimeSets, boolean[] primesBool) {
        List<int[]> nextPrimeSets = new ArrayList<>();

        for (int[] currentPrimeSet : currentPrimeSets) {
            int[][] nextPrimeSubSets = generateNextPrimeSetSize(primes, currentPrimeSet, primesBool);
            nextPrimeSets.addAll(Arrays.asList(nextPrimeSubSets));
        }

        return Converter.listToArr(nextPrimeSets);
    }

    private static int[][] generateNextPrimeSetSize(int[] primes, int[] currentPrimeSet, boolean[] primesBool) {
        int hasDivisibleOf3 = -1;
        for (int prime : currentPrimeSet) {
            if (prime % 3 != 0) {
                hasDivisibleOf3 = prime % 3;
                break;
            }
        }

        List<int[]> primeSets = new ArrayList<>();
        for (int i = 1; i < primes.length; i++) {
            if (primes[i] <= currentPrimeSet[currentPrimeSet.length-1]) continue;
            if (hasDivisibleOf3 != -1 && primes[i] % 3 != hasDivisibleOf3) continue;
            int[] newPrimeSet = ArrayFunctions.concatenate(currentPrimeSet, new int[] {primes[i]});
            if (isPrimePairSet(newPrimeSet, primesBool)) primeSets.add(newPrimeSet);
        }

        return Converter.listToArr(primeSets);
    }

    private static boolean isPrimePairSet(int[] numbers, boolean[] primes) {
        int[][] possiblePairs = Combinations.chooseNElements(numbers, 2, true);

        for (int[] pair : possiblePairs) {
            if (!isPrimePair(pair, primes)) return false;
        }

        return true;
    }

    private static boolean isPrimePair(int[] pair, boolean[] primes) {
        int[] digits1 = Converter.digitArray(pair[0]);
        int[] digits2 = Converter.digitArray(pair[1]);
        int[] concatenationDigits = ArrayFunctions.concatenate(digits1, digits2);
        int concatenation = (int) Converter.digitFromArrayLong(concatenationDigits);

        return primes[concatenation];
    }
}
