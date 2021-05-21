package utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class that is responsible for displaying the calendar from the MyCalendar class
 */

public class DateDisplay {


    public String displayCalendarNow() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MM yyyy");
        String form = now.format(format);
        String[] str = form.split(" ");
        int[] convert = new int[str.length];
        for (int i = 0; i < convert.length; i++) {
            convert[i] = Integer.parseInt(str[i]);
        }
        MyCalendar my = new MyCalendar(convert[0],convert[1],convert[2]);
        return my.displayCalendar();
    }
    public String displayCalendarEnd(int days) {
        LocalDate now = LocalDate.now().plusDays(days);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MM yyyy");
        String form = now.format(format);
        String[] str = form.split(" ");
        int[] convert = new int[str.length];
        for (int i = 0; i < convert.length; i++) {
            convert[i] = Integer.parseInt(str[i]);
        }
        MyCalendar my = new MyCalendar(convert[0],convert[1],convert[2]);
        return my.displayCalendar();
    }
}
