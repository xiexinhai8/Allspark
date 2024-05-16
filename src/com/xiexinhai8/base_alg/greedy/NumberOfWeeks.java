package com.xiexinhai8.base_alg.greedy;

import java.util.PriorityQueue;

/**
 * 1953. 你可以工作的最大周数
 *
 * 给你 n 个项目，编号从 0 到 n - 1 。同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。
 *
 * 你可以按下面两个规则参与项目中的工作：
 *
 * 每周，你将会完成 某一个 项目中的 恰好一个 阶段任务。你每周都 必须 工作。
 * 在 连续的 两周中，你 不能 参与并完成同一个项目中的两个阶段任务。
 * 一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将 停止工作 。注意，由于这些条件的限制，你可能无法完成所有阶段任务。
 *
 * 返回在不违反上面规则的情况下你 最多 能工作多少周。
 * @author xxh
 * Created on 2024-05-16
 */
public class NumberOfWeeks {

    /**
     * 贪心算法
     * 如果任务数最多的任务 <= 其它任务的任务数和 + 1则可以完成所有任务
     * 否则能最多完成的任务为 其它任务总和 * 2 + 1
     */
    public long numberOfWeeks(int[] milestones) {
        long maxTasks = milestones[0];
        long totalTask = 0;
        for (int i = 0; i < milestones.length; i++) {
            maxTasks = Math.max(maxTasks, milestones[i]);
            totalTask += milestones[i];
        }
        long rest = totalTask - maxTasks;
        if (maxTasks <= rest + 1) {
            return totalTask;
        } else {
            return rest * 2 + 1;
        }
    }

    /** 采用贪心的方式, 每次从任务数最多的任务减一, 并且将上次的任务放回；
     *  问题：时间复杂度很高，0(n * m)*/
    public long numberOfWeeks1(int[] milestones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> (b - a));
        for (int i = 0; i < milestones.length; i++) {
            queue.offer(milestones[i]);
        }

        int weeks = 1;
        int currentWord = queue.poll();
        while (!queue.isEmpty()) {
            int nextWork = queue.poll();
            if (currentWord - 1 > 0) {
                queue.offer(currentWord - 1);
            }
            currentWord = nextWork;
            weeks++;
        }

        return weeks;
    }
}
