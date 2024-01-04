package com.xiexinhai8.base_alg.back_trace.combinations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2397. 被列覆盖的最多行数
 *
 * 给你一个下标从 0 开始、大小为 m x n 的二进制矩阵 matrix ；另给你一个整数 numSelect，表示你必须从 matrix 中选择的 不同 列的数量。
 *
 * 如果一行中所有的 1 都被你选中的列所覆盖，则认为这一行被 覆盖 了。
 *
 * 形式上，假设 s = {c1, c2, ...., cnumSelect} 是你选择的列的集合。对于矩阵中的某一行 row ，如果满足下述条件，则认为这一行被集合 s 覆盖：
 *
 * 对于满足 matrix[row][col] == 1 的每个单元格 matrix[row][col]（0 <= col <= n - 1），col 均存在于 s 中，或者
 * row 中 不存在 值为 1 的单元格。
 * 你需要从矩阵中选出 numSelect 个列，使集合覆盖的行数最大化。
 *
 * 返回一个整数，表示可以由 numSelect 列构成的集合 覆盖 的 最大行数 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2024-01-04
 */
public class MaximumRows {

    Set<List<Integer>> colSet = new HashSet<>();

    public int maximumRows(int[][] matrix, int numSelect) {
        List<Integer> path = new ArrayList<>();
        int[] cols = new int[matrix[0].length];
        for (int i = 0; i < cols.length; i++) {
            //System.out.print(i + ", ");
            cols[i] = i;
        }
        bt(cols, 0, numSelect, path);
        // System.out.println(colSet);
        int maxCover = 0;
        for (List<Integer> chooseCols : colSet) {
            int cover = findCover(matrix, chooseCols);
            if (cover > maxCover) {
                maxCover = cover;
            }
        }

        return maxCover;
    }

    private void bt(int[] cols, int index, int target, List<Integer> path) {

        if (path.size() == target) {
            colSet.add(new ArrayList<>(path));
            return;
        }

        if (index >= cols.length) {
            return;
        }

        bt(cols, index + 1, target, path);
        path.add(cols[index]);
        bt(cols, index + 1, target, path);
        path.remove(path.size() - 1);

    }

    private int findCover(int[][] matrix, List<Integer> chooseCols) {
        int cover = 0;

        for (int row = 0; row < matrix.length; row++) {
            boolean covered = true;
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] != 0 && !chooseCols.contains(col)) {
                    covered = false;
                    break;
                }
            }
            if (covered) {
                cover++;
            }
        }
        return cover;
    }
}
