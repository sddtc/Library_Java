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
}
