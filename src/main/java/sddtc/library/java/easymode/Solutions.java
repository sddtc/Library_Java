package sddtc.library.java.easymode;

import sddtc.library.java.object.TreeNode;

import java.util.HashSet;

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

    /**
     * invert binary tree
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return null;
        }

        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfsFindTarget(root, set, k);
    }

    private boolean dfsFindTarget(TreeNode root, HashSet<Integer> set, int k) {
        if(null == root) return false;
        if(set.contains(k - root.val)) return true;
        set.add(root.val);
        return dfsFindTarget(root.left, set, k) || dfsFindTarget(root.right, set, k);
    }
}
