package com.xiexinhai8.common.util;

import java.util.List;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-10-31
 */
public class Printer {

    public static <T> void printList(List<T> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.print("]");
        System.out.println("");
    }

    public static <T> void printArray(T[] list) {
        System.out.print("[");
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + ", ");
        }
        System.out.print("]");
        System.out.println("");
    }

    public static void printArray(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.print("]");
        System.out.println("");
    }

    /*private void printZoneMap(Map<Integer, Integer> signalMap, int xLen, int yLen) {

        for (int i = 0; i < xLen; i++) {

            for (int j = 0; j < yLen; j++) {
                System.out.print(String.format("%3d",signalMap.getOrDefault(, 0)));
            }
            System.out.println("");
        }
    }*/
}
