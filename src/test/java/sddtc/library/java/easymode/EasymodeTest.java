package sddtc.library.java.easymode;

import org.junit.Assert;
import org.junit.Test;

public class EasymodeTest {
    private Solutions easySolutions = new Solutions();

    @Test
    public void should_return_powerOfTwo() {
        int n = 1;

        Assert.assertTrue(easySolutions.isPowerOfTwo(n));
    }

    @Test
    public void should_return_powerOfThree_false() {
        int n = 6;

        Assert.assertFalse(easySolutions.isPowerOfThree(n));
    }
}
