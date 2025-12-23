package leetcode;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stack;
    Stack<Integer> stackMin;

    public MinStack() {
        this.stack = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int val) {
        this.stack.push(val);
        if (this.stackMin.isEmpty() || this.stackMin.peek() >= val) {
            this.stackMin.push(val);
        }
    }

    public void pop() {
        Integer integer = this.stack.pop();
        // so sanh 2 doi tuong wrapper class
        if (integer.equals(stackMin.peek())) {
            this.stackMin.pop();
        }
    }

    public int top() {
        return this.stack.peek();
    }

    public int getMin() {
        return this.stackMin.peek();
    }

    public static void main(String[] args) {

    }
}