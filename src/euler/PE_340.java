package euler;

import java.math.BigInteger;

public class PE_340 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        BigInteger a = BigInteger.valueOf(21).pow(7);
        BigInteger b = BigInteger.valueOf(7).pow(21);
        BigInteger c = BigInteger.valueOf(12).pow(7);
        BigInteger S = S(a, b, c);
        int digits = 9;
        return lastNDigits(S, digits);
    }

    private static String lastNDigits(BigInteger number, int n) {
        String s = number.toString();
        if (s.length() < n) return s;
        return s.substring(s.length() - n);
    }

    private static BigInteger S(BigInteger a, BigInteger b, BigInteger c) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger q = b.divide(a);
        BigInteger r = b.remainder(a);
        sum = sum.add(r.add(BigInteger.ONE).multiply(F(a, b, c, BigInteger.ZERO)).add(sumTo(r)));
        BigInteger F0 = F(a, b, c, r.add(BigInteger.ONE));
        sum = sum.add(q.multiply(sumTo(a.subtract(BigInteger.ONE))));
        sum = sum.add(q.multiply(a).multiply(F0));
        sum = sum.subtract(BigInteger.valueOf(3).multiply(a).multiply(a.subtract(c)).multiply(sumTo(q.subtract(BigInteger.ONE))));
        BigInteger x = b.subtract(a.multiply(q)).subtract(r);
        sum = sum.add(x.multiply(F(a, b, c, a.multiply(q).add(r).add(BigInteger.ONE))));
        sum = sum.add(sumTo(x));
        return sum;
    }

    private static BigInteger sumTo(BigInteger n) {
        return n.multiply(n.add(BigInteger.ONE)).divide(BigInteger.TWO);
    }

    private static BigInteger F(BigInteger a, BigInteger b, BigInteger c, BigInteger n) {
        if (n.compareTo(b) > 0) return n.subtract(c);
        BigInteger q = b.subtract(n).divide(a);
        BigInteger big4 = BigInteger.valueOf(4);
        BigInteger big3 = BigInteger.valueOf(3);
        return n.add(big4.multiply(q).multiply(a)).subtract(big3.multiply(q).multiply(c)).add(big4.multiply(a.subtract(c)));
    }
}
