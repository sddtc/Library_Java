package sddtc.library.java.hardmode;

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
        if(("".equals(s1) && s2.equals(s3)) || ("".equals(s2) && s1.equals(s3))) return true;
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
