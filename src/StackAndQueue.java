import java.util.Deque;
import java.util.LinkedList;
import java.lang.Integer;
public class StackAndQueue {

    private Deque<Integer> stack;
    private Deque<Integer> minStack;
    // merge sort with three stacks
    public void sort(LinkedList<Integer> s1) {
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        LinkedList<Integer> s3 = new LinkedList<Integer>();
        // Write your solution here.
        sort(s1 , s2, s3, s1.size());


    }

    private void sort(LinkedList<Integer> s1 , LinkedList<Integer>s2 , LinkedList<Integer> s3 , int length){
        if (length <= 1) {
            return;
        }
        int mid1 = length/2;
        int mid2 = length - length/2;
        for (int i = 0; i < mid1; i++) {
            s2.offerFirst(s1.pollFirst());
        }
        // merge sort recursion
        // sort s2 and s1
        sort(s2, s3, s1, mid1);
        sort(s1 , s3, s2, mid2);

        int i = 0;
        int j = 0;
        while (i < mid1 && j < mid2) {
            if (s2.peekFirst() < s1.peekFirst()) {
                s3.offerFirst(s2.pollFirst());
                i++;
            } else {
                s3.offerFirst(s1.pollFirst());
                j++;
            }

        }

        while (i < mid1) {
            s3.offerFirst(s2.pollFirst());
            i++;
        }

        while (j < mid2) {
            s3.offerFirst(s1.pollFirst());
            j++;
        }

        for (int index = 0; index < length; index++) {
            s1.offerFirst(s3.pollFirst());
        }


    }

    public StackAndQueue() {
        stack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
    }

    public int pop() {

        if (stack.isEmpty()) {
            return 0;
        }

        Integer result = stack.pollFirst();
        if (result.equals(minStack.peekFirst())) {
            minStack.pollFirst();
        }
        return result;


    }

    public void push(int element) {

        stack.offerFirst(element);
        if (stack.isEmpty() || element < minStack.peekFirst()) {
            minStack.offerFirst(element);
        }
    }

    public int top() {

        return stack.isEmpty() ? 0 : stack.peekFirst();
    }

    public int min() {

        return minStack.isEmpty() ? 0 : minStack.peekFirst();
    }


}
