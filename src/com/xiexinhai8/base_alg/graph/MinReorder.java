package com.xiexinhai8.base_alg.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 1466. 重新规划路线
 * 
 * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 *
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 *
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 *
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 *
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-07
 */
public class MinReorder {

    /**
     层序遍历图, 队列中放置的是目标地点;
     每次处理一个目标地点时, 确认和它相连的点(出度 + 入度(不包括父节点)), 加入队列中


     */
    public int minReorder(int n, int[][] connections) {
        List<Integer>[] outs = new ArrayList[n];
        List<Integer>[] ins = new ArrayList[n];
        Arrays.setAll(outs, a -> new ArrayList<>());
        Arrays.setAll(ins, a -> new ArrayList<>());

        for (int i = 0; i < connections.length; i++) {
            int origin = connections[i][0];
            int des = connections[i][1];
            outs[origin].add(des);
            ins[des].add(origin);
        }

        int ans = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, -1});

        while (!queue.isEmpty()) {
            int[] city = queue.poll();

            for (int out : outs[city[0]]) {
                if (out != city[1]) {
                    queue.offer(new int[]{out, city[0]});
                    ans++;
                }
            }

            for (int in : ins[city[0]]) {
                if (in != city[1]) {
                    queue.offer(new int[]{in, city[0]});
                }
            }
        }

        return ans;
    }
}
