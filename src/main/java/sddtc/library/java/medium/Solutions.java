package sddtc.library.java.medium;

import sddtc.library.java.object.ListNode;
import sddtc.library.java.object.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * no.142 https://leetcode.com/problems/linked-list-cycle-ii/
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * Note: Do not modify the linked list.
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode slow2 = head;
                while (slow2 != slow) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * no.18 https://leetcode.com/problems/4sum/
     * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
     * A solution set is:
     * [
     * [-1,  0, 0, 1],
     * [-2, -1, 1, 2],
     * [-2,  0, 0, 2]
     * ]
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList();
        List<Integer> l;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 2] < target) continue;
            for (int j = i + 1; j < len - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[len - 1] + nums[len - 2] < target) continue;
                int head = j + 1, end = len - 1;
                while (head < end) {
                    int tempt = nums[i] + nums[j] + nums[head] + nums[end];
                    if (tempt == target) {
                        l = new ArrayList();
                        l.add(nums[i]);
                        l.add(nums[j]);
                        l.add(nums[head]);
                        l.add(nums[end]);
                        list.add(l);
                        head++;
                        while (head < end && nums[head] == nums[head - 1]) {
                            head++;
                        }
                    } else if (tempt > target) end--;
                    else head++;
                }
            }
        }
        return list;
    }

    /**
     * no.91 https://leetcode.com/problems/decode-ways/
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= length; i++) {
            int first = s.charAt(i - 1) - '0';
            int second = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[length];
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (null == head) return null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (null != fast.next) {
            fast = fast.next;
            if (null == fast.next) {
                break;
            }

            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        if (null != prev) {
            prev.next = null;
        } else {
            head = null;
        }

        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);

        return root;
    }

    /**
     * no.114 https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
     *
     * @param root
     */
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (null == root) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
