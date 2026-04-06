package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PE_119 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int count = 30;
        List<Long> digitSumPowerNumbers = digitSumPowerNumbers(count);
        return String.valueOf(digitSumPowerNumbers.getLast());
    }

    private static List<Long> digitSumPowerNumbers(int count) {
        List<Long> digitSumPowerNumbers = new ArrayList<>();
        if (count == 0) return digitSumPowerNumbers;

        int digits = 1;
        int iLimit = 9*digits;
        while (digitSumPowerNumbers.size() < count) {
            for (int i = 2; i <= iLimit; i++) {
                int powerStart = (int) Math.max(2, Math.ceil((digits - 1) / Math.log10(i)));
                int powerLimit = (int) (digits / Math.log10(i));
                long result = 1;
                for (int j = 0; j < powerStart; j++) result *= i;
                for (int power = powerStart; power <= powerLimit; power++) {
                    int digitSum = Diophantine.digitSum(result);
                    if (digitSum == i) digitSumPowerNumbers.add(result);
                    result *= i;
                }
            }

            digits++;
            iLimit = 9*digits;
        }

        Collections.sort(digitSumPowerNumbers);
        return digitSumPowerNumbers.subList(0, count);
    }
}
