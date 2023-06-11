package com.xiexinhai8.base_alg.greedy;

import java.util.Arrays;

/**
 * 2611. 老鼠和奶酪
 *
 * 有两只老鼠和 n 块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。
 *
 * 下标为 i 处的奶酪被吃掉的得分为：
 *
 * 如果第一只老鼠吃掉，则得分为 reward1[i] 。
 * 如果第二只老鼠吃掉，则得分为 reward2[i] 。
 * 给你一个正整数数组 reward1 ，一个正整数数组 reward2 ，和一个非负整数 k 。
 *
 * 请你返回第一只老鼠恰好吃掉 k 块奶酪的情况下，最大 得分为多少。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-06-07
 */
public class MiceAndCheese {

    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        /** 思路: 每一块蛋糕肯定都会被吃掉, 要想让吃掉的蛋糕价值最高, 我们需要考虑这块蛋糕给谁吃价值高
         由于有块数限制, 所以我们可以建立一个增益表来记录一块蛋糕给2吃比给1吃的收益, 然后基于增益表排序, 找出n - k块增益最高的给2吃, 剩下的给1
         */
        int score = 0;
        int size = reward1.length;
        // 建立增益表
        int[][] shang = new int[size][2];
        for (int i = 0 ; i < size; i++) {
            shang[i] = new int[]{i, reward2[i] - reward1[i]};
        }

        // 增益表按增益排序
        Arrays.sort(shang, (s1, s2) -> (s2[1] - s1[1]));

        // 分配蛋糕, 统计得分
        int counter = 0;
        for (int[] s : shang) {

            if (counter < (size - k)) {
                score += reward2[s[0]];
            } else {
                score += reward1[s[0]];
            }
            counter++;
        }

        // 返回得分
        return score;
    }
}
