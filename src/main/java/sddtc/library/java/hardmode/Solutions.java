package sddtc.library.java.hardmode;

import sddtc.library.java.object.Interval;
import sddtc.library.java.object.ListNode;
import sddtc.library.java.object.Point;

import java.util.*;

/**
 * leetcode hard mode answers
 * <p/>
 * Created by sddtc on 16/1/20.
 */
public class Solutions {

    /**
     * no.149 https://leetcode.com/problems/max-points-on-a-line/
     *
     * @param points
     * @return
     */
    public int maxPoints(Point[] points) {
        if (null == points) return 0;
        if (points.length < 3) return points.length;

        int max = 0;
        int duplicate = 1;

        Map<Double, Integer> map = new HashMap<Double, Integer>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            duplicate = 1;

            Point p = points[i];
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                Point tem = points[j];
                double slope = 0.0;
                if (tem.x == p.x && tem.y == p.y) {
                    duplicate++;
                    continue;
                } else if (tem.x == p.x) {
                    slope = Integer.MAX_VALUE;
                } else {
                    slope = tem.y == p.y ? 0 : 1.0 * (tem.y - p.y) / (tem.x - p.x);
                }

                map.put(slope, map.containsKey(slope) ? map.get(slope) + 1 : 1);
            }

            if (map.keySet().size() == 0) {
                max = duplicate;
            }

            for (double key : map.keySet()) {
                max = Math.max(max, duplicate + map.get(key));
            }
        }

        return max;
    }

    /**
     * no.44 https://leetcode.com/problems/wildcard-matching/
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int si = 0, pi = 0, match = 0, startIdX = -1;

        while (si < s.length()) {
            if (pi < p.length() && (p.charAt(pi) == '?' || s.charAt(si) == p.charAt(pi))) {
                si++;
                pi++;
            } else if (pi < p.length() && p.charAt(pi) == '*') {
                startIdX = pi;
                match = si;
                pi++;
            } else if (startIdX != -1) {
                pi = startIdX + 1;
                match++;
                si = match;
            } else {
                return false;
            }
        }

        while (pi < p.length() && p.charAt(pi) == '*') {
            pi++;
        }

        return pi == p.length();
    }

    /**
     * no.76 https://leetcode.com/problems/minimum-window-substring/
     * <p/>
     * For example,
     * S = "ADOBECODEBANC"
     * T = "ABC"
     * Minimum window is "BANC".
     *
     * @param s String s
     * @param t String t
     * @return
     */
    public String minWindow(String s, String t) {
        int slength = s.length(), tlength = t.length();
        Queue<Integer> queue = new ArrayDeque<>();
        int[] wordCountOfT = new int[256];
        int[] currentWordCountOfT = new int[256];

        for (int i = 0; i < tlength; i++) {
            wordCountOfT[t.charAt(i)]++;
        }

        int hasFound = 0;
        int windowStart = -1, windowEnd = slength;

        for (int i = 0; i < slength; i++) {

            if (wordCountOfT[s.charAt(i)] != 0) {
                queue.add(i);
                currentWordCountOfT[s.charAt(i)]++;

                if (currentWordCountOfT[s.charAt(i)] <= wordCountOfT[s.charAt(i)]) {
                    hasFound++;
                }

                if (hasFound == tlength) {
                    int k;
                    do {
                        k = queue.peek();
                        queue.poll();
                        currentWordCountOfT[s.charAt(k)]--;
                    }
                    while (wordCountOfT[s.charAt(k)] <= currentWordCountOfT[s.charAt(k)]);


                    if (windowEnd - windowStart > i - k) {
                        windowStart = k;
                        windowEnd = i;
                    }

                    hasFound--;
                }
            }
        }

        return windowStart != -1 ? s.substring(windowStart, windowStart + (windowEnd - windowStart + 1)) : "";
    }


    /**
     * no.97 https://leetcode.com/problems/interleaving-string/
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.equals(s2) && s1.equals(s3)) return true;
        if (("".equals(s1) && s2.equals(s3)) || ("".equals(s2) && s1.equals(s3))) return true;
        if (("".equals(s1) || "".equals(s2)) && !"".equals(s3)) return false;

        char[] sa3 = s3.toCharArray();
        char[] sa1 = s1.toCharArray();
        char[] sa2 = s2.toCharArray();
        int sl1 = sa1.length;
        int sl2 = sa2.length;
        int sl3 = sa3.length;

        int m = 0, n = 0;

        for (int i = 0; i < sl3; i++) {
            if (sl1 > 0 && m < sl1 && sa3[i] == sa1[m]) {
                m++;
            }
            if (sl2 > 0 && n < sl2 && sa3[i] == sa2[n]) {
                n++;
            }
        }

        return m == sl1 && n == sl2;
    }

    /**
     * no.57 https://leetcode.com/problems/insert-interval/
     * <p/>
     * Example 1:
     * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     * <p/>
     * Example 2:
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
     * <p/>
     * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();

        for (Interval cur : intervals) {
            if (newInterval == null) {
                res.add(cur);
                continue;
            }
            if (cur.end < newInterval.start) {
                res.add(cur);
                continue;
            }
            if (cur.start > newInterval.end) {
                res.add(newInterval);
                newInterval = null;
                res.add(cur);
                continue;
            }

            newInterval.start = Math.min(newInterval.start, cur.start);
            newInterval.end = Math.max(newInterval.end, cur.end);
        }

        if (newInterval != null) res.add(newInterval);

        return res;
    }

    /**
     * no.23 https://leetcode.com/problems/merge-k-sorted-lists/
     * <p/>
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return reduce(map(lists, 0, lists.length / 2), map(lists, lists.length / 2 + 1, lists.length - 1));
    }

    private ListNode map(ListNode[] lists, int from, int to) {
        if (from > to) {
            return null;
        }
        if (from == to) {
            return lists[from];
        }
        if (from + 1 == to) {
            return reduce(lists[from], lists[to]);
        }
        return reduce(map(lists, from, (from + to) / 2), map(lists, (from + to) / 2 + 1, to));
    }

    private ListNode reduce(ListNode first, ListNode second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        ListNode head, pre;
        if (first.val < second.val) {
            head = first;
            pre = first;
            first = first.next;
        } else {
            head = second;
            pre = second;
            second = second.next;
        }
        while (true) {
            if (first == null) {
                pre.next = second;
                break;
            }
            if (second == null) {
                pre.next = first;
                break;
            }
            if (first.val < second.val) {
                pre.next = first;
                pre = pre.next;
                first = first.next;
            } else {
                pre.next = second;
                pre = pre.next;
                second = second.next;
            }
        }
        return head;
    }

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
        List<Interval> result = new ArrayList<>();
        Interval current = null;

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });

        for (int i = 0; i < intervals.size(); i++) {
            current = intervals.get(i);
            Interval last = result.size() == 0 ? current : result.get(result.size() - 1);

            if (last.end < current.start) {
                result.add(current);
                continue;
            } else {
                result.remove(last);

                last.start = Math.min(last.start, current.start);
                last.end = Math.max(last.end, current.end);

                result.add(last);
            }
        }


        return result;
    }

    /**
     * no.321 https://leetcode.com/problems/create-maximum-number/
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        return null;//error
    }
}
