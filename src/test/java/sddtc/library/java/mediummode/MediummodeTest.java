package sddtc.library.java.mediummode;

import org.junit.Assert;
import org.junit.Test;

public class MediummodeTest {

    private Solutions mediumSolutions = new Solutions();

    @Test
    public void should_return_1_by_numberOfString() {
        String s = "98";

        Assert.assertEquals(1, mediumSolutions.numDecodings(s));
    }

    @Test
    public void should_return_2_by_numberOfString() {
        String s = "12";

        Assert.assertEquals(2, mediumSolutions.numDecodings(s));
    }

    @Test
    public void should_return_0_by_numberOfString() {
        String s = "03421";

        Assert.assertEquals(0, mediumSolutions.numDecodings(s));
    }

    @Test
    public void should_return_perfectSquare_true() {
        int num = 16;

        Assert.assertTrue(mediumSolutions.isPerfectSquare(num));
    }

    @Test
    public void should_return_perfectSquare_false() {
        int num = 8;

        Assert.assertFalse(mediumSolutions.isPerfectSquare(num));
    }
}
