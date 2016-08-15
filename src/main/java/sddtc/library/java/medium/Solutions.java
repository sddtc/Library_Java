package sddtc.library.java.medium;

import sddtc.library.java.object.ListNode;

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
}
