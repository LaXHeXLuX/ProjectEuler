package euler;

public class PE_092 {
    private static int[] chainEnds;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 10_000_000;
        int otherLimit = (int) Math.ceil(Math.log10(limit)) * 9*9;
        makeChains(otherLimit);
        return countOfNEnders((int) Math.log10(limit), 0, 89);
    }

    private static void makeChains(int limit) {
        chainEnds = new int[limit+1];
        for (int i = 1; i <= limit; i++) {
            chainEnds[i] = chainEnd(i);
        }
    }

    private static int countOfNEnders(int size, int currentSum, int n) {
        if (size == 0) {
            if (chainEnds[currentSum] == n) return 1;
            else return 0;
        }
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count += countOfNEnders(size-1, currentSum + i*i, n);
        }
        return count;
    }

    private static int chainEnd(int n) {
        int temp = n;
        while (temp != 1 && temp != 89) {
            temp = digitSquareSum(temp);
        }
        return temp;
    }

    private static int digitSquareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
