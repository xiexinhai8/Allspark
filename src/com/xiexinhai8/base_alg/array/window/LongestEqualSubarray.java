package com.xiexinhai8.base_alg.array.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2831. 找出最长等值子数组
 *
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 *
 * 如果子数组中所有元素都相等，则认为子数组是一个 等值子数组 。注意，空数组是 等值子数组 。
 *
 * 从 nums 中删除最多 k 个元素后，返回可能的最长等值子数组的长度。
 *
 * 子数组 是数组中一个连续且可能为空的元素序列。
 * @author xxh
 * Created on 2024-05-23
 */
public class LongestEqualSubarray {

    /**
     * 子数组的元素可能变化, 不便于使用dp算法
     *
     * hash统计 + 滑动窗口
     *
     * 使用hashmap统计nums中各元素的位置, 再使用滑动窗口统计固定窗口中元素最多的数量
     */
    public int longestEqualSubarray(List<Integer> nums, int k) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        // 统计nums中各元素的位置
        for (int i = 0; i < nums.size(); i++) {
            /*map.putIfAbsent(nums.get(i), new ArrayList<>());
            map.get(nums.get(i)).add(i);*/
            // 优先使用computeIfAbsent方法
            map.computeIfAbsent(nums.get(i), x -> new ArrayList<>()).add(i);
        }

        int maxLen = 0;
        // 遍历各元素, 找出以这个元素为等值数组时的最大长度
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int start = 0, len = 1, num = 1;
            List<Integer> list = entry.getValue();
            if (maxLen > list.size()) {
                continue;
            }
            // System.out.println(list);
            for (int end = 0; end < list.size(); end++) {
                len = list.get(end) - list.get(start) + 1;
                num = end - start + 1;
                // 当窗口长度大于k时缩小窗口
                while (len - num > k) {
                    start++;
                    len = list.get(end) - list.get(start) + 1;
                    num = end - start + 1;
                }
                // 获取窗口中实际元素大小与当前最长等值数组长度比较
                if (num > maxLen) {
                    maxLen = num;
                }
            }
        }
        return maxLen;
    }
}
