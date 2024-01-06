package com.xiexinhai8.base_alg;

import com.xiexinhai8.base_alg.entity.ListNode;

/**
 * 2807. 在链表中插入最大公约数
 * 
 * 给你一个链表的头 head ，每个结点包含一个整数值。
 *
 * 在相邻结点之间，请你插入一个新的结点，结点值为这两个相邻结点值的 最大公约数 。
 *
 * 请你返回插入之后的链表。
 *
 * 两个数的 最大公约数 是可以被两个数字整除的最大正整数。
 * @author xxh
 * Created on 2024-01-06
 */
public class InsertGreatestCommonDivisors {

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode cur = head;
        ListNode next = cur.next;

        while (next != null) {
            int val = getgcd(cur.val, next.val);
            ListNode commonDivisor = new ListNode(val);
            cur.next = commonDivisor;
            commonDivisor.next = next;

            cur = next;
            next = cur.next;
        }

        return head;
    }

    private int getgcd(int val1, int val2) {
        return val2 == 0 ? val1 : getgcd(val2, val1 % val2);

    }
}
