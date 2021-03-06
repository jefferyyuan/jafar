package com.andreytim.jafar.problems.leetcode;

/**
 * Created by tim on 26/03/15.
 */
public class StrStrKMP {

    private int[] failureFunction(char[] str) {
        int[] f = new int[str.length+1];
        for (int i = 2; i < f.length; i++) {
            int j = f[i-1];
            while (j > 0 && str[j] != str[i-1]) j = f[j];
            if (j > 0 || str[j] == str[i-1]) f[i] = j+1;
        }
        return f;
    }

    private int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (needle.length() <= haystack.length()) {
            int[] f = failureFunction(needle.toCharArray());
            int i = 0, j = 0;
            while (i < haystack.length()) {
                if (haystack.charAt(i) == needle.charAt(j)) {
                    i++; j++;
                    if (j == needle.length()) return i-j;
                } else if (j > 0) j = f[j];
                else i++;
            }
        }
        return -1;
    }

    private static void test(String haystack, String needle) {
        System.out.printf("Input: haystack=%s, needle=%s; Result: %d\n", haystack, needle,
                new StrStrKMP().strStr(haystack, needle));
    }

    public static void main(String[] args) {
        test("a", "a");
        test("abab", "bab");
        test("abab", "abab");
        test("bbaa", "aab");
        test("babba", "bbb");
        test("mississippi", "issip");
    }
}
