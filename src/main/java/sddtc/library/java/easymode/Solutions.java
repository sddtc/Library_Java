package sddtc.library.java.easymode;

/**
 * Created by sddtc on 16/5/17.
 */
public class Solutions {
    /**
     * no.344 https://leetcode.com/problems/reverse-string/
     *
     * @param s
     * @return
     */
    public String reverseString(String s) {
        if (null == s || s.isEmpty()) {
            return s;
        }

        StringBuffer stringBuffer = new StringBuffer(s);
        return stringBuffer.reverse().toString();
    }

    /**
     * no.231 https://leetcode.com/problems/power-of-two/
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        return (n > 0) && ((n & n - 1) == 0);
    }

    /**
     * no.327 https://leetcode.com/problems/power-of-three/
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        if (n > 1) {
            while (n % 3 == 0) {
                n /= 3;
            }
        }
        return n == 1;
    }

    /**
     * no.342 https://leetcode.com/problems/power-of-four/
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        if (num > 1) {
            while (num % 4 == 0) {
                num /= 4;
            }
        }
        return num == 1;
    }
}
