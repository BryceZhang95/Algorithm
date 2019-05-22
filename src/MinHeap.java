public class MinHeap {

    private int[] array;
    private int size;

    public MinHeap (int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input array can't be null!");
        }
        this.array = array;
        this.size = array.length;
        heapify();
    }

    private void heapify() {
        if (isEmpty()) {
            throw new IllegalArgumentException("The heap is empty!");
        }
        if (size < 2) {
            return;
        }
        int lastParent = (size - 2)/2;
        for (int i = lastParent; i >=0; i--) {

            percolateDown(i);
        }

    }

    public MinHeap (int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("Size can't be less than zero!");
        }
        array = new int[cap];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    private void percolateUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (array[index] < array[parent]) {
                swap(array,index,parent);
            } else {
                break;
            }
            index = parent;
        }
    }

    private void percolateDown(int index) {
        while (index <= (size - 1)/2) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = left;
            if (right <= size - 1 && array[right] < array[left]) {
                smallest = right;
            }
            if (array[index] > array[smallest]) {
                swap(array,index,smallest);
            } else {
                break;
            }
            index = smallest;
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("The heap is empty!");
        }
        return array[0];
    }

    public int poll() {
        if (isEmpty()) {
            throw new IllegalArgumentException("The heap is empty!");
        }
        int temp = array[0];
        array[0] = array[size - 1];
        size--;
        percolateDown(0);
        return temp;
    }

    public void offer(int element) {
        if (isFull()) {
            int[] newArray = new int[array.length*2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
         array[size]= element;
        size++;
        percolateUp(size-1);
    }

    public int update(int element , int index) {
        if (isEmpty() || index >= size) {
            throw new IllegalArgumentException("Error!");
        }
        int temp = array[index];
        array[index] = element;
        if (temp > element) {
            percolateUp(index);
        } else {
            percolateDown(index);
        }

        return temp;
    }

    public void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void print() {
        for (int i : array) {
            System.out.println(i);
        }

    }

}
