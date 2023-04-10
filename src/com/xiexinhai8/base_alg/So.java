package com.xiexinhai8.base_alg;


import com.xiexinhai8.base_alg.entity.ListNode;

public class So {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        ListNode temp = new ListNode(2);
        cur.next = temp;
        cur = cur.next;
        temp = new ListNode(3);
        cur.next = temp;
        cur = cur.next;
        temp = new ListNode(4);
        cur.next = temp;
        cur = cur.next;
        temp = new ListNode(5);
        cur.next = temp;
        cur = cur.next;
        temp = new ListNode(6);
        cur.next = temp;

        cur = head;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }

        System.out.println();
        cur = reverseList(head);
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }



    }
    public static ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre, cur, next;
        pre = dummy;
        while (pre.next != null) {
            cur = pre.next;
            if (cur.next == null) {
                break;
            }
            next = cur.next;

            pre.next = next;
            cur.next = next.next;
            next.next = cur;

            pre = cur;
        }
        return dummy.next;
    }


}