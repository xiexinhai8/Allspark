package com.xiexinhai8.base_alg.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * 2981. 找出出现至少三次的最长特殊子字符串 I
 *
 * 给你一个仅由小写英文字母组成的字符串 s 。
 *
 * 如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。
 *
 * 返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。
 *
 * 子字符串 是字符串中的一个连续 非空 字符序列。
 * @author xxh
 * Created on 2024-05-29
 */
public class MaximumLength {

    /**
     * 统计 + 小顶堆
     * 采用小顶堆统计最大的三个子串长度, 找出最大的长度字符即可
     */
    public int maximumLength(String s) {
        char base = s.charAt(0);
        PriorityQueue<Integer>[] lenMap = new PriorityQueue[26];
        Arrays.setAll(lenMap, x -> new PriorityQueue<Integer>((a, b) -> (a - b)));
        // Arrays.fill(lenMap, new PriorityQueue<Integer>((a, b) -> (a - b)));

        int len = 0;
        for (int end = 0; end < s.length(); end++) {
            if (s.charAt(end) == base) {
                len++;
            } else {
                int idx = base - 'a';
                PriorityQueue<Integer> queue = lenMap[idx];
                // . System.out.println(queue + ",,,," + lenMap[13]);
                queue.offer(len);
                if (queue.size() > 3) {
                    queue.poll();
                }
                len = 1;
                base = s.charAt(end);
            }
        }
        lenMap[base - 'a'].offer(len);
        if (lenMap[base - 'a'].size() > 3) {
            lenMap[base - 'a'].poll();
        }

        int maxLen = -1;
        for (int i = 0; i < lenMap.length; i++) {
            len = -1;
            int preLen = 0;
            // System.out.println(lenMap[i]);
            /**
             * queue里放了最长的三个子串, 从小到大遍历queue
             * 假设当前长度为x
             * 如果当前长度比前一个长度大1 则maxLen = x - 1
             * 否则 maxLen = x - 2 + queue剩余长度
             */
            while (!lenMap[i].isEmpty()) {
                int curLen = lenMap[i].poll();
                if (curLen - 1 > 0 && curLen - 1 <= preLen) {
                    len = Math.max(len, curLen - 1);
                }
                len = Math.max(len, curLen - 2 + lenMap[i].size());
                preLen = curLen;
            }
            maxLen = Math.max(maxLen, len);
        }
        return maxLen > 0 ? maxLen : -1;
    }
}
