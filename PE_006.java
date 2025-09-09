public class PE_006 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 100;
        return difference(arrOfNFirstElements(n));
    }

    private static long[] arrOfNFirstElements(int n) {
        long[] arr = new long[n];
        for (int i = 1; i < n+1; i++) {
            arr[i-1] = i;
        }
        return arr;
    }

    private static long sumOfSquares(long[] numbers) {
        long sum = 0;
        for (long number : numbers) {
            sum += number*number;
        }
        return sum;
    }

    private static long squareOfSums(long[] numbers) {
        long sum = 0;
        for (long number : numbers) {
            sum += number;
        }
        return sum*sum;
    }

    private static long difference(long[] numbers) {
        return squareOfSums(numbers)-sumOfSquares(numbers);
    }
}
