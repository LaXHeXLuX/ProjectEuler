package euler;

import utils.Parser;

public class PE_089 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        String file = "src/euler/inputs/PE_089_roman.txt";
        String[] numerals = Parser.parseStrings(file);
        int sum = 0;
        for (String numeral : numerals) {
            sum += savedCharacterCount(numeral);
        }
        return String.valueOf(sum);
    }

    private static int savedCharacterCount(String numeral) {
        return numeral.length() - minimalRomanLength(romanToInt(numeral));
    }

    private static int romanToInt(String numeral) {
        int sum = 0;

        char last = numeral.charAt(0);
        for (int i = 1; i < numeral.length(); i++) {
            char c = numeral.charAt(i);
            if (last == '-') {
                last = c;
            }
            else if (value(last) < value(c)) {
                sum += value(c) - value(last);
                last = '-';
            }
            else {
                sum += value(last);
                last = c;
            }
        }
        if (last != '-') sum += value(last);
        return sum;
    }

    private static int minimalRomanLength(int n) {
        int length = n / 1000;
        n %= 1000;
        int hundreds = n / 100;
        if (hundreds % 5 == 4) length += 2;
        else length += hundreds / 5 + hundreds % 5;
        n %= 100;
        int tens = n / 10;
        if (tens % 5 == 4) length += 2;
        else length += tens / 5 + tens % 5;
        n %= 10;
        if (n % 5 == 4) length += 2;
        else length += n / 5 + n % 5;
        return length;
    }

    private static int value(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }
}
