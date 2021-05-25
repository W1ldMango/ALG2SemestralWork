package Utils;

public class MyCalendar {


    private static int[] daysInMonths = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int day, mouth, year;
    private int currentMonth, currentDay, currentWeekDay;

    MyCalendar(int day, int mouth, int year) {
        this.day = day;
        this.mouth = mouth;
        this.year = year;
        this.currentDay = day;
        this.currentMonth = mouth;
        this.currentWeekDay = getWeekDay(day, mouth, year);
    }



    /**
     *
     * @param day is a day of week
     * @param month is a mouth of year
     * @param year just a year
     * @return day of week 0-6
     */
    public int getWeekDay(int day, int month, int year) {
        if (month < 3) {
            month += 12;
            year -= 1;
        }
        int k = year % 100;
        int j = year / 100;
        return ((((day + (((month + 1) * 26) / 10) + k + (k / 4) + (j / 4) + (5 * j)) % 7) + 5) % 7) + 1;
    }

    /**
     * A method that displays an entire calendar, just like on a computer
     * @return full calendar
     */

    public String displayCalendar() {
        StringBuilder s = new StringBuilder("Mo Tu We Th Fr Sa Su\n");
        int weekCounter = 0;
        int monthStart = getWeekDay(1, mouth, year);
        for (int day = 1; day <= daysInMonths[currentMonth] + monthStart - 1; day++) {
            int realDay = day - monthStart + 1;
            if ((day + weekCounter) % 8 == 0) {
                s.append("\n");
                weekCounter++;
            }

            if (day < monthStart && monthStart != 1) {
                s.append("   ");
            } else if (realDay == currentDay) {
                s.append(String.format("%s%s", Colors.BG_RED + Colors.BLACK + (String.format("%2d", realDay)), Colors.RESET_COLOR + " "));
            } else {
                s.append(String.format("%2d ", realDay));
            }
        }
        return s.toString();
    }
}

