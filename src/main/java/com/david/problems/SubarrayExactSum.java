package com.david.problems;

import java.util.Arrays;

public class SubarrayExactSum {

    static void main() {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 10;
        int[] result = findSubarrayWithTarget(target, nums);
        System.out.println(Arrays.toString(result));
    }

    public static int[] findSubarrayWithTarget(int target, int[] nums) {
        int left = 0, sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum > target) {
                sum -= nums[left++];
            }

            if (sum == target)
                return new int[]{left, right};
        }

        return new int[]{-1, -1};
    }
}

