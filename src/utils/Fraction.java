package utils;

import java.math.BigInteger;
import java.util.*;

public class Fraction<T> {
    public T num;
    public T den;
    private final Arithmetics.Arithmetic<T> op;

    public Fraction(T num) {
        this.op = Arithmetics.of(num.getClass());
        this.num = num;
        this.den = op.one();
    }
    public Fraction(T num, T den) {
        this.op = Arithmetics.of(num.getClass());
        if (den.equals(op.zero())) throw new IllegalArgumentException("util.Fraction: denominator can't be zero!");
        if (op.compare(den, op.zero()) < 0) {
            den = op.neg(den);
            num = op.neg(num);
        }
        this.num = num;
        this.den = den;
    }

    public Fraction<T> add(Fraction<T> fraction) {
        T gcd = op.gcd(den, fraction.den);
        T newDen = op.mul(op.div(den, gcd), fraction.den);
        T newNum1 = op.mul(num, op.div(newDen, den));
        T newNum2 = op.mul(fraction.num, op.div(newDen, fraction.den));
        return new Fraction<>(op.add(newNum1, newNum2), newDen);
    }
    public Fraction<T> subtract(Fraction<T> fraction) {
        return this.add(new Fraction<>(op.neg(fraction.num), fraction.den));
    }
    public Fraction<T> multiply(Fraction<T> fraction) {
        return new Fraction<>(op.mul(num, fraction.num), op.mul(den, fraction.den));
    }
    public Fraction<T> divide(Fraction<T> fraction) {
        return this.multiply(new Fraction<>(fraction.den, fraction.num));
    }
    public Fraction<T> simplify() {
        T gcd = op.gcd(num, den);
        return new Fraction<>(op.div(num, gcd), op.div(den, gcd));
    }
    public String toString() {
        return "{" + num + ", " + den + "}";
    }
    public double doubleValue() {
        return op.doubleDivide(num, den);
    }
    public int compareTo(Fraction<T> fraction) {
        BigInteger product1 = new BigInteger(this.num.toString()).multiply(new BigInteger(fraction.den.toString()));
        BigInteger product2 = new BigInteger(fraction.num.toString()).multiply(new BigInteger(this.den.toString()));
        return product1.compareTo(product2);
    }
    private int[][] getInts(List<T> reciprocalCycle, int i) {
        int[] nonCycle = new int[i];
        int[] cycle = new int[reciprocalCycle.size()-i];
        for (int j = 0; j < i; j++) {
            nonCycle[j] = op.intValue(reciprocalCycle.get(j));
        }
        for (int j = i; j < reciprocalCycle.size(); j++) {
            cycle[j - i] = op.intValue(reciprocalCycle.get(j));
        }
        return new int[][] {nonCycle, cycle};
    }
    private int[][] convertToFraction(T divisible, Set<T> modCycle, List<T> reciprocalCycle) {
        Iterator<T> it = modCycle.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().equals(divisible)) break;
            i++;
        }

        return getInts(reciprocalCycle, i);
    }
    public int[][] getCycle() {
        if (op.compare(num, op.zero()) <= 0) throw new RuntimeException("Numerator has to be more than 0");
        if (op.compare(den, op.one()) == 0) return new int[][]{};

        Fraction<T> simple = this.simplify();
        List<T> reciprocalCycle = new ArrayList<>();
        Set<T> modCycle = new LinkedHashSet<>();
        T divisible = op.rem(simple.num, simple.den);

        while (op.compare(divisible, op.zero()) != 0) {
            if (!modCycle.add(divisible)) {
                return convertToFraction(divisible, modCycle, reciprocalCycle);
            }
            divisible = op.mul(divisible, op.ten());
            while (op.compare(divisible, simple.den) < 0) {
                reciprocalCycle.add(op.zero());
                if (!modCycle.add(divisible)) {
                    return convertToFraction(divisible, modCycle, reciprocalCycle);
                }
                divisible = op.mul(divisible, op.ten());
            }
            reciprocalCycle.add(op.div(divisible, simple.den));
            divisible = op.rem(divisible, simple.den);
        }

        return convertToFraction(divisible, modCycle, reciprocalCycle);
    }
}

