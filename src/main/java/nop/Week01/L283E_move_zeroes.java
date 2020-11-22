package nop.Week01;

public class L283E_move_zeroes {
    public void moveZeroes(int[] nums) {
        for (int i = 0, slower = 0; i <= nums.length - 1; i++) {
            if (nums[i] != 0) {
                int tmp = nums[slower];
                nums[slower] = nums[i];
                nums[i] = tmp;
                slower++;
            }
        }
    }
}
