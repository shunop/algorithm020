package nop.Week01;

import java.util.*;

public class L015M_3sum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return Collections.emptyList();
        Arrays.sort(nums);
        LinkedHashSet<List<Integer>> hashSet = new LinkedHashSet<List<Integer>>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        //判断结果集合里没有当前组合
                        Integer[] arr = {nums[i], nums[j], nums[k]};
                        List<Integer> list = Arrays.asList(arr);
                        hashSet.add(list);
                    }
                }
            }
        }
        return new ArrayList<List<Integer>>(hashSet);
    }
}
