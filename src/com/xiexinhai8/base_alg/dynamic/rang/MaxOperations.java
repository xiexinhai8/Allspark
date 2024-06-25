package com.xiexinhai8.base_alg.dynamic.rang;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 3040. 相同分数的最大操作数目 II
 *
 * 给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：
 *
 * 选择 nums 中最前面两个元素并且删除它们。
 * 选择 nums 中最后两个元素并且删除它们。
 * 选择 nums 中第一个和最后一个元素并且删除它们。
 * 一次操作的 分数 是被删除元素的和。
 *
 * 在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
 *
 * 请你返回按照上述要求 最多 可以进行的操作次数。
 * @author xxh
 * Created on 2024-06-24
 */
public class MaxOperations {

    public int maxOperations(int[] nums) {
        int max = 0;
        int[][] cache = new int[nums.length][nums.length];
        fillCache(cache);
        max = Math.max(max, maxOperations(nums, 2, nums.length - 1, nums[0] + nums[1], cache));
        fillCache(cache);
        max = Math.max(max, maxOperations(nums, 1, nums.length - 2, nums[0] + nums[nums.length - 1], cache));
        fillCache(cache);
        max = Math.max(max, maxOperations(nums, 0, nums.length - 3, nums[nums.length - 1] + nums[nums.length - 2], cache));

        return max + 1;
    }

    private void fillCache(int[][] cache) {

        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
    }

    private int maxOperations(int[] nums, int start, int end, int target, int[][] cache) {
        if (start >= end) {
            return 0;
        }
        if (cache[start][end] != -1) {
            return cache[start][end];
        }
        int maxScore = 0;
        if (nums[start] + nums[start + 1] == target) {
            maxScore = Math.max(maxScore, maxOperations(nums, start + 2, end, target, cache) + 1);
        }
        if (nums[end] + nums[end - 1] == target) {
            maxScore = Math.max(maxScore, maxOperations(nums, start, end - 2, target, cache) + 1);
        }
        if (nums[start] + nums[end] == target) {
            maxScore = Math.max(maxScore, maxOperations(nums, start + 1, end - 1, target, cache) + 1);
        }

        cache[start][end] = maxScore;
        return maxScore;
    }

    int[][] dirs = {{-1, 1}, {-2, 0}, {0, 2}};
    public int maxOperations_queue(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        int maxOperate = findMaxOperate(0, 1, nums, prefix);
        maxOperate = Math.max(maxOperate, findMaxOperate(nums.length - 1, 0, nums, prefix));
        maxOperate = Math.max(maxOperate, findMaxOperate(nums.length - 2, nums.length - 1, nums, prefix));

        return maxOperate;
    }

    private int findMaxOperate(int x, int y, int[] nums, int[] prefix) {
        boolean[][] map = new boolean[nums.length][nums.length];
        Deque<int[]> queue = new ArrayDeque<>();
        int maxOperate = 0;
        queue.offer(new int[] {x, y, nums[x] + nums[y], 1});

        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            int start = info[0], end = info[1], limit = info[2], operate = info[3];
            int sum = sumRange(start, end, prefix);
            if (operate * 2 > nums.length) {
                continue;
            }
            if (operate > maxOperate) {
                maxOperate = operate;
                if (maxOperate * 2 == nums.length) {
                    break;
                }
            }

            for (int i = 0; i < dirs.length; i++) {
                int newStart = (start + dirs[i][0] + nums.length) % nums.length;
                int newEnd = (end + dirs[i][1] + nums.length) % nums.length;
                if (map[newStart][newEnd]) {
                    continue;
                }
                int newSum = sumRange(newStart, newEnd, prefix);
                if (newSum - sum == limit) {
                    queue.offer(new int[]{newStart, newEnd, limit, operate + 1});
                }
                map[newStart][newEnd] = true;
            }
        }

        return maxOperate;
    }

    private int sumRange(int start, int end, int[] prefix) {
        int sum = 0;
        if (end < start) {
            sum += prefix[end];
            sum += (prefix[prefix.length - 1] - prefix[start - 1]);
        } else {
            sum = prefix[end];
            if (start - 1 >= 0) {
                sum -= prefix[start - 1];
            }
        }
        return sum;
    }
}
