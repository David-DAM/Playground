package com.david.problems;

import java.util.Arrays;

public class ProductExceptSelf {

    static void main() {
        int[] nums = {1, 2, 3, 4};
        int[] result = productExceptSelf(nums);
        System.out.println(Arrays.toString(result));
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        res[0] = 1;
        for (int i = 1; i < n; i++)
            res[i] = res[i - 1] * nums[i - 1];

        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= suffix;
            suffix *= nums[i];
        }

        return res;
    }
}

