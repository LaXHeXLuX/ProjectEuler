import util.Combinations;

public class PE_015 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 20;
        int m = 20;
        return Combinations.nChooseMBigInteger(m+n, n).longValue();
    }
}
