package euler;

import utils.Parser;

public class PE_0099 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        String file = "src/euler/inputs/PE_0099_exp.txt";
        double[] logValues = parse(file);
        return String.valueOf(biggestIndex(logValues) + 1);
    }

    private static int biggestIndex(double[] logValues) {
        int biggest = 0;
        for (int i = 1; i < logValues.length; i++) {
            if (logValues[i] > logValues[biggest]) biggest = i;
        }
        return biggest;
    }

    private static double[] parse(String filename) {
        int[][] lines = Parser.parseManyInts(filename, ",");
        double[] logValues = new double[lines.length];
        for (int i = 0; i < lines.length; i++) {
            logValues[i] = lines[i][1] * Math.log(lines[i][0]);
        }
        return logValues;
    }
}
