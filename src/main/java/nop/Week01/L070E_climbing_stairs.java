package nop.Week01;

public class L070E_climbing_stairs {
    public int climbStairs(int n) {
        int a = 0, b = 1, tmp = 0;
        for (int i = 1; i <= n; i++) {
            tmp=a+b;
            a=b;
            b=tmp;
        }
        return tmp;
    }
}
