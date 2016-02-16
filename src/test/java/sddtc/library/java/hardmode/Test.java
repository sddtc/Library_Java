package sddtc.library.java.hardmode;

import org.junit.Assert;
import sddtc.library.java.object.Interval;
import sddtc.library.java.object.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sddtc on 16/1/20.
 */
public class Test {

    private Solutions solutions = new Solutions();

    @org.junit.Test
    public void maxPoints() {
        Point p1 = new Point();
        Point p2 = new Point(-1, -1);
        Point p3 = new Point(-3, -3);

        Point[] points = {p1, p2, p3};
        System.out.println(solutions.maxPoints(points));
    }


    @org.junit.Test
    public void isMatch() {
        Assert.assertTrue("1", solutions.isMatch("cabab", "*ab"));
        Assert.assertTrue("1", solutions.isMatch("a", "a*"));
        Assert.assertTrue("1", solutions.isMatch("", "*"));
        Assert.assertFalse("1", solutions.isMatch("aa", "ab"));
        Assert.assertTrue("2", solutions.isMatch("aa", "aa"));
        Assert.assertFalse("3", solutions.isMatch("aaa", "aa"));
        Assert.assertTrue("4", solutions.isMatch("aa", "*"));
        Assert.assertTrue("5", solutions.isMatch("aa", "a*"));
        Assert.assertTrue("6", solutions.isMatch("ab", "?*"));
        Assert.assertFalse("7", solutions.isMatch("aab", "c*a*b"));
    }

    @org.junit.Test
    public void minWindow() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(solutions.minWindow(s, t));

        String ss = "eeeeeeeeebadbaccb";
        String tt = "abc";
        System.out.println(solutions.minWindow(ss, tt));
    }

    @org.junit.Test
    public void isInterleave() {
        Assert.assertTrue(solutions.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        Assert.assertFalse(solutions.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        Assert.assertFalse(solutions.isInterleave("a", "", "aa"));
        Assert.assertFalse(solutions.isInterleave("", "", "a"));
        Assert.assertTrue(solutions.isInterleave("a", "", "a"));
        Assert.assertFalse(solutions.isInterleave("a", "b", "aba"));
    }


    @org.junit.Test
    public void interval() {
        //demo one
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1,3));
        list.add(new Interval(6,9));
        Interval newInterval = new Interval(2, 5);
        List<Interval> result = solutions.insert(list, newInterval);

        for (Interval interval : result) {
            System.out.print("[" + interval.start + "," + interval.end + "],");
        }

        System.out.println();

        //demo two
        List<Interval> list2 = new ArrayList<>();

        list2.add(new Interval(1,2));
        list2.add(new Interval(3,5));
        list2.add(new Interval(6,7));
        list2.add(new Interval(8,10));
        list2.add(new Interval(12,16));

        Interval newInterval2 = new Interval(4, 9);
        List<Interval> result2 = solutions.insert(list2, newInterval2);

        for (Interval interval : result2) {
            System.out.print("[" + interval.start + "," + interval.end + "],");
        }
    }

//    @org.junit.Test
//    public void maxNumber() {
//        int[] nums1 = {3, 4, 6, 5};
//        int[] nums2 = {9, 1, 2, 5, 8, 3};
//        int k = 5;
//
//        int[] result = solutions.maxNumber(nums1, nums2, k);
//        for(int i=0;i<result.length;i++) {
//            System.out.print(result[i]+",");
//        }
//    }
}
