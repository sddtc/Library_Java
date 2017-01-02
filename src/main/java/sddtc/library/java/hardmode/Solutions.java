package sddtc.library.java.hardmode;

import sddtc.library.java.object.*;

import java.util.*;

/**
 * leetcode hard mode answers
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
     * Example 1:
     * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     * Example 2:
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
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

    /**
     * no.51 https://leetcode.com/problems/n-queens/
     *
     * @param n n皇后问题
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        int mark[] = new int[n];
        boolean x_mark[] = new boolean[n];
        List<List<String>> ret = new ArrayList<>();
        Recur(n, mark, x_mark, ret, n);
        return ret;
    }

    public boolean Recur(int n, int mark[], boolean x_mark[], List<List<String>> ret, int depth) {
        if (depth == 0) {
            List<String> s_ret = new ArrayList<String>();
            char[] lin = new char[n];
            for (int i = 0; i < n; i++)
                lin[i] = '.';
            for (int i = 0; i < n; i++) {
                lin[mark[i]] = 'Q';
                s_ret.add(new String(lin));
                lin[mark[i]] = '.';
            }
            ret.add(s_ret);
            return true;
        } else {
            for (int i = 0; i < n; i++) {
                if (!x_mark[i] && valid(n, depth - 1, i, mark)) {
                    x_mark[i] = true;
                    mark[depth - 1] = i;
                    Recur(n, mark, x_mark, ret, depth - 1);
                    x_mark[i] = false;
                }
            }
        }
        return true;
    }

    public boolean valid(int n, int row, int index, int mark[]) {
        for (int i = row + 1; i < n; i++) {
            int colDis = Math.abs(index - mark[i]);
            int rowDis = i - row;
            if (colDis == rowDis) return false;
        }
        return true;
    }

    /**
     * no.99 https://leetcode.com/problems/recover-binary-search-tree/
     *
     * @param root
     */
    TreeNode pre = null;
    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree(TreeNode root) {
        if (null == root) {
            return;
        }
        dfs(root);
        if (null != first && null != second) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    private void dfs(TreeNode root) {
        if (null != root.left) {
            dfs(root.left);
        }
        if (null != pre && pre.val > root.val) {
            if (first == null) {
                first = pre;
            }
            if (null != first) {
                second = root;
            }
        }
        pre = root;
        if (null != root.right) {
            dfs(root.right);
        }
    }

    /**
     * no.42 https://leetcode.com/problems/trapping-rain-water/
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it is able to trap after raining.
     * For example,
     * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }
        int start = 0;
        int end = height.length - 1;
        int smaller;
        int area = 0;
        while (start < end) {
            if (height[start] < height[end]) {
                smaller = height[start];
                while (start < end && height[start] <= smaller) {
                    area += smaller - height[start];
                    start++;
                }
            } else {
                smaller = height[end];
                while (start < end && height[end] <= smaller) {
                    area += smaller - height[end];
                    end--;
                }
            }
        }
        return area;
    }

    /**
     * no.29 https://leetcode.com/problems/divide-two-integers/
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        boolean isNeg = (dividend ^ divisor) >>> 31 == 1;
        int result = 0;
        if (dividend == Integer.MIN_VALUE) {
            dividend += Math.abs(divisor);
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
            result++;
        }
        if (divisor == Integer.MIN_VALUE) {
            return result;
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int digit = 0;
        while (divisor <= (dividend >> 1)) {
            divisor <<= 1;
            digit++;
        }

        while (digit >= 0) {
            if (dividend >= divisor) {
                result += 1 << digit;
                dividend -= divisor;
            }
            divisor >>= 1;
            digit--;
        }
        return isNeg ? -result : result;
    }

    /**
     * no.138 https://leetcode.com/problems/copy-list-with-random-pointer/
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            RandomListNode copy = new RandomListNode(iter.label);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        RandomListNode pseudoHead = new RandomListNode(0);
        RandomListNode copy, copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return pseudoHead.next;
    }

    /**
     * no.145 https://leetcode.com/problems/binary-tree-postorder-traversal/
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }

        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);

        return list;
    }

    private List<Integer> postorder(TreeNode node, List<Integer> result) {
        if (null != node) {
            postorder(node.left, result);
            postorder(node.right, result);
            result.add(node.val);
        }
        return null;
    }

    /**
     * no.4 https://leetcode.com/problems/median-of-two-sorted-arrays/
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (getkth(nums1, 0, nums2, 0, l) + getkth(nums1, 0, nums2, 0, r)) / 2.0;
    }

    public double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
        if (aStart > A.length - 1) return B[bStart + k - 1];
        if (bStart > B.length - 1) return A[aStart + k - 1];
        if (k == 1) return Math.min(A[aStart], B[bStart]);

        int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
        if (aStart + k / 2 - 1 < A.length) aMid = A[aStart + k / 2 - 1];
        if (bStart + k / 2 - 1 < B.length) bMid = B[bStart + k / 2 - 1];

        if (aMid < bMid)
            return getkth(A, aStart + k / 2, B, bStart, k - k / 2);// Check: aRight + bLeft
        else
            return getkth(A, aStart, B, bStart + k / 2, k - k / 2);// Check: bRight + aLeft
    }

    /**
     * no.15 https://leetcode.com/problems/3sum/
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int low = i + 1;
            int high = nums.length - 1;
            int sum = -nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == sum) {
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while (low < high && nums[low] == nums[low + 1]) low++;
                    low++;
                    while (low < high && nums[high] == nums[high - 1]) high--;
                    high--;
                } else if (nums[low] + nums[high] > sum) {
                    while (low < high && nums[high] == nums[high - 1]) high--;
                    high--;
                } else {
                    while (low < high && nums[low] == nums[low + 1]) low++;
                    low++;
                }

            }
        }
        return res;
    }

    /**
     * no.237 https://leetcode.com/problems/integer-to-english-words/
     * 123 -> "One Hundred Twenty Three"
     * 12345 -> "Twelve Thousand Three Hundred Forty Five"
     * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
     *
     * @param num
     * @return
     */
    public String numberToWords(int num) {
        String words = "";
        if (num == 0) {
            return "Zero";
        } else if (num < 100) {
            words = toLessHundred(num);
        } else if (num < 1000) {
            words = toLessThousand(num);
        } else if (num < 1000000) {
            words = toLessMillon(num);
        } else if (num < 1000000000) {
            words = toLessBillon(num);
        } else if (num < Math.pow(2, 31)) {
            words = toLessMax(num);
        }
        return words.trim();
    }

    private String toLessHundred(int num) {
        if (num < 20) {
            return numberCase(num);
        }
        return (numberCase(num / 10 * 10) + " " + numberCase(num % 10)).trim();
    }

    public String toLessThousand(int num) {
        if (num == 0) return "";
        if (num < 100)
            return toLessHundred(num % 100);
        return (numberCase(num / 100) + " Hundred " + toLessHundred(num % 100)).trim();
    }

    public String toLessMillon(int num) {
        if (num == 0) return "";
        if (num < 1000)
            return toLessThousand(num % 1000);
        return (toLessThousand(num / 1000) + " Thousand " + toLessThousand(num % 1000)).trim();
    }

    public String toLessBillon(int num) {
        if (num == 0) return "";
        if (num < 1000000)
            return toLessMillon(num % 1000000);
        return (toLessThousand(num / 1000000) + " Million " + toLessMillon(num % 1000000)).trim();
    }

    public String toLessMax(int num) {
        if (num < 1000000000)
            return toLessBillon(num % 1000000000);
        return toLessHundred(num / 1000000000) + " Billion " + toLessBillon(num % 1000000000).trim();
    }

    private String numberCase(int num) {
        switch (num) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
            case 20:
                return "Twenty";
            case 30:
                return "Thirty";
            case 40:
                return "Forty";
            case 50:
                return "Fifty";
            case 60:
                return "Sixty";
            case 70:
                return "Seventy";
            case 80:
                return "Eighty";
            case 90:
                return "Ninety";
            default:
                return "";
        }
    }

    /**
     * no.85 https://leetcode.com/problems/maximal-rectangle/
     * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') {
                height[i] = 1;
            }
        }
        int result = largestInLine(height);
        for (int i = 1; i < matrix.length; i++) {
            resetHeight(matrix, height, i);
            result = Math.max(result, largestInLine(height));
        }
        return result;
    }

    private int largestInLine(int[] height) {
        if (null == height || height.length < 1) {
            return 0;
        }
        int[] stack = new int[height.length + 1];
        int len = 0, max = 0;
        for (int i = 0; i <= height.length; i++) {
            int h = (i == height.length) ? 0 : height[i];
            while (len != 0 && (i == height.length || height[stack[len - 1]] > h)) {
                if (len == 1) {
                    max = Math.max(height[stack[--len]] * i, max);
                } else {
                    max = Math.max(height[stack[--len]] * (i - stack[len - 1] - 1), max);
                }
            }
            stack[len++] = i;
        }
        return max;
    }

    private void resetHeight(char[][] matrix, int[] height, int idx) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[idx][i] == '1') {
                height[i] += 1;
            } else {
                height[i] = 0;
            }
        }
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
        int[][] v1 = new int[k + 1][];
        int[][] v2 = new int[k + 1][];
        int k1 = Math.min(k, nums1.length);
        int k2 = Math.min(k, nums2.length);
        v1[k1] = bestPick(nums1, k1);
        v2[k2] = bestPick(nums2, k2);
        if (nums1.length + nums2.length > k) {
            for (int i = k1; i > 0; i--) v1[i - 1] = trim(v1[i], i);
            for (int i = k2; i > 0; i--) v2[i - 1] = trim(v2[i], i);
        }
        int[] merged = null;
        for (int i = 0; i <= k; i++) {
            if (v1[i] == null || v2[k - i] == null) continue;
            merged = merge(v1[i], i, v2[k - i], k - i, merged);
        }
        return merged;
    }

    int[] merge(int[] in1, int len1, int[] in2, int len2, int[] prev) {
        int[] merged = new int[len1 + len2];
        int i1 = 0, i2 = 0, cur = 0;
        while (i1 < len1 && i2 < len2) {
            if (in1[i1] > in2[i2]) merged[cur++] = in1[i1++];
            else if (in1[i1] < in2[i2]) merged[cur++] = in2[i2++];
            else {
                int ii1 = i1, ii2 = i2;
                while (in1[ii1] == in2[ii2]) {
                    if (ii1 < len1 - 1) ii1++;
                    if (ii2 < len2 - 1) ii2++;
                    if (ii1 == len1 - 1 && ii2 == len2 - 1) break;
                }
                if (in1[ii1] > in2[ii2]) merged[cur++] = in1[i1++];
                else if (in2[ii2] > in1[ii1]) merged[cur++] = in2[i2++];
                else merged[cur++] = in2[i2++];
            }
            if (prev != null) {
                if (merged[cur - 1] < prev[cur - 1]) return prev;
                if (merged[cur - 1] > prev[cur - 1]) prev = null;
            }
        }
        if (i1 == len1) {
            while (i2 < len2) {
                merged[cur++] = in2[i2++];
                if (prev != null) {
                    if (merged[cur - 1] < prev[cur - 1]) return prev;
                    if (merged[cur - 1] > prev[cur - 1]) prev = null;
                }
            }
        } else {
            while (i1 < len1) {
                merged[cur++] = in1[i1++];
                if (prev != null) {
                    if (merged[cur - 1] < prev[cur - 1]) return prev;
                    if (merged[cur - 1] > prev[cur - 1]) prev = null;
                }
            }
        }
        return merged;
    }

    int[] trim(int[] in, int k) {
        if (k == 1) return new int[0];
        int[] out = new int[k - 1];
        int j = 0;
        for (int i = 0; i < k; i++) {
            if (i == j && (i == k - 1 || in[i] < in[i + 1])) continue;
            out[j++] = in[i];
        }
        return out;
    }

    int[] bestPick(int[] in, int k) {
        if (k == 0) return new int[0];
        int[] stack = new int[Math.max(k, in.length)];
        int cur = 1;
        stack[0] = in[0];
        for (int i = 1; i < in.length; i++) {
            while (cur > 0 && in[i] > stack[cur - 1] && in.length - i > k - cur) cur--;
            stack[cur++] = in[i];
        }
        return stack;
    }

    /**
     * no.239 https://leetcode.com/problems/sliding-window-maximum/
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            if (max < nums[i]) {
                max = nums[i];
                maxIndex = -1;
            }
        }
        result[0] = max;
        for (int i = 1; i < nums.length - k + 1; i++) {
            if (max < nums[i + k - 1]) {
                max = nums[i + k - 1];
                maxIndex = i + k - 1;
            }
            if (maxIndex < i) {
                max = Integer.MIN_VALUE;
                maxIndex = -1;
                for (int j = 0; j < k; j++) {
                    if (max < nums[i + j]) {
                        max = nums[i + j];
                        maxIndex = i + j;
                    }
                }
            }
            result[i] = max;
        }

        return result;
    }

    /**
     * no.33 https://leetcode.com/problems/search-in-rotated-sorted-array/
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[start] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
            if (nums[mid] <= nums[end]) {
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }

    /**
     * no.224 https://leetcode.com/problems/basic-calculator/
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        int sign = 1;
        int result = 0;
        Stack<Integer> value = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int sum = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += sum * sign;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                value.push(result);
                value.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                result = result * value.pop() + value.pop();
            }
        }
        return result;
    }

    /**
     * no.282 https://leetcode.com/problems/expression-add-operators/
     * Given a string that contains only digits 0-9 and a target value,
     * return all possibilities to add binary operators (not unary) +, -, or *
     * between the digits so they evaluate to the target value.
     *
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperators(String num, int target) {
        if (null == num || num.equals("")) {
            return Collections.EMPTY_LIST;
        }
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        numberWithOperators(result, stringBuilder, num.toCharArray(), 0, target, 0, 0);
        return result;
    }

    private void numberWithOperators(List<String> result, StringBuilder stringBuilder, char[] num, int position, int target, long prev, long multi) {
        if (position == num.length) {
            if (target == prev) {
                result.add(stringBuilder.toString());
            }
            return;
        }
        long curr = 0;
        for (int i = position; i < num.length; i++) {
            if (num[position] == '0' && i != position) {
                break;
            }
            curr = 10 * curr + num[i] - '0';
            int len = stringBuilder.length();
            if (0 == position) {
                numberWithOperators(result, stringBuilder.append(curr), num, i + 1, target, curr, curr);
                stringBuilder.setLength(len);
            } else {
                numberWithOperators(result, stringBuilder.append("+").append(curr), num, i + 1, target, prev + curr, curr);
                stringBuilder.setLength(len);
                numberWithOperators(result, stringBuilder.append("-").append(curr), num, i + 1, target, prev - curr, -curr);
                stringBuilder.setLength(len);
                numberWithOperators(result, stringBuilder.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr);
                stringBuilder.setLength(len);
            }
        }
    }

    /**
     * no.115 https://leetcode.com/problems/distinct-subsequences/
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        int[][] dp = new int[tLength + 1][sLength + 1];
        for (int i = 0; i <= sLength; i++) {
            dp[0][i] = 1;
        }
        for (int j = 1; j <= tLength; j++) {
            for (int m = 1; m <= sLength; m++) {
                if (t.charAt(j - 1) != s.charAt(m - 1)) {
                    dp[j][m] = dp[j][m - 1];
                } else {
                    dp[j][m] = dp[j][m - 1] + dp[j - 1][m - 1];
                }
            }
        }
        return dp[tLength][sLength];
    }

    /**
     * no.329 https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int len = dfsWithLongestIncreasingPath(matrix, cache, dirs,  i, j);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    /**
     *
     * @param matrix
     * @param cache
     * @param dirs
     * @param i
     * @param j
     * @return
     */
    private int dfsWithLongestIncreasingPath(int[][] matrix, int[][] cache, int[][] dirs, int i, int j) {
        if(cache[i][j] != 0) return cache[i][j];
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 1;
        for(int[] dir: dirs) {
            int x = i + dir[0], y = j + dir[1];
            if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            int len = 1 + dfsWithLongestIncreasingPath(matrix, cache, dirs, x, y);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}