package com.xiexinhai8.base_alg.scense.shortest_path;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2642. 设计可以求最短路径的图类
 * <p>
 * 给你一个有 n 个节点的 有向带权 图，节点编号为 0 到 n - 1 。图中的初始边用数组 edges 表示，其中 edges[i] = [fromi, toi, edgeCosti] 表示从 fromi 到 toi
 * 有一条代价为 edgeCosti 的边。
 * <p>
 * 请你实现一个 Graph 类：
 * Graph(int n, int[][] edges) 初始化图有 n 个节点，并输入初始边。
 * addEdge(int[] edge) 向边集中添加一条边，其中 edge = [from, to, edgeCost] 。数据保证添加这条边之前对应的两个节点之间没有有向边。
 * int shortestPath(int node1, int node2) 返回从节点 node1 到 node2 的路径 最小 代价。如果路径不存在，返回 -1 。一条路径的代价是路径中所有边代价之和。
 *
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
     *
     * @return 路径不存在返回-1
     */
    public int shortestPath(int node1, int node2) {
        return dijkstra(node1, node2);
    }

    /**
     * 求两点最短路径， 使用dijkstra算法
     *
     * 使用小顶堆获取最短路径
     */
    private int dijkstra(int node1, int node2) {
        int[] dis = new int[size];
        // 使用小顶堆，快速找出未visit的最短路径节点
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        boolean[] visited = new boolean[size];
        queue.offer(new int[] {node1, 0});
        Arrays.fill(dis, NO_LINK);
        dis[node1] = 0;

        // 如果堆为空说明node2无法到达
        while (!queue.isEmpty()) {
            // queue不为空且堆顶的节点已经visit了，推出对顶节点
            while (!queue.isEmpty() && visited[queue.peek()[0]]) {
                queue.poll();
            }
            // 如果堆为空说明node2无法到达
            if (queue.isEmpty()) {
                break;
            }
            int[] nodeInfo = queue.poll();
            int curDis = nodeInfo[1];
            int curPos = nodeInfo[0];
            visited[curPos] = true;
            // 找到目标节点时退出
            if (curPos == node2) {
                return curDis;
            }

            // 根据当前最短路径节点将新发现的节点加入堆
            for (int i = 0; i < size; i++) {
                if (adj[curPos][i] == NO_LINK) {
                    continue;
                }
                queue.offer(new int[] {i, Math.min(dis[i], curDis + adj[curPos][i])});
            }
        }
        // 返回-1(无法从node1 到 node2)
        return -1;
    }

    /**
     * 求两点最短路径， 使用dijkstra算法
     * 使用遍历dis数组获取最短路径
     */
    private int dijkstra1(int node1, int node2) {
        int[] dis = new int[size];
        boolean[] visited = new boolean[size];
        Arrays.fill(dis, NO_LINK);
        dis[node1] = 0;

        // 循环时可以不设置退出条件, 只要找不到最小的路径或找到目标节点时退出(没有visit的节点都是NO_LINK状态的)
        // 但是为了保证程序的稳定性， 使用epoch作为循环结束条件，防止死循环
        int epoch = size;
        while (epoch > 0) {
            epoch--;
            int minPos = findShortPath(dis, visited);
            // 没有找到最小路径时退出
            if (minPos < 0) {
                break;
            }
            visited[minPos] = true;
            // 找到目标节点时退出
            if (minPos == node2) {
                return dis[node2];
            }

            // 根据当前最短路径节点更新dis数组
            for (int i = 0; i < size; i++) {
                if (adj[minPos][i] == NO_LINK) {
                    continue;
                }
                dis[i] = Math.min(dis[i], dis[minPos] + adj[minPos][i]);
            }
        }
        // 如果最短路径已经是NO_LINK状态了, 返回-1(无法从node1 到 node2)
        return -1;
    }

    // 遍历dis数组, 找出没有visit的最短路径节点; 需要满足此节点路径小于NO_LINK; 找不到返回-1
    private int findShortPath(int[] dis, boolean[] visited) {
        //初始化最短路径为NO_LINK, 最短路径节点为-1
        int shortValue = NO_LINK;
        int shortPos = -1;

        for (int i = 0; i < dis.length; i++) {
            // 如果当前节点没有visit且当前节点路径小于shortValue; 更新路径值和节点号
            if (!visited[i] && dis[i] < shortValue) {
                shortValue = dis[i];
                shortPos = i;
            }
        }
        return shortPos;
    }

}
