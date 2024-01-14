import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int[][] other = testMethod(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.deepToString(other));
    }
    private static int[][] testMethod(int[] arr) {
        int[][] returning = new int[][] {arr};
        arr[0] = 0;
        return returning;
    }
}
