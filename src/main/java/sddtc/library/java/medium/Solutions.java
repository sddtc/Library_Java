package sddtc.library.java.medium;

/**
 * Created by sddtc on 16/6/26.
 */
public class Solutions {

    /**
     * no.367 https://leetcode.com/problems/valid-perfect-square/
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }
}
