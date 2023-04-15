package com.xiexinhai8.base_alg.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1042. 不邻接植花
 *
 * 有 n 个花园，按从 1 到 n 标记。另有数组 paths ，其中 paths[i] = [xi, yi] 描述了花园 xi 到花园 yi 的双向路径。在每个花园中，你打算种下四种花之一。
 *
 * 另外，所有花园 最多 有 3 条路径可以进入或离开.
 *
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 *
 * 以数组形式返回 任一 可行的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1、2、3、4 表示。保证存在答案。
 *
 * @author xxh
 * Created on 2023-04-15
 */
public class GardenNoAdj {

    public int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer> flowers = Arrays.asList(1, 2, 3, 4);
        int[] colors = new int[n];

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < paths.length; i++) {
            map.putIfAbsent(paths[i][0] - 1, new HashSet<>());
            map.putIfAbsent(paths[i][1] - 1, new HashSet<>());
            map.get(paths[i][0] - 1).add(paths[i][1] - 1);
            map.get(paths[i][1] - 1).add(paths[i][0] - 1);
        }

        for (int i = 0; i < n; i++) {
            Set<Integer> adj = map.get(i);
            colors[i] = chooseColor(adj, colors, flowers);
        }

        return colors;
    }

    private int chooseColor(Set<Integer> adjs, int[] colors, List<Integer> flowers) {
        Set<Integer> candidateFlowers = new HashSet<>();
        if (adjs == null) {
            return flowers.get(0);
        }
        for (int adj : adjs) {
            candidateFlowers.add(colors[adj]);
        }
        for (int flower : flowers) {
            if (!candidateFlowers.contains(flower)) {
                return flower;
            }
        }
        return 1;
    }
}
