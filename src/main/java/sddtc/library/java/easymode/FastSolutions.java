package sddtc.library.java.easymode;

import sddtc.library.java.object.TreeNode;

public class FastSolutions {

    /**
     * Two Sum IV - Input is a BST
     *
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        if(null == root) return false;
        else return findFirst(root, root, k);
    }

    private boolean findFirst(TreeNode root, TreeNode t, int k) {
        if(null == t) return false;
        if(findSecond(root, k - t.val, t)) return true;
        else return findFirst(root, t.left, k) || findFirst(root, t.right, k);
    }

    private boolean findSecond(TreeNode t, int k, TreeNode first) {
        if(null == t) return false;
        if(t.val == k && first != t) return true;
        else if(t.val< k) return findSecond(t.right, k ,first);
        else return findSecond(t.left, k, first);
    }
}
