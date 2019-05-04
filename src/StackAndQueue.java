import java.util.LinkedList;
import java.lang.Integer;
public class StackAndQueue {

    public void sort(LinkedList<Integer> s1) {
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        LinkedList<Integer> s3 = new LinkedList<Integer>();
        // Write your solution here.
        if (s1.size() == 0 || s1 == null) {
            return;
        }
        int globalMin = Integer.MAX_VALUE;
        int temp;
        int size = s1.size();
        while (s3.size()!=size){
            while (!s1.isEmpty()){
                temp = s1.pop();
                if (temp < globalMin) {
                    globalMin = temp;
                }
                s2.push(temp);

            }
            while (!s2.isEmpty()){
                temp = s2.pop();
                if (temp == globalMin) {
                    s3.push(temp);
                }
                else {
                    s1.push(temp);
                }
            }

        }





    }
}
