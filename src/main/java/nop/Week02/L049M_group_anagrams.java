package nop.Week02;

import java.util.*;

/*
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
示例:
输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
说明：
所有输入均为小写字母。
不考虑答案输出的顺序。

链接：https://leetcode-cn.com/problems/group-anagrams
 */
public class L049M_group_anagrams {
    //1. 哈希
    //7ms
    public List<List<String>> groupAnagrams1(String[] strs) {
        if (strs == null || strs.length == 0) return Collections.EMPTY_LIST;
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            } else {
                list.add(str);
            }
        }
        return new ArrayList<>(map.values());
    }
    //2. 质数相乘
    public List<List<String>> groupAnagrams2(String[] strs) {
        int[] prime = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
        if (strs == null || strs.length == 0) return Collections.EMPTY_LIST;
        Map<Long, List<String>> map = new HashMap<>();
        for (String str : strs) {
            Long key = 1L;
            str = str.toLowerCase();
            for (int i = 0,len = str.length(); i < len; i++) {
                key *= prime[str.charAt(i) - 'a'];
            }
            List<String> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            } else {
                list.add(str);
            }
        }
        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
        int[] prime = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
        long a= 2l;
        for (int i = 0; i < 39; i++) {
            a *=a;
        }
        System.out.println(a);
    }
}
