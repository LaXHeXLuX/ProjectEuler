package euler;

public class PE_301 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 30;
        return String.valueOf(nimLose(limit));
    }

    private static long nimLose(int powerOfTwo) {
        long count = 1;
        long f1 = 1;
        long f2 = 1;
        for (int i = 0; i < powerOfTwo; i++) {
            count += f1;
            long temp = f1;
            f1 = f2;
            f2 += temp;
        }
        return count;
    }
}
