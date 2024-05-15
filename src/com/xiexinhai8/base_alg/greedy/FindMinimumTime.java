package com.xiexinhai8.base_alg.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 2589. 完成所有任务的最少时间
 *
 * 你有一台电脑，它可以 同时 运行无数个任务。给你一个二维整数数组 tasks ，其中 tasks[i] = [starti, endi, durationi] 表示第 i 个任务需要在 闭区间 时间段 [starti,
 * endi] 内运行 durationi 个整数时间点（但不需要连续）。
 *
 * 当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
 *
 * 请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
 * @author xxh
 * Created on 2024-05-15
 */
public class FindMinimumTime {

    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a1, a2) -> (a1[1] - a2[1]));
        boolean[] done = new boolean[tasks[tasks.length - 1][1] + 1];

        for (int i = 0; i < tasks.length; i++) {
            int duration = tasks[i][2];
            for (int j = tasks[i][0]; j <= tasks[i][1]; j++) {
                if (done[j]) {
                    duration--;
                }
            }
            int reset = Math.max(0, duration);

            for (int j = tasks[i][1]; j >= tasks[i][0] && reset > 0; j--) {
                if (done[j]) {
                    continue;
                }
                done[j] = true;
                reset--;
            }
        }
        int takeTimes = 0;
        for (int i = 0; i < done.length; i++) {
            if (done[i]) {
                takeTimes++;
            }
        }
        return takeTimes;
    }

    // 制作一个2000 * 2000 的矩阵; 对每一列进行统计, 通过贪心的方法优先处理1多的列(相当于每单位时间干活最多的时间点)
    public int findMinimumTime1(int[][] tasks) {
        // map key为时间点, vale 为任务号
        Map<Integer, List<Integer>> taskMap = new HashMap<>();
        int[] tasksWeight = new int[tasks.length];

        for (int i = 0; i < tasks.length; i++) {
            tasksWeight[i] = tasks[i][2];
            for (int j = tasks[i][0]; j <= tasks[i][1]; j++) {
                taskMap.putIfAbsent(j, new ArrayList<>());
                taskMap.get(j).add(i);
            }
        }

        int takeTimes = 0;
        while (true) {
            // 从map中找到任务最多的时间点
            int maxLoadTime = findMaxLooadTime(taskMap);
            if (maxLoadTime <= 0) {
                break;
            }
            takeTimes++;
            List<Integer> doTasks = taskMap.get(maxLoadTime);
            //System.out.println(doTasks);
            for (int taskNo : doTasks) {
                // System.out.println("taskNo = " + taskNo + " weight = " + tasksWeight[taskNo]);
                tasksWeight[taskNo]--;
            }
            // System.out.println(taskMap.size());

            for (int i = 0; i < tasksWeight.length; i++) {
                if (tasksWeight[i] <= 0) {
                    // 从map中将当前任务号删除掉
                    cleanTask(i, taskMap);
                }
            }
            taskMap.remove(maxLoadTime);
        }

        for (int i = 0; i < tasksWeight.length; i++) {
            takeTimes += tasksWeight[i];
        }

        return takeTimes;
    }

    private int findMaxLooadTime(Map<Integer, List<Integer>> map) {
        int maxLoadTime = 0;
        int maxLoad = 2;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            //System.out.print(entry.getKey() + "=" + entry.getValue() + ", ");
            if (entry.getValue().size() >= maxLoad) {
                maxLoad = entry.getValue().size();
                maxLoadTime = entry.getKey();
            }
        }
        //System.out.println();
        return maxLoadTime;
    }

    private void cleanTask(int taskNo, Map<Integer, List<Integer>> map) {

        for (List<Integer> list : map.values()) {
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                int i = iterator.next();
                if (i == taskNo) {
                    iterator.remove();
                }
            }
        }
    }
}
