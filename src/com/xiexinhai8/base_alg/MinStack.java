package com.xiexinhai8.base_alg;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-08-17
 */
public class MinStack {
    class DeLinkedNode {
        int val;
        DeLinkedNode pre;
        DeLinkedNode next;
        public DeLinkedNode(int val) {
            this.val = val;
        }
    }

    private Deque<DeLinkedNode> stack;
    private DeLinkedNode head;
    private DeLinkedNode tail;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayDeque<>();
        head = new DeLinkedNode(0);
        tail = new DeLinkedNode(0);
        head.next = tail;
        tail.pre = head;
    }

    public void push(int x) {
        DeLinkedNode node = new DeLinkedNode(x);
        stack.push(node);
        DeLinkedNode cur = head.next;
        while (cur != tail) {
            if (cur.val > x) {
                DeLinkedNode pre = cur.pre;
                node.pre = pre;
                pre.next = node;
                cur.pre = node;
                node.next = cur;
                break;
            }
            cur = cur.next;
        }
        if (cur == tail) {
            DeLinkedNode pre = cur.pre;
            node.pre = pre;
            pre.next = node;
            cur.pre = node;
            node.next = cur;
        }
    }

    public int pop() {
        DeLinkedNode node = stack.pop();
        if (node != null) {
            DeLinkedNode pre = node.pre;
            DeLinkedNode next = node.next;
            pre.next = next;
            next.pre = pre;
        }
        return node.val;
    }

    public int top() {
        return stack.peek().val;
    }

    public int min() {
        return head.next.val;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, -1, -100, 5, -210, 9};
        MinStack ms = new MinStack();
        for (int i = 0; i < data.length; i++) {
            ms.push(data[i]);

        }
        System.out.println(ms.pop());
        System.out.println(ms.pop());
        System.out.println(ms.min());
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < data.length; i++) {
            d.push(i);
        }
        System.out.println(d);
        System.out.println(d.poll());
        System.out.println(d.pollFirst());
    }
}

