package euler;

public class PE_097 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long factor = 28433;
        long exp = 2;
        long power = 7830457;
        long add = 1;
        long mod = 10_000_000_000L;
        return compute(factor, exp, power, add, mod);
    }

    private static long compute(long factor, long exp, long power, long add, long mod) {
        long answer = exp;
        for (int i = 0; i < power-1; i++) {
            answer = (answer * exp) % mod;
        }

        answer = (answer * factor) % mod;

        answer = (answer + add) % mod;
        return answer;
    }
}
