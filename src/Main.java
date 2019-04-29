import java.util.Arrays;

public class Main {

    private static int changeInt(int x){
        x = 10;
        return x;
    }
    public static void main(String[] args) {
        int[] array = {1,5};
        int[][] matrix = {{1,2,3},{2,4,5},{6,8,9}};
        BinarySearch bs = new BinarySearch();
        Sort sort = new Sort();
        long result = sort.fibonacci(10);
        System.out.println("result is "+result);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = null;
        LinkedList ll = new LinkedList();
        ll.middleNode(node1);
//        int [] result = bs.kClosest(array,2,10);
//        for (int i =0;i<result.length;i++){
//            System.out.println("result is "+result[i]);
//        }
        //int result = bs.shiftPosition(array);
        //int[] result = bs.sortedMatrix1(matrix,7);
//        int[] result = bs.sortedMatrix2(matrix,5);
//        System.out.println("result is "+result[0]+result[1]);



    }
}
