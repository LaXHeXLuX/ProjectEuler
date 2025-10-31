package src.euler;

import util.Parser;

public class PE_089 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        String file = "src/euler/inputs/PE_089_roman.txt";
        String[] numerals = Parser.parseStrings(file);
        int sum = 0;
        for (String numeral : numerals) {
            sum += savedCharacterCount(numeral);
        }
        return sum;
    }

    private static int savedCharacterCount(String numeral) {
        return numeral.length() - toMinimalRoman(romanToInt(numeral)).length();
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

    private static String toMinimalRoman(int n) {
        String numeral = "";
        numeral += "M".repeat(n / 1000);
        n %= 1000;
        int hundreds = n / 100;
        if (hundreds == 9) numeral += "CM";
        else if (hundreds == 4) numeral += "CD";
        else numeral += "D".repeat(hundreds / 5) + "C".repeat(hundreds % 5);
        n %= 100;
        int tens = n / 10;
        if (tens == 9) numeral += "XC";
        else if (tens == 4) numeral += "XL";
        else numeral += "L".repeat(tens / 5) + "X".repeat(tens % 5);
        n %= 10;
        if (n == 9) numeral += "IX";
        else if (n == 4) numeral += "IV";
        else numeral += "V".repeat(n / 5) + "I".repeat(n % 5);
        return numeral;
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
