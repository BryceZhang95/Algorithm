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

}
