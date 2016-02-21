package sddtc.library.java.hardmode;

import sddtc.library.java.object.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * something amazing and fast
 * coooool!!!
 * <p/>
 * Created by sddtc on 16/2/21.
 */
public class FastSolutions {
    /**
     * no.56 https://leetcode.com/problems/merge-intervals/
     * <p/>
     * <p/>
     * For example,
     * Given [1,3],[2,6],[8,10],[15,18],
     * return [1,6],[8,10],[15,18].
     *
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ans = new ArrayList<>();
        int[] s = new int[intervals.size()];
        int[] e = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            s[i] = intervals.get(i).start;
            e[i] = intervals.get(i).end;
        }
        Arrays.sort(s);    //create an sorted array which are all start values
        Arrays.sort(e);   //create an sorted array which are all end values
        int start = 0;
        int end = 0;
        boolean flag = true;  //judge the start value
        if (intervals.size() == 1) return intervals;
        for (int i = 0; i < intervals.size() - 1; i++) {
            if (s[i] <= e[i] && flag) {   //update the first value of start
                start = s[i];
                flag = false;
            }
            if (s[i + 1] > e[i]) {      //update the last value of end
                end = e[i];
                ans.add(new Interval(start, end));
                flag = true;
            }
            if (s[i + 1] <= e[i] && i == intervals.size() - 2) {  //deal with the last index
                end = e[i + 1];
                ans.add(new Interval(start, end));
            }
            if (i == intervals.size() - 2 && flag == true) {
                start = s[i + 1];
                end = e[i + 1];
                ans.add(new Interval(start, end));
            }
        }
        return ans;
    }
}
