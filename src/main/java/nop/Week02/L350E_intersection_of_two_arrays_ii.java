package nop.Week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
给定两个数组，编写一个函数来计算它们的交集。
示例 1：
输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
示例 2:
输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]

说明：
输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
我们可以不考虑输出结果的顺序。
进阶：

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果nums1的大小比nums2小很多，哪种方法更优？
如果nums2的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 */
public class L350E_intersection_of_two_arrays_ii {
    //1. 排序
    /*
执行用时: 4 ms
内存消耗: 38.9 MB
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length, len2 = nums2.length;
        int i1 = 0, i2 = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (i1 < len1 && i2 < len2) {
            if (nums1[i1] == nums2[i2]) {
                list.add(nums1[i1]);
                i1++;
                i2++;
            } else if (nums1[i1] < nums2[i2]) {
                i1++;
            } else {
                i2++;
            }
        }
        int[] result = list.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }

    //1. 排序2
    /*
执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：38.7 MB, 在所有 Java 提交中击败了74.84%的用户
     */
    public int[] intersect1_2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length, len2 = nums2.length;
        int i1 = 0, i2 = 0, count = 0;
        int[] result = new int[Math.min(len1, len2)];
        while (i1 < len1 && i2 < len2) {
            if (nums1[i1] == nums2[i2]) {
                result[count++] = nums1[i1];
                i1++;
                i2++;
            } else if (nums1[i1] < nums2[i2]) {
                i1++;
            } else {
                i2++;
            }
        }
        return Arrays.copyOfRange(result, 0, count);
    }

    //2. 哈希
    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect2(nums2, nums1);
        }
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i : nums1) {
            Integer count = hashMap.getOrDefault(i, 0);
            hashMap.put(i, ++count);
        }
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        for (int i : nums2) {
            Integer count = hashMap.getOrDefault(i, 0);
            if (count > 0) {
                hashMap.put(i, --count);
                result[index++] = i;
            }
        }
        return Arrays.copyOfRange(result, 0, index);
    }
}
