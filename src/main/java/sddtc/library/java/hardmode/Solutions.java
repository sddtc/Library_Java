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
     * no.123 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int lenght = prices.length;

        int[] leftProfit = new int[lenght];
        int leftMaxProfit = 0;
        int leftMin = prices[0];
        for (int i = 0; i < lenght; i++) {
            if (prices[i] < leftMin) leftMin = prices[i];
            if (prices[i] - leftMin > leftMaxProfit) leftMaxProfit = prices[i] - leftMin;
            leftProfit[i] = leftMaxProfit;
        }

        int maxProfit = 0;
        int rightMaxProfit = 0;
        int rightMax = prices[lenght - 1];
        for (int i = lenght - 1; i >= 0; i--) {
            if (prices[i] > rightMax) rightMax = prices[i];
            if (rightMax - prices[i] > rightMaxProfit) rightMaxProfit = rightMax - prices[i];
            int currentProfit = rightMaxProfit + (i > 0 ? leftProfit[i - 1] : 0);
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
        }

        return maxProfit;
    }

    /**
     * no.41 https://leetcode.com/problems/first-missing-positive/
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        int len = nums.length;
        int i = 0, j = len - 1;
        // Re-arrange the nagative numbers to the end of array
        while (i <= j) {
            if (nums[i] <= 0) {
                while (i <= j && nums[j] <= 0) {
                    j--;
                }
                if (i < j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    j--;
                }
            }
            i++;
        }

        // Re-arrange the positive numbers to make the number locates at corresponding index
        i = 0;
        len = j + 1;
        while (i < len) {
            if (nums[i] > len || nums[nums[i] - 1] == nums[i]) {
                i++;
            } else {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }

        // Check which is the first positive number missing
        i = 0;
        while (i < len) {
            if (nums[i] != i + 1) return i + 1;
            i++;
        }

        return len + 1;
    }

    /**
     * no.87 https://leetcode.com/problems/scramble-string/
     * Below is one possible representation of s1 = "great":
     * <p/>
     * great
     * /    \
     * gr    eat
     * / \    /  \
     * g   r  e   at
     * / \
     * a   t
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;

        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) if (letters[i] != 0) return false;

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i))) return true;
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) return true;
        }
        return false;
    }

    /**
     * no.72 https://leetcode.com/problems/edit-distance/
     * <p/>
     * You have the following 3 operations permitted on a word:
     * a) Insert a character
     * b) Delete a character
     * c) Replace a character
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int[][] mem = new int[word1.length() + 1][word2.length() + 1];
        for (int[] row : mem) Arrays.fill(row, -1);
        mem[word1.length()][word2.length()] = 0;
        return minDist(word1, 0, word2, 0, mem);
    }

    private int minDist(String word1, int i, String word2, int j, int[][] mem) {
        if (i == word1.length() && j == word2.length()) return 0;
        if (i == word1.length()) {
            mem[i][j] = mem[i][j + 1] != -1 ? mem[i][j + 1] + 1 : minDist(word1, i, word2, j + 1, mem) + 1;
            return mem[i][j];
        }
        if (j == word2.length()) {
            mem[i][j] = mem[i + 1][j] != -1 ? mem[i + 1][j] + 1 : minDist(word1, i + 1, word2, j, mem) + 1;
            return mem[i][j];
        }
        if (word1.charAt(i) == word2.charAt(j)) {
            mem[i][j] = mem[i + 1][j + 1] != -1 ? mem[i + 1][j + 1] : minDist(word1, i + 1, word2, j + 1, mem);
            return mem[i][j];
        }
        int w1 = mem[i + 1][j] != -1 ? mem[i + 1][j] + 1 : minDist(word1, i + 1, word2, j, mem) + 1;
        int w2 = mem[i][j + 1] != -1 ? mem[i][j + 1] + 1 : minDist(word1, i, word2, j + 1, mem) + 1;
        int w1w2 = mem[i + 1][j + 1] != -1 ? mem[i + 1][j + 1] + 1 : minDist(word1, i + 1, word2, j + 1, mem) + 1;
        mem[i][j] = Math.min(w1w2, Math.min(w1, w2));
        return mem[i][j];
    }
}
