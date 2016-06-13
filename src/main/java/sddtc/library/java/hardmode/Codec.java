package sddtc.library.java.hardmode;

import sddtc.library.java.object.TreeNode;

/**
 * no.297 https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * Author tuijiang
 * Date 16/5/30
 */
public class Codec {
    char[] chars;
    int length, c;

    public String serialize(TreeNode root) {
        length = 128;
        chars = new char[length];
        c = 0;
        sdfs(root);
        return new String(chars).substring(0, c);
    }

    private void sdfs(TreeNode root) {
        add(root);
        if (null == root) {
            return;
        }
        sdfs(root.left);
        sdfs(root.right);
    }

    private void add(TreeNode root) {
        int v;
        if (null == root) {
            v = Integer.MIN_VALUE;
        } else {
            v = root.val;
        }

        if (c == length) {
            grow();
        }
        chars[c++] = (char) (v >>> 16);
        chars[c++] = (char) v;
    }

    void grow() {
        int nl = length * 2;
        char[] n = new char[nl];
        System.arraycopy(chars, 0, n, 0, length);
        chars = n;
        length = nl;
    }

    public TreeNode deserialize(String data) {
        chars = data.toCharArray();
        c = 0;
        TreeNode root = next();
        ddfs(root);
        return root;
    }

    private void ddfs(TreeNode root) {
        if (null == root) {
            return;
        }
        root.left = next();
        ddfs(root.left);
        root.right = next();
        ddfs(root.right);
    }

    private TreeNode next() {
        if (c == length) {
            return null;
        }
        char a = chars[c++];
        char b = chars[c++];
        int v = ((int) a << 16) | b;
        if (v == Integer.MIN_VALUE) {
            return null;
        }
        return new TreeNode(v);
    }
}
