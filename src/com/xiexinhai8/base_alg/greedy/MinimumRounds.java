package com.xiexinhai8.base_alg.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2244. 完成所有任务需要的最少轮数
 *
 * 给你一个下标从 0 开始的整数数组 tasks ，其中 tasks[i] 表示任务的难度级别。在每一轮中，你可以完成 2 个或者 3 个 相同难度级别 的任务。
 *
 * 返回完成所有任务需要的 最少 轮数，如果无法完成所有任务，返回 -1 。
 * @author xxh
 * Created on 2024-05-14
 */
public class MinimumRounds {
    /**
     * 直接统计再计算
     * 方法: 先通过hashmap统计频率, 再采用贪心法
     * 只要某个级别的任务数不为1, 那么一定可以拆解成3 和 2, 且尽量拆解成3能够让每个等级的任务轮数最少
     * 证明: 一个任务的数量为n, n 可以拆解成 n / 3 个 3 和 n % 3;
     * 其中n % 3 可能为1和2
     * 如果n % 3 = 1 则只需要将其中一个3和1合并就能拆解成两个2
     * 如果n % 3 = 2 则不需要拆解, 直接完成两个任务就行
     */
    public int minimumRounds_2(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            map.merge(tasks[i], 1, Integer::sum);
        }

        int minRounds = 0;
        for (int freq : map.values()) {

            if (freq == 1) {
                return -1;
            }
            minRounds += (freq / 3);
            if (freq % 3 != 0) {
                minRounds++;
            }
        }
        return minRounds;
    }

    /**
     * 排序后直接统计
     * 方法: 贪心法, 只要某个级别的任务数不为1, 那么一定可以拆解成3 和 2, 且尽量拆解成3能够让每个等级的任务轮数最少
     * 证明: 一个任务的数量为n, n 可以拆解成 n / 3 个 3 和 n % 3;
     * 其中n % 3 可能为1和2
     * 如果n % 3 = 1 则只需要将其中一个3和1合并就能拆解成两个2
     * 如果n % 3 = 2 则不需要拆解, 直接完成两个任务就行
     */
    public int minimumRounds_1(int[] tasks) {
        Arrays.sort(tasks);
        int minRounds = 0;

        int freq = 1;
        for (int i = 1; i < tasks.length; i++) {
            if (tasks[i] == tasks[i - 1]) {
                freq++;
            } else {
                // 如果freq为1, 无法完成任务, 返回无法-1
                if (freq == 1) {
                    return -1;
                }
                // 采用贪心法, 只要能够一次完成3个任务的都一律三个任务
                minRounds += freq / 3;
                if (freq % 3 != 0) {
                    minRounds++;
                }
                freq = 1;
            }
        }
        // 最后一轮任务也需要完成
        if (freq == 1) {
            return -1;
        }
        minRounds += freq / 3;
        if (freq % 3 != 0) {
            minRounds++;
        }
        return minRounds;
    }
}
