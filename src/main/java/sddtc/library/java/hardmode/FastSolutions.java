package sddtc.library.java.hardmode;

import sddtc.library.java.object.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * something amazing and fast
 * coooool!!!
 * Created by sddtc on 16/2/21.
 */
public class FastSolutions {
    /**
     * no.56 https://leetcode.com/problems/merge-intervals/
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

    /**
     * no.218 https://leetcode.com/problems/the-skyline-problem/
     *
     * @param buildings
     * @return
     */
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> re = new ArrayList<>();
        if (buildings.length == 0)
            return re;
        int[] temp = new int[2];
        temp[0] = Integer.MIN_VALUE;
        temp[1] = 0;
        re.add(temp);
        int StartIndex = 0;
        for (int i = 0; i < buildings.length; i++) {
            int buildingLeft = buildings[i][0];
            int buildingRight = buildings[i][1];
            int buildingHeight = buildings[i][2];
            int index = findIndex(buildingLeft, re, StartIndex, re.size() - 1);
            StartIndex = update(re, index, buildingLeft, buildingRight, buildingHeight);
        }
        re.remove(0);
        return re;
    }

    private int findIndex(int buildingLeft, List<int[]> re, int StartIndex, int EndIndex) {
        int mid;
        while (StartIndex <= EndIndex) {
            mid = (StartIndex + EndIndex) / 2;
            if (re.get(mid)[0] == buildingLeft) {
                return mid;
            } else {
                if (re.get(mid)[0] < buildingLeft) {
                    StartIndex = mid + 1;
                } else {
                    EndIndex = mid - 1;
                }
            }
        }
        return StartIndex - 1;
    }

    private int update(List<int[]> re, int index, long buildingLeft, int buildingRight, int buildingHeight) {
        int newStart = index;

        for (int i = index; i < re.size(); i++) {
            if (i > 0 && re.get(i)[1] == re.get(i - 1)[1]) {
                re.remove(i);
                i--;
                continue;
            }
            long thisEnd = (i == re.size() - 1) ? Long.MAX_VALUE : re.get(i + 1)[0];
            int thisHeight = re.get(i)[1];
            if (buildingLeft > re.get(i)[0]) {
                if (buildingHeight > re.get(i)[1]) {
                    int[] temp = new int[2];
                    temp[0] = (int) buildingLeft;
                    temp[1] = buildingHeight;
                    re.add(i + 1, temp);
                    newStart = i + 1;
                    i++;
                }
            } else {
                if (buildingHeight > re.get(i)[1]) {
                    re.get(i)[1] = buildingHeight;
                    if (i > 0 && re.get(i - 1)[1] == buildingHeight) {
                        re.remove(i);
                        if (newStart == i)
                            newStart--;
                        i--;
                    }
                }
            }

            if (buildingRight < thisEnd) {
                if (buildingHeight > thisHeight) {
                    int[] temp = new int[2];
                    temp[0] = buildingRight;
                    temp[1] = thisHeight;
                    re.add(i + 1, temp);
                    i++;
                }
                break;
            } else {
                if (buildingRight == thisEnd) {
                    break;
                } else {
                    buildingLeft = thisEnd;
                }
            }
        }
        return newStart;
    }

    /**
     * no.224 https://leetcode.com/problems/basic-calculator/
     * operation:
     * 1:+
     * -1:-
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        if (null == s || s.equals("")) {
            return 0;
        }
        s = "(" + s + ")";
        int[] p = {0};
        return eval(s, p);
    }

    private int eval(String s, int[] p) {
        int value = 0;
        int i = p[0];
        int operation = 1;
        int number = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            switch (c) {
                case '+':
                    value = value + operation * number;
                    number = 0;
                    operation = 1;
                    i++;
                    break;
                case '-':
                    value = value + operation * number;
                    number = 0;
                    operation = -1;
                    i++;
                    break;
                case '(':
                    p[0] = i + 1;
                    value = value + operation * eval(s, p);
                    i = p[0];
                    break;
                case ')':
                    p[0] = i + 1;
                    return value + operation * number;
                case ' ':
                    i++;
                    continue;
                default:
                    number = number * 10 + c - '0';
                    i++;
            }
        }
        return value;
    }
}
