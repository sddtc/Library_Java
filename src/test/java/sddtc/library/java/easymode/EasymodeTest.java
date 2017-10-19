package sddtc.library.java.easymode;

import org.junit.Assert;
import org.junit.Test;
import sddtc.library.java.object.TreeNode;

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

    @Test
    public void should_return_sum_two_iv() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        Assert.assertFalse(easySolutions.findTarget(root, 10));
        Assert.assertTrue(easySolutions.findTarget(root, 5));
    }
}
