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

    /**
     * no.71 https://leetcode.com/problems/simplify-path/
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        int length = path.length();
        String[] stack = new String[length / 2];
        int prt = 0;
        int i = 0;
        while (i < length) {
            char c = path.charAt(i);
            if (c == '/') {
                i++;
            } else if (c == '.') {
                int j = i + 1;
                while (j < length && path.charAt(j) != '/') {
                    j++;
                }
                if (j - i == 2 && path.charAt(i + 1) == '.') {
                    if (prt > 0) {
                        prt--;
                    }
                } else if (j - i > 2) {
                    stack[prt++] = path.substring(i, j);
                }
                i = j;
            } else {
                int j = i + 1;
                while (j < length && path.charAt(j) != '/') {
                    j++;
                }
                stack[prt++] = path.substring(i, j);
                i = j;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int j = 0; j < prt; j++) {
            result.append("/");
            result.append(stack[j]);
        }

        return result.length() == 0 ? "/" : result.toString();
    }

    /**
     * no.207 https://leetcode.com/problems/course-schedule/
     *
     * @param numCourses    要完成的课程数
     * @param prerequisites 条件
     * @return 能不能完成课程呢
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> posts = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            posts.add(new ArrayList<Integer>());
        }
        int[] preNums = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            posts.get(prerequisites[i][1]).add(prerequisites[i][0]);
            preNums[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (preNums[i] == 0) {
                queue.offer(i);
            }
        }

        int count = numCourses;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i : posts.get(cur)) {
                if (--preNums[i] == 0) {
                    queue.offer(i);
                }
            }
            count--;
        }

        return count == 0;
    }


    /**
     * no.338 https://leetcode.com/problems/counting-bits/
     *
     * @param num example:5
     * @return [0, 1, 1, 2, 1, 2]
     */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int bitCount = 0;
            int n = i;

            while (n > 0) {
                if (n % 2 == 1) {
                    bitCount++;
                }
                n = n >> 1;
            }

            result[i] = bitCount;
        }
        return result;
    }

    /**
     * no.233 https://leetcode.com/problems/number-of-digit-one/
     *
     * @param n example:13
     * @return 6 because:1,10,11,12,13
     */
    public int countDigitOne(int n) {
        int result = 0;
        for (long i = 1; i <= n; i *= 10) {
            result += (n / i + 8) / 10 * i + (n / i % 10 == 1 ? n % i + 1 : 0);
        }
        return result;
    }

    /****************************************************************
     * no.316 https://leetcode.com/problems/remove-duplicate-letters/
     * First loop: use an array cnt[] to count the number of times
     * appeared for each letter in s.
     * <p/>
     * Second loop (Greedy): use a stack, pop() while (!stack.isEmpty()
     * && (sc = stack.peek()) >= c && cnt[sc] > 0)
     *
     * @param s 字符串
     * @return 字典排序的不重复字符串
     */
    public String removeDuplicateLetters(String s) {
        int i, n = s.length();
        int[] cnt = new int[128];
        boolean[] inRes = new boolean[128]; // whether a char is in res[]
        char[] res = s.toCharArray(); // simulate a stack

        for (i = 0; i < n; i++)
            cnt[res[i]]++;

        char c, sc;
        int end = -1;
        // now cnt[c] means the remaining count of the char c
        for (i = 0; i < n; i++) {
            c = res[i];
            if (inRes[c]) {
                cnt[c]--;
                continue;
            }

            while (end >= 0 && (sc = res[end]) >= c && cnt[sc] > 0) {
                end--;
                inRes[sc] = false;
            }

            res[++end] = c;
            cnt[c]--;
            inRes[c] = true;
        }
        return String.valueOf(res).substring(0, end + 1);
    }

    /**
     * no.335 https://leetcode.com/problems/self-crossing/
     * Given x = [2, 1, 1, 2],
     * ┌───┐
     * │   │
     * └───┼──>
     * Return true (self crossing)
     *
     * @param x
     * @return
     */
    public boolean isSelfCrossing(int[] x) {
        if (x.length >= 5 && x[1] == x[3] && x[2] - x[4] <= x[0]) { // 5th line merges with the 1st one
            return true;
        }
        for (int i = 3; i < x.length; ++i) {
            if (x[i - 1] <= x[i - 3] && x[i] >= x[i - 2] // crosses the line three steps behind
                    || i >= 5 && x[i - 2] >= x[i - 4]
                    && x[i - 3] - x[i - 1] >= 0 && x[i - 3] - x[i - 1] <= x[i - 5]
                    && x[i] >= x[i - 2] - x[i - 4]) { // crosses the line five steps behind
                return true;
            }
        }
        return false;
    }

    /**
     * no.218 https://leetcode.com/problems/the-skyline-problem/
     *
     * @param buildings
     * @return
     */
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();

        for (int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }

        Collections.sort(height, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        Queue<Integer> pq = new PriorityQueue<>(11, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        pq.offer(0);
        int prev = 0;
        for (int[] h : height) {
            if (h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }

            int cur = pq.peek();
            if (prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }

        return result;
    }
}