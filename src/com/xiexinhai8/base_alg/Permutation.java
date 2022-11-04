package com.xiexinhai8.base_alg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-09-14
 */
public class Permutation {
    List<String> r = new ArrayList<>();

    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        boolean[] used = new boolean[chars.length];
        Deque<Character> path = new ArrayDeque<>();

        dfs(chars, used, path);
        String[] arr = new String[r.size()];
        r.toArray(arr);
        return arr;
    }

    private void dfs(char[] chars, boolean[] used, Deque<Character> path) {
        if (path.size() >= chars.length) {
            r.add(toString(path));
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) {
                continue;
            }
            path.push(chars[i]);
            used[i] = true;
            dfs(chars, used, path);
            path.pop();
            used[i] = false;
        }
    }

    private String toString(Deque<Character> path) {
        return new String(path.stream().toString());
    }



    /*public String[] permutation(String s) {
        List<String> ret = new ArrayList<String>();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        do {
            ret.add(new String(arr));
        } while (nextPermutation(arr));
        int size = ret.size();
        String[] retArr = new String[size];
        for (int i = 0; i < size; i++) {
            retArr[i] = ret.get(i);
        }
        return retArr;
    }

    public boolean nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }*/


}
