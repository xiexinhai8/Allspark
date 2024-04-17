package com.xiexinhai8.base_alg.union;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 399. 除法求值
 *
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个
 * Ai 或 Bi 是一个表示单个变量的字符串。
 *
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 *
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 *
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 *
 * 注意：未在等式列表中出现的变量是未定义的，因此无法确定它们的答案。
 *
 * @author xxh
 * Created on 2024-04-17
 */
public class CalcEquation {


    /**
     * 此问题可以建模成一个没有方向的图的路径问题, 如果不可达或者达到每一目的地的路径有多条且代价不一样则可视为没有答案
     *
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 获取所有节点
        Set<String> nodes = new HashSet<>();
        for (int i = 0; i < equations.size(); i++) {
            nodes.add(equations.get(i).get(0));
            nodes.add(equations.get(i).get(1));
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String source = queries.get(i).get(0);
            String target = queries.get(i).get(1);
            // 判断查询节点是否在图中
            if (!nodes.contains(source) || !nodes.contains(target)) {
                result[i] = -1;
                continue;
            }
            // dfs查询图
            boolean[] visited = new boolean[equations.size()];
            dfs(1, source, target, equations, values, visited, i, result);
            if (result[i] == 0) {
                result[i] = -1;
            }
        }

        return result;

    }

    private void dfs(double score, String cur, String target, List<List<String>> equations, double[] values, boolean[] visited, int query, double[] result) {
        if (cur.equals(target)) {
            if (result[query] != 0 && result[query] != score) {
                result[query] = -1;
            } else {
                result[query] = score;
            }
            return;
        }
        if (result[query] == -1 || noPath(visited)) {
            return;
        }

        for (int i = 0; i < equations.size(); i++) {
            if (visited[i]) {
                continue;
            }
            String from = null;
            String to = null;
            double curScore = score;
            if (equations.get(i).get(0).equals(cur)) {
                from = equations.get(i).get(0);
                to = equations.get(i).get(1);
                curScore *= values[i];
            }
            if (equations.get(i).get(1).equals(cur)) {
                from = equations.get(i).get(1);
                to = equations.get(i).get(0);
                curScore /= values[i];
            }
            if (from == null) {
                continue;
            }
            visited[i] = true;
            //System.out.println(curScore);
            dfs(curScore, to, target, equations, values, visited, query, result);
            visited[i] = false;
        }
    }

    private boolean noPath(boolean[] visited) {

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
