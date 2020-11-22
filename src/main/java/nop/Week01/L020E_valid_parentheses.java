package nop.Week01;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//https://leetcode-cn.com/problems/valid-parentheses/

public class L020E_valid_parentheses {
    public boolean isValid(String s) {
        int len = s.length();
        if (s == null || len < 2 || (len & 1) == 1) return false;
//        int aleft = 0, aright = 0, bleft = 0, bright = 0, cleft = 0, cright = 0;
//        int a = 0, b = 0, c = 0;
        for (int i = 0, j = len - 1; i < j; i++, j--) {
            int sum = s.charAt(i) + s.charAt(j);
            if (sum == 81 && s.charAt(i) == '(') break;
            if (sum == 248 && s.charAt(i) == '{') break;
            if (sum == 184 && s.charAt(i) == '}') break;
            return false;
        }
        return true;
    }

}
