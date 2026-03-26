package euler;

import utils.PolygonalNumber;

public class PE_061 {
    private static int[] numbers;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 6;
        cyclicPolygonalNumbers(n);
        return String.valueOf(sum());
    }

    private static int sum() {
        int sum = 0;
        for (int i : numbers) sum += i;
        return sum;
    }

    private static void cyclicPolygonalNumbers(int n) {
        numbers = new int[n];
        int mask = (1 << n) - 2;

        int i = 1;
        numbers[0] = (int) PolygonalNumber.polygonalNumber(3, i);

        while (numbers[0] < 1000) {
            i++;
            numbers[0] = (int) PolygonalNumber.polygonalNumber(3, i);
        }

        while (numbers[0] < 10_000) {
            if ((numbers[0] % 100) / 10 != 0) {
                cyclicPolynomialNumbersRec(1, mask);
                if (numbers[numbers.length-1] > 0) return;
            }
            i++;
            numbers[0] = (int) PolygonalNumber.polygonalNumber(3, i);
        }
    }

    private static void cyclicPolynomialNumbersRec(int count, int mask) {
        int start = numbers[count-1]%100;
        if (count == numbers.length-1) {
            int last = 100*start + numbers[0]/100;
            int i = 0;
            while (mask > 0) {
                if ((mask & 1) == 1) break;
                mask >>= 1;
                i++;
            }
            if (PolygonalNumber.isPolygonalNumber(i+3, last)) {
                numbers[numbers.length-1] = last;
            }
            return;
        }

        int initialMask = mask;
        mask <<= 1;
        int s = 2;
        while (mask > 0) {
            mask >>= 1;
            s++;
            if ((mask & 1) == 0) continue;
            int i = 1;
            int poly = (int) PolygonalNumber.polygonalNumber(s, i);
            while (poly/100 < start) {
                i++;
                poly = (int) PolygonalNumber.polygonalNumber(s, i);
            }
            while (poly/100 == start) {
                if ((poly % 100) / 10 != 0) {
                    numbers[count] = poly;
                    cyclicPolynomialNumbersRec(count+1, initialMask ^ 1 << (s-3));
                    if (numbers[numbers.length-1] > 0) return;
                }
                i++;
                poly = (int) PolygonalNumber.polygonalNumber(s, i);
            }
        }
        numbers[count] = 0;
    }
}
