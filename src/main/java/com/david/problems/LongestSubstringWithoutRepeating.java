package com.david.problems;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeating {

    static void main() {
        String s = "abcabcbb";
        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, max = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left++));
            }
            set.add(s.charAt(right));
            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}
