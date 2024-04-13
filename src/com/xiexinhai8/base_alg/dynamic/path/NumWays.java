package com.xiexinhai8.base_alg.dynamic.path;

/**
 * LCP 07. 传递信息
 *
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 *
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 * @author xxh
 * Created on 2024-04-13
 */
public class NumWays {

    /**
     * dfs
     */
    int ways = 0;
    int target;
    public int numWays_dfs(int n, int[][] relation, int k) {
        target = n - 1;
        bfs(0, relation, k);
        return ways;
    }

    private void bfs(int start, int[][] paths, int step) {
        if (step == 0) {
            if (start == target) {
                ways++;
            }
            return;
        }

        for (int[] path : paths) {
            if (path[0] == start) {
                bfs(path[1], paths, step - 1);
            }
        }
    }


    /**动态规划
     *
     * 本题计算的是经过k步, 从0 - n-1 的路径数, 不要求计算真实的路径, 可以采用动态规划解决
     *
     * f(i, j) 为从0到j经过i步的路径数, 其中f(0, 0)=1;
     * 最后的结果是计算f(k, n - 1);
     */
    public int numWays_dy(int n, int[][] relation, int k) {
        boolean[][] adj = new boolean[n][n];

        for (int i = 0; i < relation.length; i++) {
            adj[relation[i][0]][relation[i][1]] = true;
        }

        int[][] f = new int[k + 1][n];
        f[0][0] = 1;

        for (int step = 1; step <= k; step++) {

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adj[i][j] && f[step - 1][i] > 0) {
                        f[step][j] += f[step - 1][i];
                    }
                }
            }
        }

        return f[k][n - 1];
    }
}
