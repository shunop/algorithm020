package nop.Week01;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//https://leetcode-cn.com/problems/valid-parentheses/

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class L020E_valid_parentheses {
    public static boolean isValid(String s) {
        int len = s.length();
        if (s == null || len < 2 || (len & 1) == 1) return false;
        HashMap<Character, Character> dict = new HashMap<Character, Character>() {
            {
                put(')', '(');
                put('}', '{');
                put(']', '[');
            }
        };
        LinkedList<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (dict.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != dict.get(c)) return false;
//                else stack.pop();
//                else stack.poll();
                else stack.removeFirst();
            } else {
//                stack.push(c);
                stack.addFirst(c);
            }
        }
        return stack.isEmpty();
    }


    public static boolean isValid_2(String s) {
        if (s == null || s.length() < 2 || (s.length() & 1) != 0) return false;
        int len = s.length();
        //关键点 1.哈希映射 2.栈空间
        HashMap<Character, Character> hashMap = new HashMap<Character, Character>() {{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (hashMap.containsKey(ch)) {
                if (stack.empty() || hashMap.get(ch) != stack.peek()) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(isValid_2("[]"));
        System.out.println(isValid_2("([)]"));
    }

    public boolean isValid_3(String s) {
        if (s == null || s.length() == 0 ) return false;
        int len = s.length();
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c=='(') stack.push(')');
            else if (c=='[') stack.push(']');
            else if (c=='{') stack.push('}');
            else {
                if (stack.isEmpty() || c!=stack.peek()){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
