package nop.Week02;

public class L641M_design_circular_deque {
    public static void main(String[] args) {
        int front = 9;
        int capacity = 10;
        System.out.println((front - 1 + capacity) % capacity);
        System.out.println(12 % 8);
    }
}
/*
设计实现双端队列。
你的实现需要支持以下操作：

MyCircularDeque(k)：构造函数,双端队列的大小为k。
insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
getRear()：获得双端队列的最后一个元素。如果双端队列为空，返回 -1。
isEmpty()：检查双端队列是否为空。
isFull()：检查双端队列是否满了。
示例：

MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
circularDeque.insertLast(1);			        // 返回 true
circularDeque.insertLast(2);			        // 返回 true
circularDeque.insertFront(3);			        // 返回 true
circularDeque.insertFront(4);			        // 已经满了，返回 false
circularDeque.getRear();  				// 返回 2
circularDeque.isFull();				        // 返回 true
circularDeque.deleteLast();			        // 返回 true
circularDeque.insertFront(4);			        // 返回 true
circularDeque.getFront();				// 返回 4



提示：

所有值的范围为 [1, 1000]
操作次数的范围为 [1, 1000]
请不要使用内置的双端队列库。

链接：https://leetcode-cn.com/problems/design-circular-deque
 */

class MyCircularDeque {
    /*
    执行用时：7 ms, 在所有 Java 提交中击败了69.12%的用户
    内存消耗：39.1 MB, 在所有 Java 提交中击败了76.21%的用户
     */
    private int[] preQueue; //前置队列
    private int[] postQueue; //后置队列
    private int maxSize; //设置容量
    private int size = 0; //实际大小
    private int preIndex; //下一个头部可插入索引
    private int _preIndex; //前置队列起始位
    private int postIndex; //下一个尾部可插入索引
    private int _postIndex;//后置队列起始位


    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        if (k < 0) throw new RuntimeException("Size must be greater than 0");
        preQueue = new int[k - 1];
        postQueue = new int[k];
        maxSize = k;
        preIndex = k - 2;
        _preIndex = k - 2;
        postIndex = 0;
        _postIndex = 0;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (size >= maxSize) return false;
        size++;
        preQueue[preIndex--] = value;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (size >= maxSize) return false;
        size++;
        postQueue[postIndex++] = value;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (size == 0) return false;
        size--;
        if (preIndex != _preIndex) {
            ++preIndex;
//            preQueue[++preIndex] = 0;
            return true;
        } else {
            //preQueue is empty
//            postQueue[_postIndex] = 0;
            if (size != 0) {
                int[] tmp = new int[maxSize];
                System.arraycopy(postQueue, 1, tmp, 0, postIndex);
                postQueue = tmp;
            }
            postIndex--;
            return true;
        }
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (size == 0) return false;
        size--;
        if (postIndex != _postIndex) {
            --postIndex;
//            postQueue[--postIndex] = 0;
            return true;
        } else {
            //postQueue is empty
//            preQueue[_preIndex] = 0;
            if (size != 0) {
                int[] tmp = new int[maxSize - 1];
                System.arraycopy(preQueue, preIndex + 1, tmp, preIndex + 2, _preIndex - preIndex - 1);
                preQueue = tmp;
            }
            preIndex++;
            return true;
        }

    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (size == 0) return -1;
        if (preIndex != _preIndex) return preQueue[preIndex + 1];
        if (postIndex != _postIndex) return postQueue[_postIndex];
        throw new RuntimeException("index Err");
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (size == 0) return -1;
        if (postIndex != _postIndex) return postQueue[postIndex - 1];
        if (preIndex != _preIndex) return preQueue[_preIndex];
        throw new RuntimeException("index Err");
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return size == maxSize;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */

class MyCircularDeque2 {
    private int[] arr;
    int front, rear, capacity;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque2(int k) {
        capacity = k + 1;
        arr = new int[capacity];
        front = 0;
        rear = 0;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        front = (front - 1 + capacity) % capacity;
        arr[front] = value;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) return false;
        arr[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) return false;
        front = (front + 1) % capacity;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) return false;
        rear = (rear - 1 + capacity) % capacity;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (isEmpty()) return -1;
        return arr[front];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) return -1;
        return arr[(rear - 1 + capacity) % capacity];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */


class MyCircularDeque3 {
    private int[] arr;
    int front, rear, capacity ,size;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque3(int k) {
        capacity = k + 1;
        arr = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        size ++;
        front = (front - 1 + capacity) % capacity;
        arr[front] = value;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) return false;
        size ++;
        arr[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) return false;
        size --;
        front = (front + 1) % capacity;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) return false;
        size--;
        rear = (rear - 1 + capacity) % capacity;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (isEmpty()) return -1;
        return arr[front];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) return -1;
        return arr[(rear - 1 + capacity) % capacity];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
//        return (rear + 1) % capacity == front;
        return size + 1 == capacity;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */