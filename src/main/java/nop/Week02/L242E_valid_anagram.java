package nop.Week02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
示例1:
输入: s = "anagram", t = "nagaram"
输出: true
示例 2:
输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。
进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

链接：https://leetcode-cn.com/problems/valid-anagram
 */
public class L242E_valid_anagram {
    //1. 哈希
    // 34 ms
    public static boolean isAnagram1(String s, String t) {
        if (s == null || t == null) return false;
        int len1 = s.length(), len2 = t.length();
        if (len1 != len2) return false;
        Map<Character, Integer> hashMap = new HashMap<>();
        boolean into = false;
        //注意这里不适合把两个循环合并到一起, 因为在第二次循环里面可以提前结束方法
        for (int i = 0; i < len1; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                Integer countSI = hashMap.getOrDefault(s.charAt(i), 0);
                hashMap.put(s.charAt(i), ++countSI);
                Integer countTI = hashMap.getOrDefault(t.charAt(i), 0);
                hashMap.put(t.charAt(i), --countTI);
                into = true;
            }
        }
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() != 0) return false;
        }
        return true;// return into; // "a" 和 "a" 也叫有效的字母异位词吗??
    }

    //1.2 哈希
    //17 ms
    public static boolean isAnagram1_2(String s, String t) {
        if (s == null || t == null) return false;
        int len1 = s.length(), len2 = t.length();
        if (len1 != len2) return false;
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < len1; i++) {
            Integer countSI = hashMap.getOrDefault(s.charAt(i), 0);
            hashMap.put(s.charAt(i), ++countSI);
        }
        for (int i = 0; i < len2; i++) {
            Integer countTI = hashMap.getOrDefault(t.charAt(i), 0);
            hashMap.put(t.charAt(i), --countTI);
            if (countTI < 0) return false;
        }
        return true;
    }

    //1.3 哈希
    //6 ms
    public static boolean isAnagram1_3(String s, String t) {
        if (s == null || t == null) return false;
        int len1 = s.length(), len2 = t.length();
        if (len1 != len2) return false;
        int[] arr = new int[26];
        s = s.toLowerCase();
        t = t.toLowerCase();
        for (int i = 0; i < len1; i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < len2; i++) {
            arr[t.charAt(i) - 'a']--;
            if (arr[t.charAt(i) - 'a'] < 0) return false;
        }
        return true;
    }

    //2 排序
    // 3 ms
    public static boolean isAnagram2(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }


    public static void main(String[] args) {
        System.out.println(isAnagram1("anagram", "nagaram"));
    }
}
