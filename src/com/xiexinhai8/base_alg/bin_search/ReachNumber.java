package com.xiexinhai8.base_alg.bin_search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 754. 到达终点数字
 *
 * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 * 你可以做一些数量的移动 numMoves :
 * 每次你可以选择向左或向右移动。
 * 第 i 次移动（从  i == 1 开始，到 i == numMoves ），在选择的方向上走 i 步。
 * 给定整数 target ，返回 到达目标所需的 最小 移动次数(即最小 numMoves ) 。
 */
public class ReachNumber {

    public static void main(String[] args) {
        System.out.println(new ReachNumber().reachNumber(100000));
    }

    public int reachNumber(int target) {

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        int step = 0;

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            Set<Integer> visited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                int curMil = queue.poll();
                if (curMil == target) {
                    return step - 1;
                }
                if (!visited.contains(curMil - step)) {
                    queue.offer(curMil - step);
                    visited.add(curMil - step);
                }
                if (!visited.contains(curMil + step)) {
                    queue.offer(curMil + step);
                    visited.add(curMil + step);
                }
            }
        }
        return 0;
    }
}
