package com.xiexinhai8.base_alg.bin_search;

import java.util.Arrays;

/**
 * 875. 爱吃香蕉的珂珂
 * 
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * @author xxh
 * Created on 2023-06-04
 */
public class MinEatingSpeed {

    public static void main(String[] args) {
        int[] piles = {312884470};
        int totalTime= 968709470;
        System.out.println(new MinEatingSpeed().minEatingSpeed(piles, totalTime));
    }

    public int minEatingSpeed(int[] piles, int totalTime) {
        /** 思路: 关键是找到一个合适的最低速度能够在h小时内吃掉所有香蕉
         设置吃香蕉的速度最快为h = piles[size], 最慢为l = 1;
         每次选中间速度mid = (h + l) / 2
         如果mid的速度无法吃完 l = mid + 1
         如果mid的速度能够吃完 h = mid

         当 l == h 返回 h
         */
        Arrays.sort(piles);
        int l = 1, h = piles[piles.length - 1];

        while (l < h) {
            int mid = (l + h) / 2;
            boolean canFinish = tryEat(piles, mid, totalTime);
            if (canFinish) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }

        return h;
    }

    private boolean tryEat(int[] piles, int speed, int totalTime) {
        int eatTime = 0;
        for (int i = 0; i < piles.length; i++) {
            eatTime += Math.ceil(piles[i] * 1.0 / speed);
            if (eatTime > totalTime) {
                return false;
            }
        }
        return true;
    }
}
