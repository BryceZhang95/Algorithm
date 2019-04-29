import java.util.Arrays;
public class BinarySearch {

    public int classicBinarySearch(int[] array , int target){
        if(array.length == 0) return -1;
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1)
        {
            int mid = left + (right - left)/2;
            if (array[mid] == target) return mid;
            else if (array[mid] < target) left = mid;
            else right = mid;
        }
        if (array[left] == target) return left;
        else if(array[right] == target) return right;
        else return -1;
    }

    public int[] kClosest(int[] array , int k , int target){
        if(array.length == 0 || array == null) return array;
        if(k == 0) return new int[0];
        int left = 0;
        int right = array.length - 1;
        int[] result = new int[k];
        while(left < right - 1){
            int mid = left + (right - left)/2;
            if(array[mid] > target) right = mid;
            else left = mid;
        }
        for(int i = 0; i<k; i++){

           if(right >= array.length || left>=0 && Math.abs(array[left] - target) < Math.abs(array[right] - target)){
               result[i] = array[left--];
           }
           else {
               result[i] = array[right++];
           }

        }
        return result;


    }
    public int shiftPosition(int[] array) {
        // Write your solution here
        if(array.length == 0) return -1;
        int left = 0;
        int right = array.length - 1;
        while(left< right - 1)
        {
            int mid = left + (right - left)/2;
            if(array[mid] > array[right]) left = mid;
            else right = mid ;
        }
        if(array[left] > array[right]) return right;
        else if(array[left] < array[right]) return left;
        else return -1;

    }

    public int[] sortedMatrix1(int[][] matrix , int target)
    {
        int[] a ={-1,-1};
        if(matrix.length == 0) return a;
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        int rowSize = matrix.length;
        int columnSize = matrix[0].length;
        int[] result = new int[2];
        while(left < right - 1)
        {
            int mid = left + (right - left)/2;
            if(matrix[mid/columnSize][mid%columnSize] > target) right = mid;
            else if(matrix[mid/columnSize][mid%columnSize] < target) left = mid;
            else{
                result[0] = mid/columnSize;
                result[1] = mid%columnSize;
                return  result;
            }
        }

        if(matrix[left/columnSize][left%columnSize] == target)
        {
            result[0] = left/columnSize;
            result[1] = left%columnSize;
            return result;
        }
        else if(matrix[right/columnSize][right%columnSize] == target)
        {
            result[0] = right/columnSize;
            result[1] = right%columnSize;
            return result;
        }
        else return a;


    }

    public int[] sortedMatrix2(int[][] matrix , int target)
    {
        int[] a ={-1,-1};
        if(matrix.length == 0) return a;
        int rowSize = matrix.length;
        int columnSize = matrix[0].length;
        int left;
        int right;
        for (int i=0; i<rowSize; i++)
        {
            left = i * columnSize;
            right = (i+1) * columnSize - 1;
            while (left <= right)
            {
                int mid = left + (right - left)/2;
                if(matrix[mid/columnSize][mid%columnSize] == target)
                {
                    int[] result = {mid/columnSize , mid%columnSize};
                    return  result;
                }
                else if(matrix[mid/columnSize][mid%columnSize] > target) right = mid - 1;
                else left = mid + 1;
            }
        }

        return a;

    }
}
