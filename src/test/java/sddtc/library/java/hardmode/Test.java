package sddtc.library.java.hardmode;

import org.junit.Assert;
import sddtc.library.java.object.Point;

/**
 * Created by sddtc on 16/1/20.
 */
public class Test {

    private Solutions solutions = new Solutions();

    @org.junit.Test
    public void maxPoints() {
        Point p1 = new Point();
        Point p2 = new Point(-1,-1);
        Point p3 = new Point(-3,-3);

        Point[] points = {p1, p2, p3};
        System.out.println(solutions.maxPoints(points));
    }


    @org.junit.Test
    public void isMatch() {
        Assert.assertTrue("1", solutions.isMatch("cabab", "*ab"));
        Assert.assertTrue("1", solutions.isMatch("a", "a*"));
        Assert.assertTrue("1", solutions.isMatch("", "*"));
        Assert.assertFalse("1",solutions.isMatch("aa", "ab"));
        Assert.assertTrue("2", solutions.isMatch("aa", "aa"));
        Assert.assertFalse("3",solutions.isMatch("aaa", "aa"));
        Assert.assertTrue("4",solutions.isMatch("aa", "*"));
        Assert.assertTrue("5",solutions.isMatch("aa", "a*"));
        Assert.assertTrue("6",solutions.isMatch("ab", "?*"));
        Assert.assertFalse("7",solutions.isMatch("aab", "c*a*b"));
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
//        Assert.assertTrue(solutions.isInterleave("aabcc","dbbca","aadbbcbcac"));
//        Assert.assertFalse(solutions.isInterleave("aabcc", "dbbca","aadbbbaccc"));
//        Assert.assertFalse(solutions.isInterleave("a", "", "aa"));
//        Assert.assertFalse(solutions.isInterleave("", "","a"));
//        Assert.assertTrue(solutions.isInterleave("a", "", "a"));
        Assert.assertFalse(solutions.isInterleave("a","b","aba"));
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
