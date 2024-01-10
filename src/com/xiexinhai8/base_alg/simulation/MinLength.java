package com.xiexinhai8.base_alg.simulation;

/**
 * 2696. 删除子串后的字符串最小长度
 *
 * 给你一个仅由 大写 英文字符组成的字符串 s 。
 *
 * 你可以对此字符串执行一些操作，在每一步操作中，你可以从 s 中删除 任一个 "AB" 或 "CD" 子字符串。
 *
 * 通过执行操作，删除所有 "AB" 和 "CD" 子串，返回可获得的最终字符串的 最小 可能长度。
 *
 * 注意，删除子串后，重新连接出的字符串可能会产生新的 "AB" 或 "CD" 子串。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2024-01-10
 */
public class MinLength {

    public int minLength(String s) {
        char[] sc = s.toCharArray();

        int first = 0, second = 1;

        while (second < s.length()) {
            if (valid(first, second, sc)) {
                sc[first] = 0;
                sc[second] = 0;
                while (first >= 0 && sc[first] == 0) {
                    first -= 1;
                }
                second += 1;
            } else {
                first = second;
                second += 1;
            }
        }

        int counter = 0;
        for (int i = 0; i < sc.length; i++) {
            if (sc[i] == 0) {
                continue;
            }
            counter++;
        }

        return counter;
    }

    private boolean valid(int first, int second, char[] sc) {
        if (first < 0) {
            return false;
        }
        if (sc[first] == 'A' && sc[second] == 'B') {
            return true;
        }
        if (sc[first] == 'C' && sc[second] == 'D') {
            return true;
        }
        return false;
    }
}
