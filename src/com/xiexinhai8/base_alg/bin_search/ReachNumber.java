package com.xiexinhai8.base_alg.bin_search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class ReachNumber {

    public static void main(String[] args) {
        System.out.println(new ReachNumber().reachNumber(100000));
    }

    public int reachNumber(int target) {

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        int step = 0;

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            Set<Integer> visited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                int curMil = queue.poll();
                if (curMil == target) {
                    return step - 1;
                }
                if (!visited.contains(curMil - step)) {
                    queue.offer(curMil - step);
                    visited.add(curMil - step);
                }
                if (!visited.contains(curMil + step)) {
                    queue.offer(curMil + step);
                    visited.add(curMil + step);
                }
            }
        }
        return 0;
    }
}
