package utils;

import java.math.BigInteger;

public final class Arithmetics {
    interface Arithmetic<T> {
        T add(T a, T b);
        T sub(T a, T b);
        T mul(T a, T b);
        T div(T a, T b);
        T rem(T a, T b);
        default T gcd(T a, T b) {
            throw new UnsupportedOperationException("gcd not supported for this type");
        }
        T zero();
        T one();
    }
    static final Arithmetic<Integer> INT = new Arithmetic<>() {
        public Integer add(Integer a, Integer b) {
            return a + b;
        }
        public Integer sub(Integer a, Integer b) {
            return a - b;
        }
        public Integer mul(Integer a, Integer b) {
            return a * b;
        }
        public Integer div(Integer a, Integer b) {
            return a / b;
        }
        public Integer rem(Integer a, Integer b) {
            return a % b;
        }
        public Integer gcd(Integer a, Integer b) {
            while (b != 0) {
                int t = b;
                b = a % b;
                a = t;
            }
            return a;
        }
        public Integer zero() {
            return 0;
        }
        public Integer one() {
            return 1;
        }
    };
    static final Arithmetic<Long> LONG = new Arithmetic<>() {
        public Long add(Long a, Long b) {
            return a + b;
        }
        public Long sub(Long a, Long b) {
            return a - b;
        }
        public Long mul(Long a, Long b) {
            return a * b;
        }
        public Long div(Long a, Long b) {
            return a / b;
        }
        public Long rem(Long a, Long b) {
            return a % b;
        }
        public Long gcd(Long a, Long b) {
            while (b != 0) {
                long t = b;
                b = a % b;
                a = t;
            }
            return a;
        }
        public Long zero() {
            return 0L;
        }
        public Long one() {
            return 1L;
        }
    };
    static final Arithmetic<Double> DOUBLE = new Arithmetic<>() {
        public Double add(Double a, Double b) {
            return a + b;
        }
        public Double sub(Double a, Double b) {
            return a - b;
        }
        public Double mul(Double a, Double b) {
            return a * b;
        }
        public Double div(Double a, Double b) {
            return a / b;
        }
        public Double rem(Double a, Double b) {
            return a % b;
        }
        public Double zero() {
            return 0.0;
        }
        public Double one() {
            return 1.0;
        }
    };
    static final Arithmetic<BigInteger> BIG = new Arithmetic<>() {
        public BigInteger add(BigInteger a, BigInteger b) {
            return a.add(b);
        }
        public BigInteger sub(BigInteger a, BigInteger b) {
            return a.subtract(b);
        }
        public BigInteger mul(BigInteger a, BigInteger b) {
            return a.multiply(b);
        }
        public BigInteger div(BigInteger a, BigInteger b) {
            return a.divide(b);
        }
        public BigInteger rem(BigInteger a, BigInteger b) {
            return a.remainder(b);
        }
        public BigInteger gcd(BigInteger a, BigInteger b) {
            return a.gcd(b);
        }
        public BigInteger zero() {
            return BigInteger.ZERO;
        }
        public BigInteger one() {
            return BigInteger.ONE;
        }
    };
    private Arithmetics() {}
}
