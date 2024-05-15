package com.xiexinhai8.base_alg.scense.shortest_path;

import java.util.Arrays;

/**
 * 1334. 阈值距离内邻居最少的城市
 *
 * 有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，距离阈值是一个整数
 * distanceThreshold。
 *
 * 返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。如果有多个这样的城市，则返回编号最大的城市。
 *
 * 注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。
 * @author xxh
 * Created on 2024-05-15
 */
public class FindTheCity {

    int NO_GOT = Integer.MAX_VALUE / 2;

    /**
     * 计算图中所有点的最短距离使用 floyd 算法
     * 方法: 本题其实需要计算出图中所有点之间的最小距离, 可以采用floyd算法计算出所有点的最小距离, 最后再去找出合规的城市
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // 构建邻接矩阵
        int[][] adj = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(adj[i], NO_GOT);
        }
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int distance = edges[i][2];
            adj[from][to] = distance;
            adj[to][from] = distance;
        }

        // 遍历所有中间节点并更新邻接矩阵
        for (int i = 0; i < n; i++) {

            for (int in = 0; in < n; in++) {
                // 如果入度和中间节点没有连通, 剪枝
                if (adj[in][i] == NO_GOT) {
                    continue;
                }
                for (int out = 0; out < n; out++) {
                    // 如果入度和出度都有连接, 更新邻接矩阵
                    if (in != out && adj[i][out] != NO_GOT) {
                        adj[in][out] = Math.min(adj[in][out], adj[in][i] + adj[i][out]);
                    }
                }
            }
        }

        int minNum = n;
        int cityName = -1;
        for (int i = 0; i < n; i++) {
            int num = 0;
            for (int j = 0; j < n; j++) {
                // System.out.print(adj[i][j] + ", ");
                if (adj[i][j] <= distanceThreshold) {
                    num++;
                }
            }
            // System.out.println();
            // 由于规定距离内经过城市相同时取城市号大的, 所有num <= minNum需要替换城市号
            if (num <= minNum) {
                cityName = i;
                minNum = num;
            }
        }

        return cityName;
    }
}
