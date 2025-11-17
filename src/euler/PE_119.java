package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PE_119 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int count = 30;
        List<Long> digitSumPowerNumbers = digitSumPowerNumbers(count);
        return digitSumPowerNumbers.getLast();
    }

    private static List<Long> digitSumPowerNumbers(int count) {
        List<Long> digitSumPowerNumbers = new ArrayList<>();
        if (count == 0) return digitSumPowerNumbers;

        long limit = 100;
        long iLimit = 1_000;
        while (digitSumPowerNumbers.size() < count) {
            for (int i = 2; i <= iLimit; i++) {
                int powerStart = (int) Math.max(2, Math.ceil(Math.log(limit / 100.0) / Math.log(i)));
                int powerLimit = (int) (Math.log(limit) / Math.log(i));
                for (int power = powerStart; power <= powerLimit; power++) {
                    long result = i;
                    for (int j = 1; j < power; j++) {
                        result *= i;
                    }
                    int digitSum = Diophantine.digitSum(result);
                    if (digitSum == i) digitSumPowerNumbers.add(result);
                }
            }

            limit *= 100;
            if (limit < 0) break;
        }

        Collections.sort(digitSumPowerNumbers);
        return digitSumPowerNumbers.subList(0, count);
    }
}
