import java.util.Comparator;
import java.util.PriorityQueue;

public class Heap {
    public int[] kSmallest(int[] array, int k) {
        // Write your solution here
        if (array.length == 0 || k == 0) {
            return new int[0];
        }

        int[] result = new int[k];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.equals(o2)) {
                    return 0;
                }

                return o1 < o2 ? 1 : -1;
            }
        });

        for (int num : array) {
            if (maxHeap.size() < k) {
                maxHeap.offer(num);
            } else {
                if (maxHeap.peek() > num) {
                    maxHeap.poll();
                    maxHeap.offer(num);
                }
            }

        }

        for (int i = 0; i < k; i++) {
            result[k - i - 1] = maxHeap.poll();
        }

        return result;
    }
}
