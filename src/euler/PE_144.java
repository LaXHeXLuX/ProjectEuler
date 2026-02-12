package euler;

import utils.Fraction;

import java.util.List;

public class PE_144 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static long PE() {
        int[] ellipseXYR = {4, 1, 10};
        List<Fraction<Long>> startPoint = List.of(new Fraction<>(0L), new Fraction<>(101L, 10L));
        List<Fraction<Long>> firstTouchPoint = List.of(new Fraction<>(14L, 10L), new Fraction<>(-96L, 10L));
        Fraction<Long> direction = direction(startPoint, firstTouchPoint);
        Fraction<Long> holeRadius = new Fraction<>(1L, 100L);

        return reflectionCount(ellipseXYR, firstTouchPoint, direction, holeRadius);
    }

    private static long reflectionCount(int[] ellipseXYR, List<Fraction<Long>> firstTouchPoint, Fraction<Long> direction, Fraction<Long> holeRadius) {
        List<Fraction<Long>> touchPoint = firstTouchPoint;
        long count = 0;
        while (!inHole(touchPoint, holeRadius)) {
            count++;
            direction = outgoingDirection(direction, touchPoint, ellipseXYR);
            touchPoint = nextTouchPoint(touchPoint, direction, ellipseXYR);
        }
        return count;
    }

    private static Fraction<Long> outgoingDirection(Fraction<Long> incomingDirection, List<Fraction<Long>> touchPoint, int[] ellipseXYR) {
        return null;
    }

    private static Fraction<Long> outgoingDirection(Fraction<Long> incomingDirection, Fraction<Long> reflectionSlope) {
        return null;
    }

    private static List<Fraction<Long>> nextTouchPoint(List<Fraction<Long>> touchPoint, Fraction<Long> direction, int[] ellipseXYR) {
        return null;
    }

    private static Fraction<Long> direction(List<Fraction<Long>> from, List<Fraction<Long>> to) {
        List<Fraction<Long>> vector = List.of(to.getFirst().subtract(from.getFirst()), to.getLast().subtract(from.getLast()));
        return vector.getLast().divide(vector.getFirst());
    }

    private static boolean inHole(List<Fraction<Long>> touchPoint, Fraction<Long> holeRadius) {
        boolean positiveY = touchPoint.getLast().compareTo(new Fraction<>(0L)) > 0;
        Fraction<Long> absoluteX = touchPoint.getFirst();
        if (absoluteX.num < 0L) {
            absoluteX.num = -absoluteX.num;
        }
        boolean xInHole = absoluteX.compareTo(holeRadius) < 0;
        return positiveY && xInHole;
    }
}
