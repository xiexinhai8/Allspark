package com.xiexinhai8.base_alg.graph;

/**
 *
 * 2050. 并行课程 III
 *
 * 给你一个整数 n ，表示有 n 节课，课程编号从 1 到 n 。同时给你一个二维整数数组 relations ，其中 relations[j] = [prevCoursej, nextCoursej] ，表示课程
 * prevCoursej 必须在课程 nextCoursej 之前 完成（先修课的关系）。同时给你一个下标从 0 开始的整数数组 time ，其中 time[i] 表示完成第 (i+1) 门课程需要花费的 月份 数。
 *
 * 请你根据以下规则算出完成所有课程所需要的 最少 月份数：
 *
 * 如果一门课的所有先修课都已经完成，你可以在 任意 时间开始这门课程。
 * 你可以 同时 上 任意门课程 。
 * 请你返回完成所有课程所需要的 最少 月份数。
 *
 * 注意：测试数据保证一定可以完成所有课程（也就是先修课的关系构成一个有向无环图）。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-05
 */
public class MinimumTime {

    public int minimumTime(int n, int[][] relations, int[] time) {
        /** 贪心
         问题: 不知道怎么遍历图, 对图不了解

         动态规划
         问题: 动态规划前提是拆子问题, 且子问题提前处理完成了

         递归, 找到最后几个节点, 将父节点推入队列(队列中的节点记录后续耗时)
         */
        return 0;
    }
}
