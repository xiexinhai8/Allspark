package com.xiexinhai8.base_alg.back_trace;

import java.util.ArrayList;
import java.util.List;

/**
 * 1219. 黄金矿工
 *
 * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
 *
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
 *
 * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
 * 矿工每次可以从当前位置向上下左右四个方向走。
 * 每个单元格只能被开采（进入）一次。
 * 不得开采（进入）黄金数目为 0 的单元格。
 * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-11-08
 */
class GetMaximumGold {
    int maxValue = 0;
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 判断从哪个点出发收益最大
     * @param grid
     * @return
     */
    public int getMaximumGold(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        List<Integer> path = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                bt(i, j, visited, grid, 0, path);
            }
        }
        return maxValue;

    }

    private void bt(int row, int col, boolean[][] visited, int[][] grid, int curValue, List<Integer> path) {
        if (visited[row][col] || grid[row][col] == 0) {
            return;
        }

        visited[row][col] = true;
        curValue += grid[row][col];
        // path.add(grid[row][col]);
        // 发现更大收益点后输出结果
        if (curValue > maxValue) {
            maxValue = curValue;
            //System.out.println(path);
        }
        for (int i = 0; i < dir.length; i++) {
            int newRow = row + dir[i][0];
            int newCol = col + dir[i][1];
            if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length) {
                continue;
            }
            bt(newRow, newCol, visited, grid, curValue, path);
        }

        // path.remove(path.size() - 1);
        visited[row][col] = false;
        curValue -= grid[row][col];

    }
}
