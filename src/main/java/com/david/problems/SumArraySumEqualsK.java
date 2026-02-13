package com.david.problems;

import java.util.HashMap;
import java.util.Map;

public class SumArraySumEqualsK {


    /**
     * Input:
     * nums = [1,1,1]
     * k = 2
     * <p>
     * Output:
     * 2
     * <p>
     * Explicación:
     * [1,1] (pos 0-1)
     * [1,1] (pos 1-2)
     *
     * @param args
     */
    static void main(String[] args) {
        int result = subarraySum(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7);
        System.out.println("Result: " + result);
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // caso base: suma parcial 0 aparece una vez

        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }


}
