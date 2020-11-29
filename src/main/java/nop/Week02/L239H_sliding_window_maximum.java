package nop.Week02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/*
给定一个数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
返回滑动窗口中的最大值。
进阶：

你能在线性时间复杂度内解决此题吗？
示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

提示：
1 <= nums.length <= 10^5
-10^4<= nums[i]<= 10^4
1 <= k<= nums.length

链接：https://leetcode-cn.com/problems/sliding-window-maximum
 */
public class L239H_sliding_window_maximum {
    //1 暴力求解1- 超时
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < k; j++) {
                max = Math.max(nums[i + j], max);
            }
            list.add(max);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    //2 暴力求解2- 超时
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int resultLen = nums.length - k + 1;
        int[] result = new int[resultLen];
        for (int i = 0; i < resultLen; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < k; j++) {
                max = Math.max(nums[i + j], max);
            }
            result[i] = max;
        }
        return result;
    }

    //3 双端队列3- 通过=35ms
    public static int[] maxSlidingWindow3(int[] nums, int k) {
        int len = nums.length;
        if (len * k == 0) return new int[0];
        if (k == 1) return nums;
        //双端队列(存索引) : 头部最大尾部存小于他的, 元素存入时 把尾部小于他的元素都移除掉
        Deque<Integer> deque = new ArrayDeque<Integer>();
        deque.add(0);
        int[] result = new int[len - k + 1];
        //初始化第一个滑动窗口
        for (int i = 1; i < k; i++) {
            //判断头部索引是不是过期了, 过期则移除
            if (!deque.isEmpty() && i - deque.getFirst() >= k) deque.removeFirst();
            //元素存入时 把尾部小于他的元素都移除掉
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) deque.removeLast();
            deque.addLast(i);
        }
        result[0] = nums[deque.getFirst()];
        //开始滑动
        for (int i = k; i < len; i++) {
            //判断头部索引是不是过期了, 过期则移除
            if (!deque.isEmpty() && i - deque.getFirst() >= k) deque.removeFirst();
            //元素存入时 把尾部小于他的元素都移除掉
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) deque.removeLast();
            deque.addLast(i);
            result[i - k + 1] = nums[deque.getFirst()];
        }
        return result;
    }


    public static void main(String[] args) {
//        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] ints = maxSlidingWindow3(nums, 3);
//        System.out.println(Arrays.toString(ints));
        int[] nums2 = {7,2,4};
        int[] ints2 = maxSlidingWindow3(nums2, 2);
        System.out.println(Arrays.toString(ints2));
    }
}
