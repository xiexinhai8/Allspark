package com.xiexinhai8.base_alg.bin_search;

import java.util.Arrays;

/**
 * 826. 安排工作以达到最大收益
 *
 * 你有 n 个工作和 m 个工人。给定三个数组： difficulty, profit 和 worker ，其中:
 *
 * difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
 * worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
 * 每个工人 最多 只能安排 一个 工作，但是一个工作可以 完成多次 。
 *
 * 举个例子，如果 3 个工人都尝试完成一份报酬为 $1 的同样工作，那么总收益为 $3 。如果一个工人不能完成任何工作，他的收益为 $0 。
 * 返回 在把工人分配到工作岗位后，我们所能获得的最大利润 。
 * @author xxh
 * Created on 2024-05-17
 */
public class MaxProfitAssignment {

    /**
     * 排序 + 遍历
     * 通过对工作的难度进行排序(难度从小到大), 工人也按照技术从低到高排序
     * 遍历工人, 找到对应的工作难度并加上对应工作中最大的收益
     */
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[][] workInfo = new int[difficulty.length][2];
        for (int i = 0; i < difficulty.length; i++) {
            workInfo[i] = new int[] { difficulty[i], profit[i] };
        }
        Arrays.sort(workInfo, (a1, b1) -> (a1[0] - b1[0]));
        Arrays.sort(worker);

        int totalProfit = 0;
        int curWorkPos = 0;
        int maxWorkProfit = 0;
        for (int i = 0; i < worker.length; i++) {
            while (curWorkPos < workInfo.length && workInfo[curWorkPos][0] <= worker[i]) {
                maxWorkProfit = Math.max(maxWorkProfit, workInfo[curWorkPos][1]);
                curWorkPos++;
            }
            totalProfit += maxWorkProfit;
        }

        return totalProfit;
    }
    /**
     * 排序 + 二分查找
     * 通过对工作的难度进行排序(难度从小到大 + 收益从大到小), 根据排序后的数据建立一个单调递增的收益表
     * 遍历工人, 通过二分法得到工人能干的位置, 同时查找收益表获取此工人的最大收益
     */
    public int maxProfitAssignment1(int[] difficulty, int[] profit, int[] worker) {
        int[][] workInfo = new int[difficulty.length][2];
        for (int i = 0; i < difficulty.length; i++) {
            workInfo[i] = new int[] { difficulty[i], profit[i] };
        }
        Arrays.sort(workInfo, (a1, b1) -> (a1[0] == b1[0] ? b1[1] - a1[1] : a1[0] - b1[0]));
        int[] maxWorkProfits = new int[difficulty.length];
        maxWorkProfits[0] = workInfo[0][1];
        for (int i = 1; i < workInfo.length; i++) {
            maxWorkProfits[i] = Math.max(maxWorkProfits[i - 1], workInfo[i][1]);
        }

        int totalProfit = 0;
        for (int i = 0; i < worker.length; i++) {
            int workLimitPos = biSearch(worker[i], workInfo);
            // System.out.print(workInfo[workLimitPos][0] + ", ");
            if (workLimitPos < 0) {
                continue;
            }
            totalProfit += maxWorkProfits[workLimitPos];
        }

        return totalProfit;
    }

    private int biSearch(int upLimit, int[][] workInfo) {
        int start = 0, end = workInfo.length - 1;
        if (workInfo[end][0] <= upLimit) {
            return end;
        }
        if (workInfo[start][0] > upLimit) {
            return -1;
        }

        while (start < end) {
            int mid = (start + end) / 2;

            if (workInfo[mid][0] < upLimit) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return workInfo[start][0] > upLimit ? start - 1 : start;
    }
}
