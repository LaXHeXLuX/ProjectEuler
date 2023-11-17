import UsefulFunctions.ArrayFunctions;
import UsefulFunctions.Divisors;
import UsefulFunctions.Primes;

public class PE_058 {
    public static void main(String[] args) {
        int[] fraction = {1, 10};
        System.out.println(firstSpiralLengthWherePrimeDiagonalRatioBelow(fraction));
    }

    private static int firstSpiralLengthWherePrimeDiagonalRatioBelow(int[] fraction) {
        boolean[] primes = Primes.sieveOfPrimes(1_000_000_000);
        int spiralLength = 3;
        int[] currentFraction = {3, 5};

        while (compareTo(currentFraction, fraction) >= 0) {
            spiralLength += 2;
            int[] diagonalNumbers = numbersInDiagonalsOfSpiralOfLength(spiralLength);
            currentFraction = new int[] {amountOfPrimesInArray(diagonalNumbers, primes), diagonalNumbers.length};
        }

        return spiralLength;
    }

    private static int compareTo(int[] fraction1, int[] fraction2) {
        int gcd = (int) Divisors.greatestCommonDivisor(fraction1[1], fraction2[1]);
        int[] newFraction1 = ArrayFunctions.multiply(fraction1, fraction2[1]/gcd);
        int[] newFraction2 = ArrayFunctions.multiply(fraction2, fraction1[1]/gcd);
        return Integer.compare(newFraction1[0], newFraction2[0]);
    }

    private static int[] numbersInDiagonalsOfSpiralOfLength(int n) {
        if (n % 2 == 0) return new int[0];
        int[] numbers = new int[1 + (n-1)*2];
        numbers[0] = 1;
        int currentNumber = 1;

        for (int i = 0; i < (n-1)/2; i++) {
            for (int j = 0; j < 4; j++) {
                currentNumber += (i+1)*2;
                numbers[1 + 4*i + j] = currentNumber;
            }
        }

        return numbers;
    }

    private static int amountOfPrimesInArray(int[] numbers, boolean[] primes) {
        int counter = 0;

        for (int number : numbers) {
            if (primes[number]) counter++;
        }

        return counter;
    }
}
