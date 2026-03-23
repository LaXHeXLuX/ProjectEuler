package euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PE_054 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        String file = "src/euler/inputs/PE_054_poker.txt";
        String[][][] hands = parse(file);

        int counter = 0;

        for (String[][] handPair : hands) {
            boolean p1Wins = p1Wins(handPair[0], handPair[1]);
            if (p1Wins) {
                counter++;
            }
        }

        return String.valueOf(counter);
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

    private static boolean p1Wins(String[] h1, String[] h2) {
        boolean flush1 = flush(h1);
        int[] values1 = new int[13];
        for (String card : h1) {
            values1[cardValue(card)]++;
        }

        boolean flush2 = flush(h2);
        int[] values2 = new int[13];
        for (String card : h2) {
            values2[cardValue(card)]++;
        }

        boolean p1HasHighCard = false;
        for (int i = values1.length-1; i > 0; i--) {
            if (values1[i] != values2[i]) {
                p1HasHighCard = values1[i] > values2[i];
                break;
            }
        }

        int straight1 = straight(values1);
        int straight2 = straight(values2);

        if (straight1 > 0 && flush1 && straight2 > 0 && flush2) {
            return straight1 > straight2;
        }
        else if (straight1 > 0 && flush1) return true;
        else if (straight2 > 0 && flush2) return false;

        int[] ofAKind1 = ofAKind(values1);
        int[] ofAKind2 = ofAKind(values2);

        if (ofAKind1[0] == 4 && ofAKind2[0] == 4) {
            if (ofAKind1[1] > ofAKind2[1]) return true;
            if (ofAKind1[1] < ofAKind2[1]) return false;
            return p1HasHighCard;
        }
        else if (ofAKind1[0] == 4) return true;
        else if (ofAKind2[0] == 4) return false;

        int[] fullHouse1 = fullHouse(values1);
        int[] fullHouse2 = fullHouse(values2);
        if (fullHouse1[0] >= 0 || fullHouse2[0] >= 0) {
            if (fullHouse1[0] > fullHouse2[0]) return true;
            if (fullHouse1[0] < fullHouse2[0]) return false;
            return fullHouse1[1] > fullHouse2[1];
        }

        if (flush1 && !flush2) return true;
        if (!flush1 && flush2) return false;
        if (straight1 >= 0 || straight2 >= 0) {
            return straight1 > straight2;
        }

        if (ofAKind1[0] == 3 && ofAKind2[0] == 3) {
            if (ofAKind1[1] > ofAKind2[1]) return true;
            if (ofAKind1[1] < ofAKind2[1]) return false;
            return p1HasHighCard;
        }
        else if (ofAKind1[0] == 3) return true;
        else if (ofAKind2[0] == 3) return false;

        int[] twoPair1 = twoPair(values1);
        int[] twoPair2 = twoPair(values2);
        if (twoPair1[0] >= 0 || twoPair2[0] >= 0) {
            if (twoPair1[0] > twoPair2[0]) return true;
            if (twoPair1[0] < twoPair2[0]) return false;
            if (twoPair1[1] > twoPair2[1]) return true;
            if (twoPair1[1] < twoPair2[1]) return false;
            return p1HasHighCard;
        }

        if (ofAKind1[0] == 2 && ofAKind2[0] == 2) {
            if (ofAKind1[1] > ofAKind2[1]) return true;
            if (ofAKind1[1] < ofAKind2[1]) return false;
            return p1HasHighCard;
        }
        else if (ofAKind1[0] == 2) return true;
        else if (ofAKind2[0] == 2) return false;

        return p1HasHighCard;
    }

    private static int[] twoPair(int[] values) {
        int[] twoPair = {-1, -1};
        int i;
        for (i = values.length-1; i >= 0; i--) {
            if (values[i] == 2) {
                twoPair[0] = i;
                break;
            }
        }
        if (twoPair[0] == -1) return twoPair;
        for (i--; i >= 0; i--) {
            if (values[i] == 2) {
                twoPair[1] = i;
                return twoPair;
            }
        }
        return new int[] {-1, -1};
    }

    private static int[] fullHouse(int[] values) {
        int[] fullHouse = {-1, -1};
        int i;
        for (i = 0; i < values.length; i++) {
            if (values[i] == 3) {
                fullHouse[0] = i;
                break;
            }
        }
        if (fullHouse[0] == -1) return fullHouse;
        for (; i < values.length; i++) {
            if (values[i] == 2) {
                fullHouse[1] = i;
                return fullHouse;
            }
        }
        return new int[] {-1, -1};
    }

    private static int[] ofAKind(int[] values) {
        int[] ofAKind = {1, -1};
        for (int i = values.length-1; i >= 0; i--) {
            if (values[i] > ofAKind[0]) {
                ofAKind[0] = values[i];
                ofAKind[1] = i;
            }
        }
        return ofAKind;
    }

    private static boolean flush(String[] hand) {
        char suit = hand[0].charAt(1);
        for (int i = 1; i < hand.length; i++) {
            if (hand[i].charAt(1) != suit) return false;
        }
        return true;
    }

    private static int straight(int[] values) {
        int streak = 0;
        for (int i = 0; i < values.length; i++) {
            int v = values[i];
            if (v > 1) return -1;
            if (v == 0) streak = 0;
            else {
                streak++;
                if (streak == 5) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static int cardValue(String card) {
        return switch (card.charAt(0)) {
            case 'A' -> 12;
            case 'K' -> 11;
            case 'Q' -> 10;
            case 'J' -> 9;
            case 'T' -> 8;
            default -> card.charAt(0) - '2';
        };
    }
}
