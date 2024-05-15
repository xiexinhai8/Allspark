package com.xiexinhai8.base_alg.graph.min_path;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 2662. 前往目标的最小代价
 * <p>
 * 给你一个数组 start ，其中 start = [startX, startY] 表示你的初始位置位于二维空间上的 (startX, startY) 。另给你一个数组 target ，其中 target = [targetX,
 * targetY] 表示你的目标位置 (targetX, targetY) 。
 * <p>
 * 从位置 (x1, y1) 到空间中任一其他位置 (x2, y2) 的代价是 |x2 - x1| + |y2 - y1| 。
 * <p>
 * 给你一个二维数组 specialRoads ，表示空间中存在的一些特殊路径。其中 specialRoads[i] = [x1i, y1i, x2i, y2i, costi] 表示第 i 条特殊路径可以从 (x1i, y1i) 到
 * (x2i, y2i) ，但成本等于 costi 。你可以使用每条特殊路径任意次数。
 * <p>
 * 返回从 (startX, startY) 到 (targetX, targetY) 所需的最小代价。
 *
 * @author xxh
 * Created on 2024-04-18
 */
public class MinimumCost {
    // 最大值为1e5, 所以乘积需要1e6；最大整数为2e10+；所以坐标需要使用long
    long MULTI = (long) 1e6;

    /**
     * 最短路径问题: 采用dijkstra
     */
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        // 由于每个点之间都能通路, 所以这里采用adj来表示图
        //System.out.println(MULTI);

        // 去重除起始节点外的其它节点
        long startVal = start[0] * MULTI + start[1];
        long targetVal = target[0] * MULTI + target[1];
        //System.out.println(targetVal);
        // System.out.println(target[0] * 1L * target[1]);
        Set<Long> nodeSet = new HashSet<>();
        nodeSet.add(startVal);
        nodeSet.add(targetVal);
        for (int i = 0; i < specialRoads.length; i++) {
            nodeSet.add(specialRoads[i][0] * MULTI + specialRoads[i][1]);
            nodeSet.add(specialRoads[i][2] * MULTI + specialRoads[i][3]);
        }
        Map<Long, Integer> posLoc = new HashMap<>();
        long[] nodes = new long[nodeSet.size()];
        int cur = 0;
        for (long node : nodeSet) {
            nodes[cur] = node;
            posLoc.put(nodes[cur], cur);
            cur++;
        }
        // 创建邻接矩阵
        int[][] adj = new int[nodes.length][nodes.length];
        // 初始化邻接矩阵
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                // 通过定义计算两点的距离
                adj[i][j] = calDis(i, j, nodes, adj);
            }
        }
        for (int i = 0; i < specialRoads.length; i++) {
            int from = posLoc.get(specialRoads[i][0] * MULTI + specialRoads[i][1]);
            int to = posLoc.get(specialRoads[i][2] * MULTI + specialRoads[i][3]);
            adj[from][to] = Math.min(adj[from][to], specialRoads[i][4]);

        }

        // 通过dijkstra算法计算最短距离
        int[] minDis = new int[nodes.length];
        Arrays.fill(minDis, Integer.MAX_VALUE);
        boolean[] done = new boolean[nodes.length];
        minDis[posLoc.get(startVal)] = 0;
        int epoch = nodes.length;
        //print(minDis);
        while (epoch > 0) {
            epoch--;
            int minPos = findMinPos(minDis, done);
            // System.out.println("minPos = " + minPos);
            if (posLoc.get(targetVal).equals(minPos)) {
                return minDis[minPos];
            }
            done[minPos] = true;
            for (int i = 0; i < adj[0].length; i++) {
                minDis[i] = Math.min(minDis[i], minDis[minPos] + adj[minPos][i]);
            }
            //print(minDis);
        }
        // print(minDis);
        return -1;
    }

    private int findMinPos(int[] minDis, boolean[] done) {
        int minVal = Integer.MAX_VALUE;
        int minPos = -1;

        for (int i = 0; i < minDis.length; i++) {
            if (done[i]) {
                continue;
            }
            if (minVal >= minDis[i]) {
                minVal = minDis[i];
                minPos = i;
            }
        }
        return minPos;
    }

    private int calDis(int i, int j, long[] nodes, int[][] adj) {
        int iX = (int) (nodes[i] / MULTI);
        int iY = (int) (nodes[i] % MULTI);
        int jX = (int) (nodes[j] / MULTI);
        int jY = (int) (nodes[j] % MULTI);
        return Math.abs(iX - jX) + Math.abs(iY - jY);
    }

    private void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }
}
