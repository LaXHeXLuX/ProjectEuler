package euler;

import java.util.Arrays;

public class PE_084 {
    private static final int squares = 40;
    private static final int doublesCount = 3;
    private static int diceSides;

    private static final int JAIL = 10;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        diceSides = 4;
        double[][] matrix = markovMatrix();
        double[] landings = landings(matrix);

        Integer[] indices = new Integer[squares];
        for (int i = 0; i < squares; i++) indices[i] = i;
        Arrays.sort(indices, (a, b) -> Double.compare(landings[b], landings[a]));
        return "" + indices[0] + indices[1] + indices[2];
    }

    private static double[] landings(double[][] matrix) {
        double[] state = new double[squares];
        Arrays.fill(state, 1.0 / (squares));

        for (int m = 0; m < 100; m++) {
            double[] next = new double[squares];
            for (int i = 0; i < squares; i++) {
                for (int j = 0; j < squares; j++) {
                    next[j] += state[i] * matrix[i][j];
                }
            }
            state = next;
        }

        return state;
    }

    private static double[][] markovMatrix() {
        double[][] matrix = new double[squares][squares];
        for (int square = 0; square < squares; square++) {
             matrix[square] = landings(square);
        }
        return matrix;
    }

    private static double[] landings(int square) {
        return landings(square, 1);
    }

    private static double[] landings(int square, int rollCount) {
        double[] landings = new double[squares];

        double coefficient = 1.0/(diceSides*diceSides);
        for (int d1 = 1; d1 <= diceSides; d1++) {
            for (int d2 = 1; d2 <= diceSides; d2++) {
                int nextSquare = (square + d1 + d2) % squares;
                if (d1 == d2) {
                    if (rollCount == doublesCount) {
                        landings[JAIL] += coefficient;
                        continue;
                    } else {
                        double[] nextLandings = landings(nextSquare, rollCount+1);
                        for (int i = 0; i < nextLandings.length; i++) {
                            landings[i] += coefficient*nextLandings[i];
                        }
                    }
                    continue;
                }
                switch (nextSquare) {
                    case 30 -> landings[JAIL] += coefficient;
                    case 2, 17, 33 -> {
                        landings[0] += coefficient/16;
                        landings[JAIL] += coefficient/16;
                        landings[nextSquare] += coefficient*14/16;
                    }
                    case 7, 22, 36 -> {
                        landings[0] += coefficient/16;
                        landings[JAIL] += coefficient/16;
                        landings[11] += coefficient/16;
                        landings[24] += coefficient/16;
                        landings[39] += coefficient/16;
                        landings[5] += coefficient/16;
                        int nextRail = ((nextSquare + 5)/10*10 + 5) % squares;
                        landings[nextRail] += coefficient*2/16;
                        int nextUtility = 12;
                        if (nextSquare > 11 && nextSquare < 28) nextUtility = 28;
                        landings[nextUtility] += coefficient/16;
                        int back3 = (nextSquare-3+squares) % squares;
                        landings[back3] += coefficient/16;
                        landings[nextSquare] += coefficient*6/16;
                    }
                    default -> landings[nextSquare] += coefficient;
                }
            }
        }

        return landings;
    }
}
