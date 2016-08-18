package sddtc.library.java.other;

import java.util.Calendar;

/**
 * Author tuijiang
 * Date 16/8/18
 */
public class CalenderTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,7,17,5,0);
        System.out.println(calendar.getTimeInMillis());
    }
}
