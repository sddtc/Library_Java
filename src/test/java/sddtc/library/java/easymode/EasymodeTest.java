package sddtc.library.java.easymode;

import org.junit.Assert;
import org.junit.Test;

public class EasymodeTest {

    private Solutions easySolutions = new Solutions();

    @Test
    public void should_return_powerOfTwo_true() {
        int n = 4;
        Assert.assertTrue(easySolutions.isPowerOfTwo(n));
    }

    @Test
    public void should_return_powerOfTwo_false() {
        int n = 3;
        Assert.assertFalse(easySolutions.isPowerOfTwo(n));
    }

    @Test
    public void should_return_powerOfThree_true() {
        int n = 27;

        Assert.assertTrue(easySolutions.isPowerOfThree(n));
    }

    @Test
    public void should_return_powerOfThree_false() {
        int n = 6;

        Assert.assertFalse(easySolutions.isPowerOfThree(n));
    }
}
