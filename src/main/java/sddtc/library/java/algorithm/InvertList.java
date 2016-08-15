package sddtc.library.java.algorithm;

import sddtc.library.java.object.ListNode;

/**
 * Author sddtc
 * Date 16/8/12
 */
public class InvertList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode one = new ListNode(2);
        ListNode two = new ListNode(3);
        ListNode three = new ListNode(4);
        head.next = one;
        one.next = two;
        two.next = three;
        three.next = null;
        ListNode h = head;
        while (null != h) {
            System.out.print(h.val + " ");
            h = h.next;
        }
        ListNode invert = new InvertList().invert(head);
        System.out.print(" => ");
        while (null != invert) {
            System.out.print(invert.val + " ");
            invert = invert.next;
        }
    }

    private ListNode invert(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode r;
        ListNode p = head;
        ListNode q = head.next;
        head.next = null;
        while (null != q) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        head = p;
        return head;
    }
}
