package com.xiexinhai8.data_structure.queue;

/**
 *
 * 1670. 设计前中后队列
 *
 * 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 *
 * 请你完成 FrontMiddleBack 类：
 *
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的 最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 * void pushBack(int val) 将 val 添加到队里的 最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
 *
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-11-28
 */
class FrontMiddleBackQueue {
    Node head;
    Node tail;
    Node mid;
    int count;
    int midIndex;

    public FrontMiddleBackQueue() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
        mid = tail;
        count = 0;
        midIndex = 2;
        //System.out.println("--------------------");
    }

    public void pushFront(int val) {
        count++;
        midIndex++;

        Node node = new Node(val);
        Node next = head.next;

        head.next = node;
        node.pre = head;
        next.pre = node;
        node.next = next;

        updateMid();
    }

    public void pushMiddle(int val) {
        count++;
        midIndex++;

        Node node = new Node(val);

        Node pre = mid.pre;
        pre.next = node;
        node.pre = pre;
        node.next = mid;
        mid.pre = node;

        updateMid();
    }

    public void pushBack(int val) {
        count++;

        // 需要考虑首次从最后插入的情况, 可能导致mid不更新
        Node node = new Node(val);
        Node pre = tail.pre;

        pre.next = node;
        node.pre = pre;
        node.next = tail;
        tail.pre = node;

        updateMid();
    }

    public int popFront() {
        if (count <= 0) {
            return -1;
        }

        count--;
        midIndex--;

        Node curNode = head.next;

        Node next = curNode.next;
        head.next = next;
        next.pre = head;

        updateMid();

        return curNode.val;
    }

    public int popMiddle() {
        if (count <= 0) {
            return -1;
        }
        //System.out.println(count + " " + mid.val + " " + midIndex);
        if (count % 2 != 0) {
            mid = mid.next;
            midIndex++;
        }
        count--;
        midIndex--;
        Node node = mid.pre;

        Node pre = node.pre;
        pre.next = mid;
        mid.pre = pre;

        updateMid();

        return node.val;
    }

    public int popBack() {
        if (count <= 0) {
            return -1;
        }
        count--;

        Node node = tail.pre;

        Node pre = node.pre;
        pre.next = tail;
        tail.pre = pre;

        updateMid();

        return node.val;
    }

    private void updateMid() {
        int newMidIndex = count / 2 + 1;
        if (newMidIndex > midIndex) {
            mid = mid.next;
        }
        if (newMidIndex < midIndex) {
            mid = mid.pre;
        }
        midIndex = newMidIndex;

        if (count == 0) {
            midIndex = 2;
            mid = tail;
        }
    }

    static class Node {
        int val;
        Node next;
        Node pre;

        Node(int value) {
            this.val = value;
        }
    }
}
