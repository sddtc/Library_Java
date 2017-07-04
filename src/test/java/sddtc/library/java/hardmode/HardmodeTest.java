package sddtc.library.java.hardmode;

import org.junit.Assert;
import sddtc.library.java.object.Interval;
import sddtc.library.java.object.ListNode;
import sddtc.library.java.object.Point;
import sddtc.library.java.object.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HardmodeTest {

    private Solutions solutions = new Solutions();

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
        Assert.assertTrue(solutions.isInterleave("a", "b", "aba"));
    }

    @Test
    /* Given [1,3],[6,9],[2,5]
     * return [1,5],[6,9] */
    public void should_merge_two_intervals() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 3));
        list.add(new Interval(6, 9));
        Interval newInterval = new Interval(2, 5);
        List<Interval> result = solutions.insert(list, newInterval);

        Assert.assertEquals(new Interval(1,5), result.get(0));
        Assert.assertEquals(new Interval(6,9), result.get(1));
    }


    @Test
    /* Given  [1,2],[3,5],[6,7],[8,10],[12,16]
     * return [1,2],[3,10],[12,16] */
    public void should_merge_five_intervals_to_three_intervals() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 2));
        list.add(new Interval(3, 5));
        list.add(new Interval(6, 7));
        list.add(new Interval(8, 10));
        list.add(new Interval(12, 16));

        Interval newInterval = new Interval(4, 9);
        List<Interval> result = solutions.insert(list, newInterval);

        Assert.assertEquals(new Interval(1, 2), result.get(0));
        Assert.assertEquals(new Interval(3, 10), result.get(1));
        Assert.assertEquals(new Interval(12,16), result.get(2));
    }

    @Test
    public void should_return_mergeKLists_six_size() {
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

        ListNode listNode = solutions.mergeKLists(nodes);

        int height = 0;
        while (null != listNode) {
            height++;
            listNode = listNode.next;
        }

        Assert.assertEquals(6, height);
    }

    @Test
    public void should_return_mergeKLists_is_zero() {
        ListNode[] listNodes = new ListNode[2];
        ListNode listNode = solutions.mergeKLists(listNodes);

        int height = 0;
        while (null != listNode) {
            height++;
            listNode = listNode.next;
        }

        Assert.assertEquals(0, height);
    }

    @Test
    public void should_return_mergeKLists_one_size() {
        ListNode[] listNodes = new ListNode[2];
        listNodes[0] = null;
        listNodes[1] = new ListNode(1);
        ListNode listNode = solutions.mergeKLists(listNodes);

        int height = 0;
        while (null != listNode) {
            height++;
            listNode = listNode.next;
        }

        Assert.assertEquals(1, height);
    }

    @Test
    public void should_return_mergeKLists_two_size() {
        ListNode[] listNodes = new ListNode[4];
        listNodes[0] = null;
        listNodes[1] = new ListNode(1);
        listNodes[2] = null;
        listNodes[3] = new ListNode(2);

        ListNode listNode = solutions.mergeKLists(listNodes);

        int height = 0;
        while (null != listNode) {
            height++;
            listNode = listNode.next;
        }

        Assert.assertEquals(2, height);
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
    public void should_return_bitCount_from_0_to_5() {
        int num = 5;
        int[] result = solutions.countBits(num);

        Assert.assertEquals(6, result.length);
        Assert.assertEquals(0, result[0]);
        Assert.assertEquals(1, result[1]);
        Assert.assertEquals(1, result[2]);
        Assert.assertEquals(2, result[3]);
        Assert.assertEquals(1, result[4]);
        Assert.assertEquals(2, result[5]);
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
    public void should_return_maxSlidingWindow_35567() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] result = solutions.maxSlidingWindow(nums, 4);

        Assert.assertTrue(Arrays.equals(result, Arrays.stream(new int[]{3,5,5,6,7}).toArray()));
    }

    @Test
    public void should_return_maxSlidingWindow_77777() {
        int[] nums = {-7, -8, 7, 5, 7, 1, 6, 0};
        int[] result = solutions.maxSlidingWindow(nums, 4);

        Assert.assertTrue(Arrays.equals(result, Arrays.stream(new int[]{7,7,7,7,7}).toArray()));
    }

}
