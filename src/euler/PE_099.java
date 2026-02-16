package euler;

import utils.Parser;

import java.util.*;

public class PE_099 {
    private record BaseExp(int base, int exp, int index) {
        @Override
            public String toString() {
                return this.base + "," + this.exp + "," + this.index;
            }
    }

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        String file = "src/euler/inputs/PE_099_exp.txt";
        List<BaseExp> baseExponents = parse(file);
        BaseExp biggestBaseExp = biggestBaseExp(baseExponents);
        return biggestBaseExp.index + 1;
    }

    private static BaseExp biggestBaseExp(List<BaseExp> baseExponents) {
        BaseExp biggest = baseExponents.getFirst();
        for (BaseExp baseExponent : baseExponents) {
            double x = Math.log(biggest.base) / Math.log(baseExponent.base);
            if (x * biggest.exp < baseExponent.exp) {
                biggest = baseExponent;
            }
        }
        return biggest;
    }

    private static List<BaseExp> parse(String filename) {
        int[][] lines = Parser.parseManyInts(filename, ",");
        List<BaseExp> baseExponents = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            baseExponents.add(new BaseExp(lines[i][0], lines[i][1], i));
        }
        return baseExponents;
    }
}
