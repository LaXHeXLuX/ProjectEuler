package euler;

public class PE_301 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 30;
        return nimLose(limit);
    }

    private static long nimLose(int powerOfTwo) {
        long f1 = 1;
        long f2 = 2;
        for (int i = 1; i < powerOfTwo; i++) {
            long temp = f1;
            f1 = f2;
            f2 += temp;
        }
        return f2;
    }
}
