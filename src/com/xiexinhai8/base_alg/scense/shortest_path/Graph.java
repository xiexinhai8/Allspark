package com.xiexinhai8.base_alg.scense.shortest_path;

import java.util.Arrays;

/**
 * 2642. 设计可以求最短路径的图类
 *
 * 给你一个有 n 个节点的 有向带权 图，节点编号为 0 到 n - 1 。图中的初始边用数组 edges 表示，其中 edges[i] = [fromi, toi, edgeCosti] 表示从 fromi 到 toi
 * 有一条代价为 edgeCosti 的边。
 *
 * 请你实现一个 Graph 类：
 * Graph(int n, int[][] edges) 初始化图有 n 个节点，并输入初始边。
 * addEdge(int[] edge) 向边集中添加一条边，其中 edge = [from, to, edgeCost] 。数据保证添加这条边之前对应的两个节点之间没有有向边。
 * int shortestPath(int node1, int node2) 返回从节点 node1 到 node2 的路径 最小 代价。如果路径不存在，返回 -1 。一条路径的代价是路径中所有边代价之和。
 * @author xxh
 * Created on 2024-05-14
 */
public class Graph {
    int[][] adj;
    int size;
    int NO_LINK = Integer.MAX_VALUE;

    public Graph(int n, int[][] edges) {
        this.size = n;
        this.adj = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(adj[i], NO_LINK);
        }
        for (int i = 0; i < edges.length; i++) {
            adj[edges[i][0]][edges[i][1]] = edges[i][2];
        }
    }

    public void addEdge(int[] edge) {
        adj[edge[0]][edge[1]] = edge[2];
    }

    /**
     * 两点之间的最短路径， 采用dijkstra算法
     * @param node1
     * @param node2
     * @return 路径不存在返回-1
     */
    public int shortestPath(int node1, int node2) {
        return dijkstra(node1, node2);
    }

    private int dijkstra(int node1, int node2) {
        int[] dis = new int[size];
        boolean[] visited = new boolean[size];
        Arrays.fill(dis, NO_LINK);
        dis[node1] = 0;

        while (true) {
            int minPos = findShortPath(dis, visited);
            if (minPos < 0) {
                break;
            }
            visited[minPos] = true;
            if (minPos == node2) {
                return dis[node2];
            }

            for (int i = 0; i < size; i++) {
                if (adj[minPos][i] == NO_LINK) {
                    continue;
                }
                dis[i] = Math.min(dis[i], dis[minPos] + adj[minPos][i]);
            }
        }
        return -1;
    }

    private int findShortPath(int[] dis, boolean[] visited) {
        int shortValue = NO_LINK;
        int shortPos = -1;

        for (int i = 0; i < dis.length; i++) {
            if (!visited[i] && dis[i] < shortValue) {
                shortValue = dis[i];
                shortPos = i;
            }
        }
        return shortPos;
    }
}
