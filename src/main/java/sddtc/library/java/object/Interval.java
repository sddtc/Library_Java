package sddtc.library.java.object;

/**
 * Created by sddtc on 16/2/15.
 */
public class Interval {
    public int start;
    public int end;

    Interval() {
        start = 0;
        end = 0;
    }

    public Interval(int s, int e) {
        start = s;
        end = e;
    }
}