package nop.Week01;

import java.util.HashMap;

public class L001E_two_sum {
    //两层循环
    public int[] twoSum_1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }
        return null;
    }

    //哈希
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int tmp = target - nums[i];
            if (hashMap.containsKey(tmp)) {
                return new int[]{hashMap.get(tmp), i};
            }
            hashMap.put(nums[i], i);
        }
        return null;
    }
}
