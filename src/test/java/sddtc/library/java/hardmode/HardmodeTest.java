package sddtc.library.java.hardmode;

import org.junit.Assert;
import sddtc.library.java.object.Interval;
import sddtc.library.java.object.ListNode;
import sddtc.library.java.object.Point;
import sddtc.library.java.object.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HardmodeTest {
    private Solutions solutions = new Solutions();
    private FastSolutions fastSolutions = new FastSolutions();
    private sddtc.library.java.mediummode.Solutions mediumSolutions = new sddtc.library.java.mediummode.Solutions();
    private sddtc.library.java.easymode.Solutions easySolutions = new sddtc.library.java.easymode.Solutions();

    @Test
    public void should_return_maxPoints_three() {
        Point p1 = new Point();
        Point p2 = new Point(-1, -1);
        Point p3 = new Point(-3, -3);

        Point[] points = {p1, p2, p3};
        Assert.assertEquals(3, solutions.maxPoints(points));
    }


    @Test
    public void should_return_isMatch() {
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

    @Test
    public void should_return_minWindow() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        Assert.assertEquals("BANC", solutions.minWindow(s, t));

        String ss = "eeeeeeeeebadbaccb";
        String tt = "abc";
        Assert.assertEquals("bac", solutions.minWindow(ss, tt));
    }

    @Test
    public void should_return_isInterleave() {
        Assert.assertTrue(solutions.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        Assert.assertFalse(solutions.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        Assert.assertFalse(solutions.isInterleave("a", "", "aa"));
        Assert.assertFalse(solutions.isInterleave("", "", "a"));
        Assert.assertTrue(solutions.isInterleave("a", "", "a"));
        Assert.assertFalse(solutions.isInterleave("a", "b", "aba"));
    }


    @Test
    // TODO: 2017/7/3 test case 
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

    @Test
    // TODO: 2017/7/3 test case 
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

    @Test
    // TODO: 2017/7/3 test case 
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

    @Test
    public void should_return_isScramble_true() {
        String s1 = "great";
        String s2 = "rgate";

        Assert.assertTrue(solutions.isScramble(s1, s2));
    }

    @Test
    public void should_return_minDistance() {
        String one = "abc";
        String two = "bce";

        Assert.assertEquals(2, solutions.minDistance(one, two));
    }

    @Test
    public void should_return_simplePaths() {
        String p1 = "/home/";
        String p2 = "/a/./b/../../c/";
        String p3 = "///";
        String p4 = "/a/...";

        Assert.assertEquals("/home", solutions.simplifyPath(p1));
        Assert.assertEquals("/c", solutions.simplifyPath(p2));
        Assert.assertEquals("/", solutions.simplifyPath(p3));
        Assert.assertEquals("/a/...", solutions.simplifyPath(p4));
    }

    @Test
    public void should_return_bitCount() {
        int num = 5;
        int[] result = solutions.countBits(num);

        Assert.assertEquals(6, result.length);
        // TODO: 2017/7/3 test case
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + ",");
        }
    }

    @Test
    public void should_return_countDigit() {
        int n = 1410065408;

        Assert.assertEquals(1737167499, solutions.countDigitOne(n));
    }

    @Test
    public void should_return_removeDuplicateLetters() {
        String s1 = "bcabc";
        String s2 = "cbacdcbc";

        Assert.assertEquals("abc", solutions.removeDuplicateLetters(s1));
        Assert.assertEquals("acdb", solutions.removeDuplicateLetters(s2));
    }

    @Test
    public void should_return_postorder() {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        TreeNode left = new TreeNode(3);
        root.right = right;
        right.left = left;
        List<Integer> result = solutions.postorderTraversal(root);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals(3, result.get(0).intValue());
        Assert.assertEquals(2, result.get(1).intValue());
        Assert.assertEquals(1, result.get(2).intValue());
    }

    @Test
    public void should_return_median() {
        int[] n1 = {1, 2, 3};
        int[] n2 = {4};

        Assert.assertEquals(2.5, solutions.findMedianSortedArrays(n1, n2), 0);
    }

    @Test
    public void should_return_numberToWords() {
        int num1 = 123;
        int num2 = 12345;
        int num3 = 1234567;

        Assert.assertEquals("One Hundred Twenty Three", solutions.numberToWords(num1));
        Assert.assertEquals("Twelve Thousand Three Hundred Forty Five", solutions.numberToWords(num2));
        Assert.assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", solutions.numberToWords(num3));
    }

    @Test
    public void should_return_perfectSquare() {
        int num = 16;
        
        Assert.assertTrue(mediumSolutions.isPerfectSquare(num));
    }

    @Test
    // TODO: 2017/7/3 test case
    public void maxSlidingWindow() {
//        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums = {-7, -8, 7, 5, 7, 1, 6, 0};
        int[] result = solutions.maxSlidingWindow(nums, 4);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
        }
    }

    @Test
    public void should_return_powerOfTwo() {
        int n = 1;

        Assert.assertTrue(easySolutions.isPowerOfTwo(n));
    }

    @Test
    public void should_return_powerOfThree_false() {
        int n = 6;

        Assert.assertFalse(easySolutions.isPowerOfThree(n));
    }

    @Test
    public void numberOfString() {
        String s = "12";
//        s = "03421";
//        s = "1221304";
        // TODO: 2017/7/3 test case
        System.out.println(mediumSolutions.numDecodings(s));
    }
}
