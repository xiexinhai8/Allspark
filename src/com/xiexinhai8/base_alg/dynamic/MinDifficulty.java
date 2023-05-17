package com.xiexinhai8.base_alg.dynamic;

/**
 * 1335. 工作计划的最低难度
 *
 * 你需要制定一份 d 天的工作计划表。工作之间存在依赖，要想执行第 i 项工作，你必须完成全部 j 项工作（ 0 <= j < i）。
 *
 * 你每天 至少 需要完成一项任务。工作计划的总难度是这 d 天每一天的难度之和，而一天的工作难度是当天应该完成工作的最大难度。
 *
 * 给你一个整数数组 jobDifficulty 和一个整数 d，分别代表工作难度和需要计划的天数。第 i 项工作的难度是 jobDifficulty[i]。
 *
 * 返回整个工作计划的 最小难度 。如果无法制定工作计划，则返回 -1 。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-05-16
 */
public class MinDifficulty {

    public int minDifficulty(int[] jobDifficulty, int d) {
        /** 思路1: 找出计划的最大难度, 其中最大难度成为分割点

         问题: 最大难度可能重复, 导致分割点很多, 不知道从哪个分割点下手
         */
        int[][] g = new int[d][jobDifficulty.length];
        int[][] distance = new int[jobDifficulty.length][jobDifficulty.length];
        int localMax = -1;
        getDistance(distance, jobDifficulty);
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < jobDifficulty.length; j++) {
                if (i == 0) {
                    g[i][j] = distance[0][j];
                } else {
                    g[i][j] = -1;
                }
            }
        }

        for (int i = 1; i < d; i++) {

            for (int j = i; j < jobDifficulty.length; j++) {
                g[i][j] = Integer.MAX_VALUE;
                for (int k = i - 1; k < j; k++) {
                    g[i][j] = Math.min(g[i][j], g[i - 1][k] + distance[k + 1][j]);
                }

            }
        }

        return g[d - 1][jobDifficulty.length - 1];
    }

    private void getDistance(int[][] distance, int[] jobDifficulty) {

        for (int end = jobDifficulty.length - 1; end >= 0; end--) {

            for (int start = end; start >= 0; start--) {
                if (start == end) {
                    distance[start][end] = jobDifficulty[start];
                } else {
                    distance[start][end] = Math.max(distance[start + 1][end], jobDifficulty[start]);
                }

            }
        }
    }
}
