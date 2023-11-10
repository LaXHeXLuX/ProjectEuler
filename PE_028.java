public class PE_028 {
    public static void main(String[] args) {
        int limit = 1001;
        System.out.println(numberSpiralDiagonalSum(limit));
    }

    private static int numberSpiralDiagonalSum(int spiralSize) {
        int sum = 1;
        int currentNumber = 1;
        for (int i = 3; i <= spiralSize; i+=2) {
            for (int j = 0; j < 4; j++) {
                currentNumber += i-1;
                sum += currentNumber;
            }
        }
        return sum;
    }
}
