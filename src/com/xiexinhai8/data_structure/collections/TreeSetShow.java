package com.xiexinhai8.data_structure.collections;

import java.util.Random;
import java.util.TreeSet;

/**
 * TreeSet 底层采用TreeMap（红黑树)实现，treeSet中的数据能够实现有序排列
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-11-17
 */
public class TreeSetShow<T> {

    public static void main(String[] args) {
        TreeSetShow.testCeiling();
        TreeSetShow.testFloor();
    }

    // floor(n): <= n
    public static void testFloor() {
        TreeSet<Integer> treeMap = new TreeSet<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            treeMap.add(i);
        }
        System.out.println("3 floor: " + treeMap.floor(3));
    }

    // ceiling(n): >= n
    public static void testCeiling() {
        TreeSet<Integer> treeMap = new TreeSet<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            treeMap.add(i);
        }
        System.out.println("3 floor: " + treeMap.ceiling(3));
    }
}
