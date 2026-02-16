package euler;

public class PE_019 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        return countingSundays();
    }

    private static int countingSundays() {
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // the day is monday, 01.01.1901
        int year = 1900;
        int weekDay = 0;
        int sundays = 0;
        while (year < 2001) {
            months[1] = isLeapYear(year) ? 29 : 28;
            for (int month : months) {
                if (year >= 1901 && weekDay == 6) {
                    sundays++;
                }
                weekDay = (weekDay + month) % 7;
            }
            year++;
        }
        return sundays;
    }

    private static boolean isLeapYear(int year) {
        if (year % 4 != 0) return false;
        if (year % 100 != 0) return true;
        return year % 400 == 0;
    }
}