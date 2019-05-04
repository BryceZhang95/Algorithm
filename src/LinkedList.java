class ListNode{
    public int value;
    public ListNode next;
    public ListNode(int value){
        this.value = value;
        next = null;
    }
}
public class LinkedList {

    public ListNode reverseIterative(ListNode head){
        ListNode prev = null;
        while (head!=null){
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

    public ListNode reverseRecursion(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode swapPairs(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;
        return temp;
    }

    public ListNode middleNode(ListNode head){
        // corner case
        if (head == null || head.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        // if the middle node of N1 -> N2 -> N3 -> N4 is N2
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
//       // if the middle node of N1 -> N2 -> N3 -> N4 is N3
//        while (fast!=null && fast.next!=null){
//            slow = slow.next;
//            fast = fast.next.next;
//        }
        return slow;
    }

    public boolean hasCycle(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }

        }
        return false;
    }

    public ListNode insert(ListNode head, int value){
        if (head == null){
            return new ListNode(value);
        }
        if (value <= head.value){
            ListNode newNode = new ListNode(value);
            newNode.next = head;
            return newNode;
        }
        ListNode left = head;
        ListNode right = head.next;
        while (right!=null){
            if (value >= left.value && value <=right.value){
                ListNode newNode = new ListNode(value);
                left.next = newNode;
                newNode.next = right;
                //return head;
            }
            else {
                left = left.next;
                right = right.next;
            }
        }
        if (value >= left.value){
            left.next = new ListNode(value);
            //return head;
        }
        return head;
    }

    public ListNode insert2(ListNode head , int value){
        if (head == null){
            return new ListNode(value);
        }
        if (value <= head.value){
            ListNode newNode = new ListNode(value);
            newNode.next = head;
            return newNode;
        }
        ListNode pointer = head;
        while (pointer.next!=null && pointer.next.value<value){
            pointer = pointer.next;
        }
        ListNode newNode = new ListNode(value);
        newNode.next = pointer.next;
        pointer.next = newNode;
        return head;
    }

    public ListNode merge(ListNode one, ListNode two){
        ListNode dummyNode = new ListNode(0);
        ListNode cur = dummyNode;
        while (one!=null && two!=null){
            if (one.value < two.value){
                cur.next = one;
                one = one.next;
            }
            else {
                cur.next = two;
                two = two.next;
            }
            cur = cur.next;
        }
        if (one == null) {
            cur.next = two;
        }
        else {
            cur.next = one;
        }
        return dummyNode.next;

    }

    public int count(ListNode head){
        if (head == null){
            return 0;
        }
        int count = 1;
        ListNode cur = head;
        while (cur.next!=null){
            count++;
            cur = cur.next;
        }
        return count;
    }

    public ListNode indexOf(int index,ListNode head){
        int count = 0;
        ListNode cur = head;
        while (count!=index){
            cur = cur.next;
            count++;
        }

        return cur;
    }

    public ListNode remove(ListNode head, int value){
        if (head == null){
            return head;
        }
        if (head.value == value){
            return head.next;
        }
        ListNode cur = head;
        while (cur.next!=null && cur.next.value!=value){
            cur = cur.next;
        }
        if (cur.next!=null){
            cur.next = cur.next.next;
        }

        return head;

    }

    public ListNode insert(ListNode head, int index, int value) {
        // Write your solution here
        // insert at certain index
        if (index < 0){
            return head;
        }
        if (index == 0){
            ListNode newHead = new ListNode(value);
            newHead.next = head;
            return newHead;
        }
        ListNode cur = head;
        while (index - 1> 0 && cur.next!=null){
            cur = cur.next;
            index-- ;
        }

        if (index == 1){
            ListNode newHead = new ListNode(value);
            ListNode temp = cur.next;
            cur.next = newHead;
            newHead.next = temp;
        }


        return head;


    }

    public ListNode deleteNode(ListNode head, int index) {
        // Write your solution here
        if (index == 0) {
            return head.next;
        }
        ListNode cur = head;
        while (index - 1 > 0 && cur.next!=null){
            cur = cur.next;
            index--;
        }
        if (index == 1 && cur.next!=null){
            cur.next = cur.next.next;
        }

        return head;
    }

    public ListNode deleteNodes(ListNode head, int[] indices) {
        // Write your solution here
        if (head == null|| indices.length == 0 || indices == null) {
            return null;
        }
        ListNode newHead = new ListNode(0);
        for (int i = 0; i < indices.length; i++){
            if (i == 0){
                newHead = deleteNode(head,indices[i]);
            }
            else {
                newHead = deleteNode(newHead,indices[i] - i);
            }
        }
        return newHead;


    }

    public ListNode removeElements(ListNode head, int val) {
        // Write your solution here
        if (head == null) {
            return head;
        }

        while(head.value == val ){
            if (head.next!=null){
                head = head.next;
            }else {
                return head.next;
            }

        }
        ListNode cur = head;

        while(cur!=null&&cur.next!=null){
            if(cur.next.value == val) {
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return head;

    }

    public ListNode rotateKplace(ListNode head, int n) {
        // Write your solution here
        if (head == null){
            return head;
        }
        ListNode cur = head;
        ListNode pre = head;
        int count = 1;
        while (cur.next!=null) {
            cur = cur.next;
            count++;
        }
        int loop = n - n%count;
        pre = cur;
        cur.next = head;
        cur = head;
        while (loop > 0) {
            cur = cur.next;
            pre = pre.next;
            loop--;
        }
        pre.next = null;
        return cur;

    }

    public ListNode partition(ListNode head, int target) {
        // Write your solution here
        if (head == null) {
            return null;
        }
        ListNode smallHead = new ListNode(0);
        ListNode largeHead = new ListNode(0);
        ListNode smallTail = smallHead;
        ListNode largeTail = largeHead;
        ListNode cur = head;
        while (cur!=null) {
            if (cur.value < target) {
                smallTail.next = cur;
                cur = cur.next;
                smallTail = smallTail.next;
            }
            else {
                largeTail.next = cur;
                cur = cur.next;
                largeTail = largeTail.next;
            }
        }
        smallTail.next = largeHead.next;
        return smallHead.next;

    }

    public ListNode reorder(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = middleNode(head);
        ListNode headOne = head;
        ListNode headTwo = reverseRecursion(middle.next);
        middle.next = null;
        return merge(headOne, headTwo);


    }


//    public ListNode partition(ListNode head, int x){
//        if (head == null) {
//            return null;
//        }
//        ListNode dummySmall = new ListNode(0);
//        ListNode dummyLarge = new ListNode(0);
//        ListNode tailSmall = dummySmall;
//        ListNode tailLarge = dummyLarge;
//        ListNode cur = head;
//        while (cur!=null){
//            if (cur.value<=x){
//                tailSmall.next = cur;
//
//            }
//        }
//    }
}
