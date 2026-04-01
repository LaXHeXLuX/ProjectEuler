package euler;

import utils.Parser;

import java.util.*;

public class PE_099 {
    private record BaseExp(int base, int exp, int index) {}

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        String file = "src/euler/inputs/PE_099_exp.txt";
        BaseExp[] baseExponents = parse(file);
        BaseExp biggestBaseExp = biggestBaseExp(baseExponents);
        return String.valueOf(biggestBaseExp.index + 1);
    }

    private static BaseExp biggestBaseExp(BaseExp[] baseExponents) {
        BaseExp biggest = baseExponents[0];
        for (BaseExp baseExponent : baseExponents) {
            double x = Math.log(biggest.base) / Math.log(baseExponent.base);
            if (x * biggest.exp < baseExponent.exp) {
                biggest = baseExponent;
            }
        }
        return biggest;
    }

    private static BaseExp[] parse(String filename) {
        int[][] lines = Parser.parseManyInts(filename, ",");
        BaseExp[] baseExponents = new BaseExp[lines.length];
        for (int i = 0; i < lines.length; i++) {
            baseExponents[i] = new BaseExp(lines[i][0], lines[i][1], i);
        }
        return baseExponents;
    }
}
