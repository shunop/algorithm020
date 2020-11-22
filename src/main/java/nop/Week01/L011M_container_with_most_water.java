package nop.Week01;

public class L011M_container_with_most_water {
    //[1,8,6,2,5,4,8,3,7]
    public int maxArea(int[] height) {
        //a. double printer head and tail
        int head = 0, tail = height.length-1;
        int containerArea = Math.min(height[0], height[height.length - 1]) * (height.length-1);
        while (head < tail) {
            if (height[head] < height[tail]) {
                int lowerHead = height[head];
                while (head < tail && lowerHead >= height[head]) head++;
            }else {
                int lowerTail = height[tail];
                while (head < tail && lowerTail >= height[tail]) tail--;
            }
            int lowerHeight = Math.min(height[head], height[tail]);
            containerArea = Math.max(containerArea, lowerHeight * (tail - head) );
        }
        return containerArea;
    }
}
