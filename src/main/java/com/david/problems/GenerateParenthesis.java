package com.david.problems;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    static void main() {
        List<String> result = generateParenthesis(3);
        System.out.println(result);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> res, String cur, int open, int close, int n) {
        if (cur.length() == n * 2) {
            res.add(cur);
            return;
        }

        if (open < n)
            backtrack(res, cur + "(", open + 1, close, n);

        if (close < open)
            backtrack(res, cur + ")", open, close + 1, n);
    }
}

