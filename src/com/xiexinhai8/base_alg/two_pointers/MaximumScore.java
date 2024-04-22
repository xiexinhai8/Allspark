package com.xiexinhai8.base_alg.two_pointers;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1793. 好子数组的最大分数
 *
 * 给你一个整数数组 nums （下标从 0 开始）和一个整数 k 。
 *
 * 一个子数组 (i, j) 的 分数 定义为 min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1) 。一个 好 子数组的两个端点下标需要满足 i <= k <= j 。
 *
 * 请你返回 好 子数组的最大可能 分数 。
 * @author xxh
 * Created on 2024-04-22
 */
public class MaximumScore {

    /**
     * 方法一： 单调栈
     */
    public int maximumScore_stack(int[] nums, int k) {
        int n = nums.length;
        int[] left = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!st.isEmpty() && x <= nums[st.peek()]) {
                st.pop();
            }
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        int[] right = new int[n];
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            while (!st.isEmpty() && x <= nums[st.peek()]) {
                st.pop();
            }
            right[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int h = nums[i];
            int l = left[i];
            int r = right[i];
            if (l < k && k < r) { // 相比 84 题多了个 if 判断
                ans = Math.max(ans, h * (r - l - 1));
            }
        }
        return ans;
    }

    /**
     * 方法二：双指针
     */
    public int maximumScore_bi(int[] nums, int k) {

        int first = k, end = k;
        int maxScore = nums[k];

        int minHeight = nums[k];
        while (first > 0 || end < nums.length - 1) {
            if (first - 1 >= 0 && end + 1 < nums.length) {
                if (nums[first - 1] > nums[end + 1]) {
                    first = first - 1;
                } else {
                    end = end + 1;
                }
            } else if (first - 1 >= 0) {
                first = first - 1;
            } else {
                end = end + 1;
            }
            minHeight = Math.min(minHeight, nums[first]);
            minHeight = Math.min(minHeight, nums[end]);
            int curSore = (end - first + 1) * minHeight;
            if (curSore > maxScore) {
                maxScore = curSore;
            }
        }
        return maxScore;
    }
}
