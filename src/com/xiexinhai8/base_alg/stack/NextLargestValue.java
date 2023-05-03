package com.xiexinhai8.base_alg.stack;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-10
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.xiexinhai8.base_alg.entity.ListNode;

/**
 * 1019. 链表中的下一个更大节点
 *
 * 给定一个长度为 n 的链表 head
 *
 * 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
 *
 * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class NextLargestValue {
    public int[] nextLargerNodes(ListNode head) {
        Deque<NodeInfo> stack = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        int pos = 0;
        while (head != null) {
            result.add(0);
            NodeInfo nodeInfo = new NodeInfo(head.val, pos);
            while (!stack.isEmpty() && stack.peek().val < head.val) {
                NodeInfo old = stack.pop();
                result.set(old.pos, head.val);
            }

            stack.push(nodeInfo);
            pos++;
        }

        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;

    }

    static class NodeInfo {
        int val;
        int pos;

        NodeInfo(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }
    }
}
