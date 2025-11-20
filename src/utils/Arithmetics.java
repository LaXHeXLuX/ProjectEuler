package utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

public class Arithmetics {
    interface Arithmetic<T> {
        T add(T a, T b);
        T neg(T a);
        default T sub(T a, T b) {
            return add(a, neg(b));
        }
        T mul(T a, T b);
        T div(T a, T b);
        T rem(T a, T b);
        default T gcd(T a, T b) {
            throw new UnsupportedOperationException("gcd not supported for this type");
        }
        T zero();
        T one();
        T ten();
        T valueOf(long n);
        T valueOf(String s);
        int intValue(T a);
        double doubleValue(T a);
        double doubleDivide(T a, T b);
        int compare(T a, T b);
    }
    static final Arithmetic<Integer> INT = new Arithmetic<>() {
        public Integer add(Integer a, Integer b) {
            return a + b;
        }
        public Integer neg(Integer a) {
            return -a;
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
        public Integer ten() {
            return 10;
        }
        public Integer valueOf(long n) {
            if (n > Integer.MAX_VALUE) throw new IllegalArgumentException("n too large");
            return (int) n;
        }
        public Integer valueOf(String s) {
            return Integer.parseInt(s);
        }
        public int intValue(Integer a) {
            return a;
        }
        public double doubleValue(Integer a) {
            return (double) a;
        }
        public double doubleDivide(Integer a, Integer b) {
            return a.doubleValue() / b.doubleValue();
        }
        public int compare(Integer a, Integer b) {
            return Integer.compare(a, b);
        }
    };
    static final Arithmetic<Long> LONG = new Arithmetic<>() {
        public Long add(Long a, Long b) {
            return a + b;
        }
        public Long neg(Long a) {
            return -a;
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
        public Long ten() {
            return 10L;
        }
        public Long valueOf(long n) {
            return n;
        }
        public Long valueOf(String s) {
            return Long.parseLong(s);
        }
        public int intValue(Long a) {
            return a.intValue();
        }
        public double doubleValue(Long a) {
            return (double) a;
        }
        public double doubleDivide(Long a, Long b) {
            return a.doubleValue() / b.doubleValue();
        }
        public int compare(Long a, Long b) {
            return Long.compare(a, b);
        }
    };
    static final Arithmetic<Double> DOUBLE = new Arithmetic<>() {
        public Double add(Double a, Double b) {
            return a + b;
        }
        public Double neg(Double a) {
            return -a;
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
        public Double ten() {
            return 10.0;
        }
        public Double valueOf(long n) {
            return (double) n;
        }
        public Double valueOf(String s) {
            return Double.parseDouble(s);
        }
        public int intValue(Double a) {
            return a.intValue();
        }
        public double doubleValue(Double a) {
            return a;
        }
        public double doubleDivide(Double a, Double b) {
            return a / b;
        }
        public int compare(Double a, Double b) {
            return Double.compare(a, b);
        }
    };
    static final Arithmetic<BigInteger> BIG = new Arithmetic<>() {
        public BigInteger add(BigInteger a, BigInteger b) {
            return a.add(b);
        }
        public BigInteger neg(BigInteger a) {
            return a.negate();
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
        public BigInteger ten() {
            return BigInteger.TEN;
        }
        public BigInteger valueOf(long n) {
            return BigInteger.valueOf(n);
        }
        public BigInteger valueOf(String s) {
            return new BigInteger(s);
        }
        public int intValue(BigInteger a) {
            return a.intValue();
        }
        public double doubleValue(BigInteger a) {
            return a.doubleValue();
        }
        public double doubleDivide(BigInteger a, BigInteger b) {
            return new BigDecimal(a).divide(new BigDecimal(b), MathContext.DECIMAL64).doubleValue();
        }
        public int compare(BigInteger a, BigInteger b) {
            return a.compareTo(b);
        }
    };
    static final Map<Class<?>, Arithmetic<?>> MAP = new HashMap<>() {{
        put(Integer.class, INT);
        put(Long.class, LONG);
        put(BigInteger.class, BIG);
        put(Double.class, DOUBLE);
    }};
    @SuppressWarnings("unchecked")
    static <T> Arithmetic<T> of(Class<?> cls) {
        Arithmetic<?> a = MAP.get(cls);
        if (a == null)
            throw new IllegalArgumentException("No Arithmetic defined for " + cls);
        return (Arithmetic<T>) a;
    }
    private Arithmetics() {}
}
