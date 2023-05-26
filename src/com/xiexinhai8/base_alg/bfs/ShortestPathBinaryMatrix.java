package com.xiexinhai8.base_alg.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 1091. 二进制矩阵中的最短路径
 *
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 *
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 *
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-05-26
 */
public class ShortestPathBinaryMatrix {

    public static void main(String[] args) {
        int[][] grid = {{0,1},{1,0}};
        System.out.println(new ShortestPathBinaryMatrix().shortestPathBinaryMatrix(grid));
    }

    int[][] seed = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        /**思路1: 动态规划 (重点[n-1, n-1] 可能存在的路径为 [n-1, n-2], [n-2, n-2], [n-2, n-1])
         问题: 到每个点都有八个方向需要计算, 所以导致无法使用动态规划(动态规划需要先计算出词表, 但是这个词表不太固定[会产生循环依赖])
         */
        /** 思路2: bfs, 遍历层数就是最短路径
         设计visited 数组, 访问过的不再访问
         如果到最后队列为空还是找不到target, 证明无法通达
         */
        if (grid[0][0] == 1) {
            return -1;
        }

        int scop = grid.length;
        boolean[][] visited = new boolean[scop][scop];

        int level = 1;
        visited[0][0] = true;
        Deque<Axis> queue = new ArrayDeque<>();
        queue.offer(new Axis(0, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Axis coordination = queue.poll();
                if (coordination.x == (scop - 1) && coordination.y == (scop - 1)) {
                    return level;
                }
                List<Axis> candidates = getCandidates(coordination, visited, grid, size);
                for (int j = 0; j < candidates.size(); j++) {
                    Axis coor = candidates.get(j);
                    queue.offer(coor);
                    visited[coor.x][coor.y] = true;
                }
            }
            level++;
        }

        return -1;
    }

    private List<Axis> getCandidates(Axis coor, boolean[][] visited, int[][] grid, int size) {
        List<Axis> candidates = new ArrayList<>();
        for (int i = 0; i < seed.length; i++) {
            int x = coor.x + seed[i][0];
            int y = coor.y + seed[i][1];
            if (x < 0 || x >= size || y < 0 || y >= size
                    || grid[x][y] == 1 || visited[x][y] == true) {
                continue;
            }
            candidates.add(new Axis(x, y));
        }
        return candidates;
    }

    static class Axis {
        int x;
        int y;
        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
