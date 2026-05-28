package euler;

public class PE_997 {
    void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int x = 9;
        int y = 10;
        int z = 11;
        return String.valueOf(f(x, y, z));
    }

    private static long f(int x, int y, int z) {
        return 3 * (1L << (x+y+z-1)) * ((1L << x) + (1L << y) + (1L << z) - 4);
    }
}
