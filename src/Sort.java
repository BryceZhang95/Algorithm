import java.util.Arrays;
public class Sort {

    public long fibonacci(int K) {
        // Write your solution here
        if(K <= 0){
            return 0;
        }
        else if(K == 1){
            return 1;
        }
        else{
            return fibonacci(K-1) + fibonacci(K-2);
        }
    }

    public long power(int a, int b) {
        // Write your solution here
        if (b == 0){
            return 1;
        }
        long halfResult = power(a , b/2);
        if (b%2 == 0){
            return halfResult * halfResult;
        }
        else{
            return halfResult * halfResult * a;
        }
    }

    public int[] selectionSort(int[] array) {
        // Write your solution here
        // time complexity: O(n^2)
        // space complextiy: O(1)
        for (int i = 0; i<(array.length - 1); i++) {
            int globalMin = i;
            for (int j = i+1; j<array.length; j++){
                if (array[j] < array[globalMin]){
                    globalMin = j;
                }

            }
            swap(array , i , globalMin);
        }

        return array;
    }

    public int[] mergeSort(int[] array) {
        // Write your solution here
//        time complexity: O(nlogn)
//        space complexity: O(n)
        int[] result = new int[array.length];
        sort(array, 0 , array.length -1,result);
        return array;

    }

    public void sort(int[]array, int left, int right , int[] result){

         if (left < right){
            int mid = left + (right - left)/2;
            sort(array,left,mid,result);
            sort(array,mid+1,right,result);
            merge(array,left , mid , right, result);
        }
    }

    public void merge(int[] array , int left , int mid, int right, int[] result){


        int i = left;
        int j = mid + 1;
        int k = 0;
        while(i <= mid && j <= right){
            if (array[i] <= array[j]){
                result[k++] = array[i++];
            }
            else {
                result[k++] = array[j++];
            }
        }

        while(i<=mid){//将左边剩余元素填充进temp中
            result[k++] = array[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            result[k++] = array[j++];
        }
        k = 0;
        while (left <= right){
            array[left++] = result[k++];
        }

    }

    public int[] quickSort(int[] array) {
        // Write your solution here
        int left = 0;
        int right = array.length - 1;
        quickSort(array, left ,right);
        return array;
    }

    public void quickSort(int[] array, int left , int right){
        if (left < right){
            int pivot = partition(array,left,right);
            quickSort(array, left , pivot-1);
            quickSort(array, pivot,right);
        }
    }

    public int partition(int[] array, int left , int right){
        int pivot = right;
        int i = left;
        int j = right - 1;
        while (i<=j){
            if (array[i] < array[pivot]){
                i++;
            }
            else {
                swap(array, i,j);
                j--;
            }

        }
        swap(array,i,right);
        return i;
    }

    public int[] rainbowSort(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }

        int i = 0;
        int j = 0;
        int k = array.length - 1;

        while (j < k) {
            if (array[j] == -1) {
                swap(array,i,j);
                i++;
                j++;
            }
            else if (array[j] == 0) {
                j++;
            }
            else {
                swap(array,j,k);
                k--;
            }
        }
        return array;
    }



    public void swap(int[] array , int i, int j){
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;

    }



}
