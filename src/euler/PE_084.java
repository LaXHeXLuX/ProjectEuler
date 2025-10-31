package euler;

public class PE_084 {
    private static final int G2J = 30;
    private static final int CH1 = 7;
    private static final int CH2 = 22;
    private static final int CH3 = 36;
    private static final int CC1 = 2;
    private static final int CC2 = 17;
    private static final int CC3 = 33;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int diceSides = 4;
        int moves = 1_000_000;
        int[] boardMoves = simulateMoves(moves, diceSides);
        int[][] sorted = mergeSort(boardMoves);
        StringBuilder modalString = new StringBuilder();
        for (int i = 1; i < 4; i++) {
            int el = sorted[1][sorted[1].length-i];
            modalString.append(el);
        }
        return Long.parseLong(modalString.toString());
    }

    private static int[][] mergeSort(int[] scores) {
        int[] positions = new int[scores.length];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = i;
        }

        return mergeSort(scores, positions);
    }

    private static int[][] mergeSort(int[] scores, int[] positions) {
        if (scores.length <= 1) return new int[][] {scores, positions};

        int[] halfScores1 = new int[scores.length / 2];
        System.arraycopy(scores, 0, halfScores1, 0, halfScores1.length);
        int[] halfScores2 = new int[scores.length - scores.length / 2];
        System.arraycopy(scores, scores.length / 2, halfScores2, 0, halfScores2.length);

        int[] halfPositions1 = new int[positions.length / 2];
        System.arraycopy(positions, 0, halfPositions1, 0, halfPositions1.length);
        int[] halfPositions2 = new int[positions.length - positions.length / 2];
        System.arraycopy(positions, positions.length / 2, halfPositions2, 0, halfPositions2.length);

        int[][] half1 = mergeSort(halfScores1, halfPositions1);
        int[][] half2 = mergeSort(halfScores2, halfPositions2);

        int[] totalScores = new int[scores.length];
        int[] totalPositions = new int[positions.length];

        int index1 = 0;
        int index2 = 0;

        for (int i = 0; i < totalScores.length; i++) {
            if (index1 == half1[1].length) {
                totalScores[i] = half2[0][index2];
                totalPositions[i] = half2[1][index2];
                index2++;
                continue;
            }

            if (index2 == half2[1].length) {
                totalScores[i] = half1[0][index1];
                totalPositions[i] = half1[1][index1];
                index1++;
                continue;
            }

            if (half1[0][index1] <= half2[0][index2]) {
                totalScores[i] = half1[0][index1];
                totalPositions[i] = half1[1][index1];
                index1++;
            } else {
                totalScores[i] = half2[0][index2];
                totalPositions[i] = half2[1][index2];
                index2++;
            }
        }

        return new int[][] {totalScores, totalPositions};
    }

    private static int[] simulateMoves(int moves, int diceSides) {
        int[] board = new int[40];
        int position = 0;
        String[] CC = {"GO", "JAIL", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        String[] CH = {"GO", "JAIL", "C1", "E3", "H2", "R1", "next R", "next R", "next U", "back 3", "", "", "", "", "", ""};

        for (int i = 0; i < moves; i++) {
            position = makeMove(position, diceSides);
            switch (position) {
                case G2J -> position = 10;
                case CH1, CH2, CH3 -> position = managePosition(position, CH);
                case CC1, CC2, CC3 -> position = managePosition(position, CC);
            }

            board[position]++;
        }

        return board;
    }

    private static int managePosition(int position, String[] deck) {
        switch (deck[0]) {
            case "GO" -> position = 0;
            case "JAIL" -> position = 10;
            case "C1" -> position = 11;
            case "E3" -> position = 24;
            case "H2" -> position = 39;
            case "R1" -> position = 5;
            case "next R" -> position = ((15-position%10) + position) % 40;
            case "next U" -> position = position < 38 && position >= 12 ? 38 : 12;
            case "back 3" -> position = (position - 3 + 40) % 40;
        }
        cycleDeck(deck);
        return position;
    }

    private static void cycleDeck(String[] deck) {
        String first = deck[0];

        for (int i = 0; i < deck.length - 1; i++) {
            deck[i] = deck[i+1];
        }

        deck[deck.length-1] = first;
    }

    private static int makeMove(int position, int diceSides) {
        int rolls = 0;
        int[] roll = rollDice(diceSides);
        rolls++;
        position = addRollToPosition(position, roll);

        while (roll[0] == roll[1]) {
            if (rolls == 3 || position == 30) {
                return 30;
            }

            roll = rollDice(diceSides);
            rolls++;
            position = addRollToPosition(position, roll);
        }

        return position;
    }

    private static int addRollToPosition(int position, int[] roll) {
        return (position + roll[0] + roll[1]) % 40;
    }

    private static int[] rollDice(int diceSides) {
        return new int[] {(int) (Math.random() * diceSides + 1), (int) (Math.random() * diceSides + 1)};
    }
}
