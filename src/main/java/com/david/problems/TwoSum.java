package com.david.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    static void main() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];
            if (map.containsKey(comp))
                return new int[]{map.get(comp), i};

            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }
}

