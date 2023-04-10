package com.xiexinhai8.base_alg.entity;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-10
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
