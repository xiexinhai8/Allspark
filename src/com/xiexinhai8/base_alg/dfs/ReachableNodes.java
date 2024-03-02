package com.xiexinhai8.base_alg.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 2368. 受限条件下可到达节点的数目
 *
 * 现有一棵由 n 个节点组成的无向树，节点编号从 0 到 n - 1 ，共有 n - 1 条边。
 *
 * 给你一个二维整数数组 edges ，长度为 n - 1 ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。另给你一个整数数组 restricted 表示 受限 节点。
 *
 * 在不访问受限节点的前提下，返回你可以从节点 0 到达的 最多 节点数目。
 *
 * 注意，节点 0 不 会标记为受限节点
 * @author xxh
 * Created on 2024-03-02
 */
public class ReachableNodes {

    int scanNum = 0;

    /**
     * 适用map模拟邻接表， 解决稀疏节点内存消耗问题
     * @param n
     * @param edges
     * @param restricted
     * @return
     */
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Map<Integer, List<Integer>> adj = generateAdj(edges, n);
        Set restrictedSet = new HashSet();
        for (int restrictedEle : restricted) {
            restrictedSet.add(restrictedEle);
        }

        dfs(-1, 0, adj, restrictedSet);

        return scanNum;
    }

    private Map<Integer, List<Integer>> generateAdj(int[][] edges, int size) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            adj.putIfAbsent(edges[i][0], new ArrayList<>());
            adj.putIfAbsent(edges[i][1], new ArrayList<>());
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        return adj;
    }

    private void dfs(int pre, int start, Map<Integer, List<Integer>> adj, Set restrictedSet) {
        if (restrictedSet.contains(start)) {
            return;
        }

        scanNum++;
        List<Integer> adjEles = adj.get(start);
        for (int i = 0; i < adjEles.size(); i++) {
            if (adjEles.get(i) == pre) {
                continue;
            }
            dfs(start, adjEles.get(i), adj, restrictedSet);
        }
    }

    /**
     * 采用邻接表的方式实现， 缺点是内存耗费比较大
     * @param n
     * @param edges
     * @param restricted
     * @return
     */
    public int reachableNodes1(int n, int[][] edges, int[] restricted) {
        int[][] adj = generateAdj1(edges, n);
        Set restrictedSet = new HashSet();
        for (int restrictedEle : restricted) {
            restrictedSet.add(restrictedEle);
        }

        dfs1(-1, 0, adj, restrictedSet);

        return scanNum;
    }

    private int[][] generateAdj1(int[][] edges, int size) {
        int[][] adj = new int[size][size];

        for (int i = 0; i < edges.length; i++) {

            adj[edges[i][0]][edges[i][1]] = 1;
            adj[edges[i][1]][edges[i][0]] = 1;
        }
        return adj;
    }

    private void dfs1(int pre, int start, int[][] adj, Set restrictedSet) {
        if (restrictedSet.contains(start)) {
            return;
        }

        scanNum++;
        for (int i = 0; i < adj[start].length; i++) {
            if (adj[start][i] == 0 || i == pre) {
                continue;
            }
            dfs1(start, i, adj, restrictedSet);
        }
    }

}
