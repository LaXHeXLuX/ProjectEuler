package euler;

import utils.Parser;

import java.util.*;

public class PE_096 {

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        List<int[][]> sudokus = parser();
        int sum = 0;
        for (int[][] sudoku : sudokus) {
            sum += first3DigitsOfSolution(sudoku);
        }
        return sum;
    }

    private static int first3DigitsOfSolution(int[][] sudoku) {
        int[][] solution = recSolve(sudoku);
        if (solution.length == 0) throw new RuntimeException("Sudoku isn't valid...");
        int[] invalid = invalidPart(solution);
        if (invalid.length != 0) throw new RuntimeException("Sudoku isn't valid: " + Arrays.toString(invalid));
        int[] row1 = solution[0];
        return 100*row1[0] + 10*row1[1] + row1[2];
    }

    private static int[][] recSolve(int[][] sudoku) {
        int[][] solvedSudoku = deepCopy(sudoku);
        Map<Integer, Set<Integer>> possibilities = makePossibilities(solvedSudoku);
        try {
            solve(solvedSudoku, possibilities);
        } catch (IllegalArgumentException _) {
            return new int[0][];
        }
        return recSolve(solvedSudoku, possibilities);
    }

    private static int[][] recSolve(int[][] sudoku, Map<Integer, Set<Integer>> possibilities) {
        int[] invalid = invalidPart(sudoku);
        if (invalid.length == 0) return sudoku;
        if (invalid[0] >= 0) return new int[0][];

        int nextI = -invalid[1];
        Set<Integer> set = possibilities.get(nextI);
        for (Integer i : set) {
            int[][] newSudoku = deepCopy(sudoku);
            newSudoku[nextI/9][nextI%9] = i;
            Map<Integer, Set<Integer>> newPossibilities = new HashMap<>();
            for (Map.Entry<Integer, Set<Integer>> entry : possibilities.entrySet()) {
                newPossibilities.put(entry.getKey(), new HashSet<>(entry.getValue()));
            }
            newPossibilities.remove(nextI);
            prune(nextI, newSudoku, newPossibilities);
            try {
                solve(newSudoku, newPossibilities);
            } catch (IllegalArgumentException _) {
                continue;
            }
            int[][] solution = recSolve(newSudoku, newPossibilities);
            if (solution.length > 0) return solution;
        }

        return new int[0][];
    }

    private static void solve(int[][] sudoku, Map<Integer, Set<Integer>> possibilities) {
        int lastSize = 0;
        int currentSize = deepSize(possibilities);
        while (!possibilities.isEmpty() && lastSize != currentSize) {
            lastSize = currentSize;
            prune(sudoku, possibilities);
            clean(sudoku, possibilities);
            currentSize = deepSize(possibilities);
        }
    }

    private static int deepSize(Map<Integer, Set<Integer>> possibilities) {
        int sum = 0;
        for (Integer i : possibilities.keySet()) {
            sum += possibilities.get(i).size();
        }
        return sum;
    }

    private static int indexOf(int rowColBox, int i, int j) {
        // 0 - row, 1 - col, 2 - box
        return switch (rowColBox) {
            case 0 -> 9*i + j;
            case 1 -> 9*j + i;
            case 2 -> 9*(i / 3 * 3 + j / 3) + ((i % 3) * 3 + j % 3);
            default -> throw new IllegalStateException("Bad state: " + rowColBox);
        };
    }

    private static void clean(int[][] sudoku, Map<Integer, Set<Integer>> possibilities) {
        for (int structI = 0; structI < 9; structI++) {
            for (int i = 1; i <= 9 ; i++) {
                for (int rowColBox = 0; rowColBox < 3; rowColBox++) {
                    List<Integer> validCells = new ArrayList<>();
                    boolean skip = false;
                    for (int j = 0; j < 9; j++) {
                        int index = indexOf(rowColBox, structI, j);
                        if (sudoku[index/9][index%9] == i) {
                            skip = true;
                            break;
                        }
                        if (sudoku[index/9][index%9] != 0) continue;
                        if (possibilities.get(index).contains(i)) validCells.add(j);
                    }
                    if (skip) continue;

                    if (validCells.isEmpty()) throw new IllegalArgumentException("ValidCells is empty for struct: " + structI + ", i: " + i);
                    if (validCells.size() == 1) {
                        int onlyI = validCells.getFirst();
                        int index = indexOf(rowColBox, structI, onlyI);
                        sudoku[index/9][index%9] = i;
                        possibilities.remove(index);
                        prune(index, sudoku, possibilities);
                    }
                }
            }
        }
    }

    private static void prune(int[][] sudoku, Map<Integer, Set<Integer>> possibilities) {
        List<Integer> remove = new ArrayList<>();
        for (Integer i : possibilities.keySet()) {
            if (possibilities.get(i).size() == 1) {
                sudoku[i/9][i%9] = possibilities.get(i).iterator().next();
                remove.add(i);
            }
        }
        for (Integer i : remove) {
            possibilities.remove(i);
            prune(i, sudoku, possibilities);
        }
    }

    private static void prune(int index, int[][] sudoku, Map<Integer, Set<Integer>> possibilities) {
        for (int i = 0; i < 9; i++) {
            int[] positions = {
                    indexOf(0, index/9, i),
                    indexOf(1, index%9, i),
                    indexOf(2, index/9/3*3 + (index%9)/3, i)
            };
            for (int pos : positions) {
                if (possibilities.containsKey(pos)) {
                    possibilities.get(pos).remove(sudoku[index / 9][index % 9]);
                    if (possibilities.get(pos).isEmpty()) throw new IllegalArgumentException("[] at " + pos + " for " + index + " " + i);
                }
            }
        }
    }

    private static Map<Integer, Set<Integer>> makePossibilities(int[][] sudoku) {
        Map<Integer, Set<Integer>> possibilities = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    possibilities.put(i*9 + j, possibilities(i*9 + j, sudoku));
                }
            }
        }
        return possibilities;
    }

    private static Set<Integer> possibilities(int index, int[][] sudoku) {
        int row = index / 9;
        int col = index % 9;
        int box = row / 3 * 3 + col / 3;
        Set<Integer> possibilities = new HashSet<>();
        for (int i = 1; i <= 9; i++) possibilities.add(i);
        for (int i = 0; i < 9; i++) {
            possibilities.remove(sudoku[row][i]);
            possibilities.remove(sudoku[i][col]);
            possibilities.remove(sudoku[box/3*3 + i/3][(box%3)*3 + i%3]);
        }
        return possibilities;
    }

    private static int[] invalidPart(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            boolean[][] checked = new boolean[3][9];
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 3; k++) {
                    int index = indexOf(k, i, j);
                    int cell = sudoku[index/9][index%9];
                    if (cell == 0) return new int[] {-1, -index};
                    if (checked[k][cell-1]) return new int[] {k, i};
                    checked[k][cell-1] = true;
                }
            }
        }
        return new int[] {};
    }

    private static int[][] deepCopy(int[][] sudoku) {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            copy[i] = Arrays.copyOf(sudoku[i], sudoku[i].length);
        }
        return copy;
    }

    private static List<int[][]> parser() {
        String filename = "src/euler/inputs/PE_096_sudoku.txt";
        String[] lines = Parser.parseStrings(filename);
        List<int[][]> sudokus = new ArrayList<>();
        for (int i = 0; i < lines.length / 10; i++) {
            int[][] sudoku = new int[9][9];
            for (int j = 0; j < 9; j++) {
                String line = lines[10*i + j + 1];
                for (int k = 0; k < line.length(); k++) {
                    sudoku[j][k] = line.charAt(k) - '0';
                }
            }
            sudokus.add(sudoku);
        }
        return sudokus;
    }
}
