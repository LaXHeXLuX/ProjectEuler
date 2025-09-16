public class PE_092 {
    private static int[] chainEnds;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 10_000_000;
        int otherLimit = (int) Math.ceil(Math.log10(limit)) * 9*9;
        makeChains(otherLimit);
        return countOf89Enders(limit);
    }

    private static void makeChains(int limit) {
        chainEnds = new int[limit+1];
        for (int i = 1; i <= limit; i++) {
            chainEnds[i] = chainEnd(i);
        }
    }

    private static int countOf89Enders(int limit) {
        int count = 0;
        for (int i = 1; i < limit; i++) {
            if (chainEnds[digitSquareSum(i)] == 89) count++;
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
