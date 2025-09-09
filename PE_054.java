import util.ArrayFunctions;
import util.Converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_054 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        String file = "PE_054_poker.txt";
        String[][][] hands = parse(file);

        int counter = 0;

        for (String[][] handPair : hands) {
            boolean winnerIsFirst = winnerIsFirst(handPair[0], handPair[1]);
            if (winnerIsFirst) {
                counter++;
            }
        }

        return counter;
    }

    private static String[][][] parse(String filename) {
        List<String[]> handsList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            while (line != null) {
                handsList.add(line.split(" "));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[][][] hands = new String[handsList.size()][][];
        for (int i = 0; i < hands.length; i++) {
            hands[i] = parseHands(handsList.get(i));
        }

        return hands;
    }

    private static String[][] parseHands(String[] twoHands) {
        String[] hand1 = new String[5];
        String[] hand2 = new String[5];
        System.arraycopy(twoHands, 0, hand1, 0, 5);
        System.arraycopy(twoHands, 5, hand2, 0, 5);
        return new String[][] {hand1, hand2};
    }

    private static boolean winnerIsFirst(String[] hand1, String[] hand2) {
        int[][] multiples1 = multiplesOfCards(hand1);
        int[][] multiples2 = multiplesOfCards(hand2);

        int straightFlush1 = straightFlush(multiples1, hand1);
        int straightFlush2 = straightFlush(multiples2, hand2);
        if (straightFlush1 < straightFlush2) return false;
        else if (straightFlush1 > straightFlush2) return true;

        int fourOfAKind1 = fourOfAKind(multiples1);
        int fourOfAKind2 = fourOfAKind(multiples2);
        if (fourOfAKind1 > 0 && fourOfAKind1 == fourOfAKind2) System.out.println("SAME 4");
        if (fourOfAKind1 < fourOfAKind2) return false;
        else if (fourOfAKind1 > fourOfAKind2) return true;

        int[] fullHouse1 = fullHouse(multiples1);
        int[] fullHouse2 = fullHouse(multiples2);
        if (fullHouse1[0] < fullHouse2[0]) return false;
        else if (fullHouse1[0] > fullHouse2[0]) return true;
        else {
            if (fullHouse1[1] < fullHouse2[1]) return false;
            else if (fullHouse1[1] > fullHouse2[1]) return true;
        }

        boolean flush1 = flush(hand1);
        boolean flush2 = flush(hand2);
        if (flush1 && flush2) System.out.println("BOTH FLUSH");
        if (!flush1 && flush2) return false;
        else if (flush1 && !flush2) return true;

        int straight1 = straight(multiples1);
        int straight2 = straight(multiples2);
        if (straight1 > 0 && straight2 > 0) System.out.println("BOTH STRAIGHT");
        if (straight1 < straight2) return false;
        else if (straight1 > straight2) return true;

        int threeOfAKind1 = threeOfAKind(multiples1);
        int threeOfAKind2 = threeOfAKind(multiples2);
        if (threeOfAKind1 < threeOfAKind2) return false;
        else if (threeOfAKind1 > threeOfAKind2) return true;

        int[] twoPairs1 = pairs(multiples1);
        int[] twoPairs2 = pairs(multiples2);
        if (!ArrayFunctions.contains(-1, twoPairs1) || !ArrayFunctions.contains(-1, twoPairs2)) {
            if (twoPairs1[1] == -1) return false;
            else if (twoPairs2[1] == -1) return true;
            else if (twoPairs1[0] < twoPairs2[0]) return false;
            else if (twoPairs1[0] > twoPairs2[0]) return true;
            else if (twoPairs1[1] < twoPairs2[1]) return false;
            else if (twoPairs1[1] > twoPairs2[1]) return true;
        }

        int pair1 = highestPair(multiples1);
        int pair2 = highestPair(multiples2);
        if (pair1 < pair2) return false;
        else if (pair1 > pair2) return true;

        int[] highCards1 = highestCardScores(multiples1);
        int[] highCards2 = highestCardScores(multiples2);
        if (highCards1.length != highCards2.length) throw new RuntimeException("BAD: " + Arrays.toString(hand1) + ", " + Arrays.toString(hand2));
        for (int i = 0; i < highCards1.length; i++) {
            if (highCards1[i] < highCards2[i]) return false;
            else if (highCards1[i] > highCards2[i]) return true;
        }

        throw new RuntimeException("DRAW");
    }

    private static int cardValue(String card) {
        char[] order = {' ', ' ', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        for (int i = 0; i < order.length; i++) {
            if (order[i] == card.charAt(0)) return i;
        }
        return -1;
    }
    private static int[][] multiplesOfCards(String[] hand) {
        List<List<Integer>> multiples = new ArrayList<>();
        boolean[] checked = new boolean[5];

        for (int starting = 0; starting < hand.length; starting++) {
            if (checked[starting]) continue;
            List<Integer> startingMultiples = new ArrayList<>();
            int startingValue = cardValue(hand[starting]);
            startingMultiples.add(startingValue);

            for (int i = starting+1; i < hand.length; i++) {
                if (startingValue == cardValue(hand[i])) {
                    checked[i] = true;
                    startingMultiples.add(startingValue);
                }
            }

            multiples.add(startingMultiples);
        }

        if (multiples.isEmpty()) return new int[0][];
        return Converter.deepListToArr(multiples);
    }
    private static int[] highestCardScores(int[][] multiples) {
        List<Integer> highCardsList = new ArrayList<>();

        for (int[] multiple : multiples) {
            if (multiple.length != 1) continue;
            highCardsList.add(multiple[0]);
        }

        int[] highCards = Converter.listToArr(highCardsList);
        return ArrayFunctions.reverseArray(ArrayFunctions.mergeSort(highCards));
    }
    private static int highestPair(int[][] multiples) {
        int[] pairs = pairs(multiples);
        if (pairs.length == 0) return -1;
        return pairs[0];
    }
    private static int[] pairs(int[][] multiples) {
        int[] pairs = new int[2];
        Arrays.fill(pairs, -1);
        int index = 0;

        for (int[] multiple : multiples) {
            if (multiple.length == 2) {
                pairs[index] = multiple[0];
                index++;
            }
        }

        return ArrayFunctions.reverseArray(ArrayFunctions.mergeSort(pairs));
    }
    private static int threeOfAKind(int[][] multiples) {
        for (int[] multiple : multiples) {
            if (multiple.length == 3) return multiple[0];
        }

        return -1;
    }
    private static int straight(int[][] multiples) {
        if (multiples.length != 5) return -1;
        int[] numbers = new int[5];

        for (int i = 0; i < multiples.length; i++) {
            numbers[i] = multiples[i][0];
        }

        numbers = ArrayFunctions.mergeSort(numbers);
        if (numbers[4] - numbers[0] == 4) return numbers[4];
        return -1;
    }
    private static boolean flush(String[] hand) {
        char suit = hand[0].charAt(1);

        for (int i = 1; i < hand.length; i++) {
            if (hand[i].charAt(1) != suit) return false;
        }

        return true;
    }
    private static int[] fullHouse(int[][] multiples) {
        int hasTwo = -1;
        int hasThree = -1;

        for (int[] multiple : multiples) {
            if (multiple.length == 2) hasTwo = multiple[0];
            else if (multiple.length == 3) hasThree = multiple[0];
        }

        if (hasTwo == -1 || hasThree == -1) return new int[] {-1, -1};
        return new int[] {hasThree, hasTwo};
    }
    private static int fourOfAKind(int[][] multiples) {
        for (int[] multiple : multiples) {
            if (multiple.length == 4) return multiple[0];
        }

        return -1;
    }
    private static int straightFlush(int[][] multiples, String[] hand) {
        if (!flush(hand)) return -1;
        return straight(multiples);
    }
}
