package sddtc.library.java.hardmode;

import org.junit.Assert;
import sddtc.library.java.object.Interval;
import sddtc.library.java.object.ListNode;
import sddtc.library.java.object.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sddtc on 16/1/20.
 */
public class Test {

    private Solutions solutions = new Solutions();
    private FastSolutions fastSolutions = new FastSolutions();

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
        list.add(new Interval(1, 3));
        list.add(new Interval(6, 9));
        Interval newInterval = new Interval(2, 5);
        List<Interval> result = solutions.insert(list, newInterval);

        for (Interval interval : result) {
            System.out.print("[" + interval.start + "," + interval.end + "],");
        }

        System.out.println();

        //demo two
        List<Interval> list2 = new ArrayList<>();

        list2.add(new Interval(1, 2));
        list2.add(new Interval(3, 5));
        list2.add(new Interval(6, 7));
        list2.add(new Interval(8, 10));
        list2.add(new Interval(12, 16));

        Interval newInterval2 = new Interval(4, 9);
        List<Interval> result2 = solutions.insert(list2, newInterval2);

        for (Interval interval : result2) {
            System.out.print("[" + interval.start + "," + interval.end + "],");
        }
    }

    @org.junit.Test
    public void mergeKLists() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(8);
        ListNode l3 = new ListNode(4);
        l3.next = new ListNode(5);

        ListNode[] nodes = new ListNode[3];
        nodes[0] = l1;
        nodes[1] = l2;
        nodes[2] = l3;

        ListNode result = solutions.mergeKLists(nodes);

        while (null != result) {
            System.out.println(result.val);
            result = result.next;
        }


        ListNode[] listNode = new ListNode[1];
        ListNode result2 = solutions.mergeKLists(listNode);

        while (null != result2) {
            System.out.println(result2.val);
            result2 = result2.next;
        }

        ListNode[] listNode2 = new ListNode[2];
        ListNode result3 = solutions.mergeKLists(listNode2);

        while (null != result3) {
            System.out.println(result3.val);
            result3 = result3.next;
        }

        ListNode[] listNode3 = new ListNode[2];
        listNode3[0] = null;
        listNode3[1] = new ListNode(1);
        ListNode result4 = solutions.mergeKLists(listNode3);

        while (null != result4) {
            System.out.println(result4.val);
            result4 = result4.next;
        }

        ListNode[] listNode4 = new ListNode[4];
        listNode4[0] = null;
        listNode4[1] = new ListNode(1);
        listNode4[2] = null;
        listNode4[3] = new ListNode(2);

        ListNode result5 = solutions.mergeKLists(listNode4);

        while (null != result5) {
            System.out.println(result5.val);
            result5 = result5.next;
        }

    }

    @org.junit.Test
    public void merge() {
        //Given [1,3],[2,6],[8,10],[15,18],
        //return [1,6],[8,10],[15,18].
        List<Interval> demo1 = new ArrayList<>();
        demo1.add(new Interval(1, 3));
        demo1.add(new Interval(2, 6));
        demo1.add(new Interval(8, 10));
        demo1.add(new Interval(15, 18));

        List<Interval> result1 = fastSolutions.merge(demo1);
        for (Interval i : result1) {
            System.out.print("[" + i.start + "," + i.end + "]");
        }

        //2
        System.out.println();
        List<Interval> demo2 = new ArrayList<>();
        demo2.add(new Interval(1, 4));
        demo2.add(new Interval(0, 0));

        List<Interval> result2 = fastSolutions.merge(demo2);
        for (Interval i : result2) {
            System.out.print("[" + i.start + "," + i.end + "]");
        }
    }

    @org.junit.Test
    public void isScramble() {
        String s1 = "great";
        String s2 = "rgate";

        System.out.println(solutions.isScramble(s1, s2));
    }

    @org.junit.Test
    public void minDistance() {
        String one = "abc";
        String two = "bce";

        int step = solutions.minDistance(one, two);
        System.out.print(step);
    }

    @org.junit.Test
    public void simplePaths() {
        String p1 = "/home/";
        String p2 = "/a/./b/../../c/";
        String p3 = "///";
        String p4 = "/a/...";

        System.out.println(solutions.simplifyPath(p1));
        System.out.println(solutions.simplifyPath(p2));
        System.out.println(solutions.simplifyPath(p3));
        System.out.println(solutions.simplifyPath(p4));
    }

    @org.junit.Test
    public void bitCount() {
        int num = 5;
        int[] result = solutions.countBits(num);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + ",");
        }
    }

    @org.junit.Test
    public void countDigit() {
        int n = 1410065408;
        System.out.print(solutions.countDigitOne(n));
    }

    @org.junit.Test
    public void removeDuplicateLetters() {
        System.out.println((int)'a');
        System.out.println((char)97);
        String s1 = "bcabc";
        String s2 = "cbacdcbc";
        System.out.println(solutions.removeDuplicateLetters(s1));
        System.out.println(solutions.removeDuplicateLetters(s2));
    }
}
