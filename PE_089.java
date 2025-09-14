import util.Parser;

import java.util.Arrays;

public class PE_089 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        String file = "inputs/PE_089_roman.txt";
        String[] numerals = Parser.parseStrings(file);
        System.out.println(Arrays.toString(numerals));
        return -1;
    }
}
