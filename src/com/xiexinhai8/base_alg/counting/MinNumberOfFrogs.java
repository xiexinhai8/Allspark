package com.xiexinhai8.base_alg.counting;

import java.util.HashMap;
import java.util.Map;

/**
 * 1419. 数青蛙
 *
 * 给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。
 *
 * 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
 *
 * 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 croakOfFrogs 不是由若干有效的
 * "croak" 字符混合而成，请返回 -1 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-05-06
 */
public class MinNumberOfFrogs {

    char[] music = new char[]{'c', 'r', 'o', 'a', 'k'};
    public int minNumberOfFrogs(String croakOfFrogs) {
        // 方案1、 双指针, 采用双指针的方式去匹配croak, 匹配上了就删除已匹配的数据

        // 方案2、频率计数, 最少的青蛙数量为:fre(c) - fre(k) 校验方法:c > r > o > a > k
        char[] music = new char[]{'c', 'r', 'o', 'a', 'k'};
        int minFrog = 1;
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            int frogNum = addInfo(freq, croakOfFrogs.charAt(i));
            if (frogNum < 0) {
                return -1;
            }
            if (frogNum > minFrog) {
                minFrog = frogNum;
            }
        }

        return freq.getOrDefault(music[0], 0) - freq.getOrDefault(music[music.length -1], 0) == 0 ? minFrog : -1;

    }

    public int addInfo(Map<Character, Integer> freq, char info) {
        int pre = Integer.MAX_VALUE;
        int curFre = freq.getOrDefault(info, 0) + 1;
        freq.put(info, curFre);
        for (char c : music) {
            if (pre < freq.getOrDefault(c, 0)) {
                return -1;
            }
            pre = freq.getOrDefault(c, 0);
        }
        return freq.getOrDefault(music[0], 0) - freq.getOrDefault(music[music.length -1], 0);

    }
}
