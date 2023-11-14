import java.math.BigInteger;

public class PE_048 {
    public static void main(String[] args) {
        int limit = 1000;
        int[] arr = arrayOfNFirstDigits(limit);
        BigInteger answer = selfPowersSum(arr);
        System.out.println(answer.remainder(BigInteger.valueOf(10_000_000_000L)));
    }

    private static int[] arrayOfNFirstDigits(int n) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i+1;
        }

        return arr;
    }

    private static BigInteger selfPowersSum(int[] numbers) {
        BigInteger sum = BigInteger.ZERO;

        for (int number : numbers) {
            BigInteger n = BigInteger.valueOf(number);
            sum = sum.add(power(n, number));
        }

        return sum;
    }

    private static BigInteger power(BigInteger n, int power) {
        power--;
        BigInteger product = n;

        for (int i = 0; i < power; i++) {
            product = product.multiply(n);
        }

        return product;
    }
}
