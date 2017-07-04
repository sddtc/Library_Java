package sddtc.library.java.mediummode;

import org.junit.Assert;
import org.junit.Test;

public class MediummodeTest {
    private Solutions mediumSolutions = new Solutions();

    @Test
    public void numberOfString() {
        String s = "12";
//        s = "03421";
//        s = "1221304";
        // TODO: 2017/7/3 test case
        System.out.println(mediumSolutions.numDecodings(s));
    }

    @Test
    public void should_return_perfectSquare() {
        int num = 16;

        Assert.assertTrue(mediumSolutions.isPerfectSquare(num));
    }
}
