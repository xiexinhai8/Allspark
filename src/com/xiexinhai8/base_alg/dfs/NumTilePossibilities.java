package com.xiexinhai8.base_alg.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * 1079. 活字印刷
 *
 * 你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。
 *
 * 注意：本题中，每个活字字模只能使用一次。
 *
 * 相似题型:
 * 1、47. 全排列 II https://leetcode.cn/problems/permutations-ii/description/
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-05-19
 */
public class NumTilePossibilities {

    public int numTilePossibilities(String tiles) {
        /** 思路1: 深度优先遍历, 每次遍历一个节点都把对应的字符串给放到字符串集合中
         输出字符串集合的大小
         */
        Set<String> set = new HashSet<>();
        boolean[] visited = new boolean[tiles.length()];
        StringBuilder sb = new StringBuilder();

        dfs(0, tiles, set, visited, sb);

        return set.size();
    }

    private void dfs(int level, String tiles, Set<String> set, boolean[] visited, StringBuilder sb) {
        if (level >= tiles.length()) {
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            sb.append(tiles.charAt(i));
            set.add(sb.toString());
            dfs(level + 1, tiles, set, visited, sb);
            visited[i] = false;
            sb.delete(sb.length() - 1, sb.length());

        }
    }
}
