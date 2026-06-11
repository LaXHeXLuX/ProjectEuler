package euler;

import java.math.BigInteger;

public class PE_0340 {
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
        BigInteger big3 = BigInteger.valueOf(3);
        BigInteger big4 = BigInteger.valueOf(4);

        BigInteger part1 = sumTo(b);
        BigInteger part2 = big4.multiply(b.add(BigInteger.ONE)).multiply(a.subtract(c));
        BigInteger part3_1 = big4.multiply(a).subtract(big3.multiply(c));

        BigInteger q = b.divide(a);
        BigInteger r = b.subtract(a.multiply(q));
        BigInteger part3_2 = a.multiply(sumTo(q.subtract(BigInteger.ONE))).add(r.add(BigInteger.ONE).multiply(q));

        return part1.add(part2).add(part3_1.multiply(part3_2));
    }

    private static BigInteger sumTo(BigInteger n) {
        return n.multiply(n.add(BigInteger.ONE)).divide(BigInteger.TWO);
    }
}
