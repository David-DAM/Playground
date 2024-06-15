package com.david.problems;

import java.util.*;

public class TwoSum {

    /**
     * Given nums = [ 2,7,11,15 ] target = 9
     * Because nums[0] + nums[1] = 9
     * Return [0,1]
     */
    public static void main(String[] args) {

        int [] nums = { 2,7,11,15 };

        int target = 9;

        int[] sum = twoSum(nums, target);

        Arrays.stream(sum).forEach(System.out::println);
    }
    public static int[] twoSum(int[] nums, int target) {

        List<Integer> index = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            for (int j = 0; j < nums.length; j++) {

                if (nums[i] + nums[j] == target ){
                    index.add(i);
                    index.add(j);
                }
            }
        }

        Set<Integer> result = new LinkedHashSet<>(index);

       return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
