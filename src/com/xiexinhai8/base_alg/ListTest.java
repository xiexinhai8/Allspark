package com.xiexinhai8.base_alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-08-23
 */
public class ListTest {

    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        List<Integer> l = new ArrayList<>();
        List<Integer> e = Arrays.asList(a);
        l.addAll(e);
        a[0] = 0;
        System.out.println(e);
        a[1] = 1;
        System.out.println(e);

        List<Integer> t = new ArrayList<>(10);
        // t.set(9, 9);
        System.out.println(t);

        String[] s = new String[10];
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
        a[0] = null;
        a[1] = null;
    }
}
