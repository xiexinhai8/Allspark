package com.xiexinhai8.base_alg.hash;

import java.util.HashMap;
import java.util.Map;

import com.xiexinhai8.base_alg.entity.ListNode;

/**
 * 1171. 从链表中删去总和值为零的连续节点
 *
 * 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
 *
 * 删除完毕后，请你返回最终结果链表的头节点。
 * 你可以返回任何满足题目要求的答案。
 *
 * （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）
 * @author xxh
 * Created on 2023-06-12
 */
public class RemoveZeroSumSublists {

    public ListNode removeZeroSumSublists(ListNode head) {

        ListNode dummy = new ListNode();
        dummy.next = head;

        Map<Integer, ListNode> map = new HashMap<>();
        ListNode cur = dummy;
        int sum = 0;
        while (cur != null) {
            sum += cur.val;
            map.put(sum, cur);
            cur = cur.next;
        }

        cur = dummy;
        sum = 0;
        while (cur != null) {
            sum += cur.val;
            ListNode tmp = map.get(sum);
            if (tmp != null) {
                cur.next = tmp.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
