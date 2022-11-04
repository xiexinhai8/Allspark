package com.xiexinhai8.base_alg;

import java.util.Arrays;
import java.util.Random;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-09-20
 */
class Annealing {
    double hi = 1e9, low = 1e-4, fa = 0.92;
    int episode = 2000;
    Random random = new Random(92211);

    int[] nums;
    int k;

    int target;
    boolean done;
    int sum;
    int preLoss;
    public static  void main(String[] args) {
        Annealing annealing = new Annealing();
        int[] array = {709,374,1492,1279,2848,6337,365,1923,739,1904,1938,4627,1106,5885,1202};
        System.out.println(annealing.canPartitionKSubsets(array, 4));
    }

    public boolean canPartitionKSubsets(int[] _nums, int _k) {
        nums = _nums;
        k = _k;
        // k为0或1
        if (k == 0 || k == 1) {
            return true;
        }
        // 一共也没k份
        if (nums.length < k) {
            return false;
        }

        // 无法平分
        sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        target = sum / k;
        System.out.println("sum: " + sum +" target: " + target + " k: " + k);

        // 最大值比target还大
        Arrays.sort(nums);
        if (nums[nums.length - 1] > target) {
            return false;
        }

        // 退火
        int count = 0;
        while (!done && count++ < episode) {
            sa();
        }
        System.out.println("episode: " + count);
        return done;
    }

    private void sa() {
        shuffle();
        preLoss = sum;
        for (double temp = hi; temp > low && !done;) {
            int a = random.nextInt(k);
            int b = random.nextInt(k);
            if (a == b) {
                continue;
            }
            // int pre = calLoss();
            swap(a, b);
            int cur = calLoss();
            int deltaLoss = cur - preLoss;

            // System.out.println("delta_loss: " + loss);
            if (deltaLoss > 0 && Math.log(deltaLoss / temp) > random.nextDouble()) {
                swap(a, b);
            } else {
                preLoss = cur;
                temp *= fa;
            }

        }
    }

    private int calLoss() {
        int loss = sum;
        int[] subSet = new int[k];
        for (int i = nums.length - 1; i >= 0 ; i--) {

            for (int j = 0; j < k; j++) {
                if (subSet[j] + nums[i] <= target) {
                    subSet[j] += nums[i];
                    loss -= nums[i];
                    break;
                }
            }
        }
        if (loss == 0) {
            done = true;
        }
        System.out.println(loss);
        return loss;
    }

    private void shuffle() {
        for (int i = nums.length - 1; i > 0; i--) swap(random.nextInt(i), i - 1);
    }

    private void swap(int a, int b) {
        int c = nums[a];
        nums[a] = nums[b];
        nums[b] = c;
    }
}
