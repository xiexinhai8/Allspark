package com.xiexinhai8.base_alg.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1620. 网络信号最好的坐标
 *
 *给你一个数组 towers 和一个整数 radius 。
 * 数组  towers  中包含一些网络信号塔，其中 towers[i] = [xi, yi, qi] 表示第 i 个网络信号塔的坐标是 (xi, yi) 且信号强度参数为 qi 。所有坐标都是在  X-Y
 * 坐标系内的 整数 坐标。两个坐标之间的距离用 欧几里得距离 计算。
 * 整数 radius 表示一个塔 能到达 的 最远距离 。如果一个坐标跟塔的距离在 radius 以内，那么该塔的信号可以到达该坐标。在这个范围以外信号会很微弱，所以 radius 以外的距离该塔是 不能到达的 。
 * 如果第 i 个塔能到达 (x, y) ，那么该塔在此处的信号为 ⌊qi / (1 + d)⌋ ，其中 d 是塔跟此坐标的距离。一个坐标的 信号强度 是所有 能到达 该坐标的塔的信号强度之和。
 * 请你返回数组 [cx, cy] ，表示 信号强度 最大的 整数 坐标点 (cx, cy) 。如果有多个坐标网络信号一样大，请你返回字典序最小的 非负 坐标。
 * 注意：
 * 坐标 (x1, y1) 字典序比另一个坐标 (x2, y2) 小，需满足以下条件之一：
 * 要么 x1 < x2 ，
 * 要么 x1 == x2 且 y1 < y2 。
 * ⌊val⌋ 表示小于等于 val 的最大整数（向下取整函数）。

 */
public class BestCoordinate {

    public static void main(String[] args) {
        /*int[][] towers = {
                {2, 1, 9},
                {0, 1, 9},
                {3, 4, 10},
                {20, 20, 50},
                {20, 30, 50},
                {20, 10, 50},
                {10, 20, 50},
                {30, 20, 50}};*/
        int[][] towers = {
                {2, 1, 9},
                {0, 1, 9},
                {3, 4, 10},
                {50, 50, 50},
                {20, 20, 50},
                {70, 20, 50},
                {20, 70, 50},
                {70, 70, 50}};
        int radius = 70;
        long timeStamp = System.currentTimeMillis();
        int epoch = 1;
        for (int i = 0; i < epoch; i++) {
            new BestCoordinate().bestCoordinate(towers, radius);
            // Printer.printArray(new BestCoordinate().bestCoordinate(towers, radius));
        }
        System.out.println("take time: " + (System.currentTimeMillis() - timeStamp) / epoch);
    }

    /**
     * 信号叠加原理，通过对信号塔进行遍历，通过dfs对信号塔周围坐标进行遍历，叠加记录其信号值
     *
     * 这个需要注意是由于计算的是圆内所有点，可能存在栈溢出风险
     * @param towers
     * @param radius
     * @return
     */
    public int[] bestCoordinate(int[][] towers, int radius) {
        Map<Integer, Integer> signal = new HashMap<>();

        for (int i = 0; i < towers.length; i++) {
            Set<Integer> visited = new HashSet<>();
            dfs(towers, towers[i][0], towers[i][1], i, visited, signal, radius, 1);
        }

        int maxKey = 0;
        int maxValue = 0;
        for (Map.Entry<Integer, Integer> entry : signal.entrySet()) {
            if (maxValue < entry.getValue()) {
                maxKey = entry.getKey();
                maxValue = entry.getValue();
            } else if (maxValue == entry.getValue()) {
                if (maxKey > entry.getKey()) {
                    maxKey = entry.getKey();
                }
            }
        }

        printZoneMap(signal, 100, 100);

        return new int[]{getCoordinateX(maxKey), getCoordinateY(maxKey)};
    }

    private void dfs(int[][] towers, int x, int y, int center,
            Set<Integer> visited, Map<Integer, Integer> signalMap, int limit, int stack) {
        int key = calCoordinateKey(x, y);
        int cx = towers[center][0];
        int cy = towers[center][1];
        double radius = calRadius(x, y, cx, cy);
        int signal = calSignal(towers[center][2], radius);
        if (x < 0 || y < 0 || visited.contains(key) || radius > limit || signal < 0) {
            return;
        }
        visited.add(key);

        updateSignal(signalMap, key, signal);
        // System.out.println(stack);
        dfs(towers, x + 1, y, center, visited, signalMap, limit, stack + 1);
        dfs(towers, x - 1, y, center, visited, signalMap, limit, stack + 1);
        dfs(towers, x, y + 1, center, visited, signalMap, limit, stack + 1);
        dfs(towers, x, y - 1, center, visited, signalMap, limit, stack + 1);
    }

    private void updateSignal(Map<Integer, Integer> signalMap, int key, int signal) {
        int old = signalMap.getOrDefault(key, 0);
        signalMap.put(key, old + signal);
    }

    private int calSignal(int originSignal, double radius) {
        return new Double(originSignal / (1 + radius)).intValue();
    }

    private double calRadius(int x, int y, int cx, int cy) {
        return Math.sqrt((cx - x) * (cx - x) + (cy - y) * (cy -y));
    }


    private int calCoordinateKey(int x, int y) {
        return x * 1000 + y;
    }

    private int getCoordinateX(int v) {
        return v / 1000;
    }

    private int getCoordinateY(int v) {
        return v % 1000;
    }

    private void printZoneMap(Map<Integer, Integer> signalMap, int xLen, int yLen) {

        for (int i = 0; i < xLen; i++) {

            for (int j = 0; j < yLen; j++) {
                System.out.print(String.format("%3d",signalMap.getOrDefault(calCoordinateKey(i, j), 0)));
            }
            System.out.println("");
        }
    }
}
