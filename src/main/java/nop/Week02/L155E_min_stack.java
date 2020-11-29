package nop.Week02;

import java.util.Stack;

public class L155E_min_stack {

}

/*
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) —— 将元素 x 推入栈中。
pop()—— 删除栈顶的元素。
top()—— 获取栈顶元素。
getMin() —— 检索栈中的最小元素。

链接：https://leetcode-cn.com/problems/min-stack
 */
//L155E_min_stack
class MinStack {

    private Stack<Integer> dataStack;
    private Stack<Integer> mainStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        dataStack = new Stack<Integer>();
        mainStack = new Stack<Integer>();
        mainStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        dataStack.push(x);
        if (x <= mainStack.peek()) mainStack.push(x);
    }

    public void pop() {
        if (dataStack.empty()) return;
        Integer pop = dataStack.pop();
        if (pop.equals(mainStack.peek())) mainStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return mainStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */