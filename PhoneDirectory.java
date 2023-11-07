import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> set;
    public PhoneDirectory(int maxNumbers){
        q = new LinkedList<>();
        set = new HashSet<>();

        for(int i = 0; i< maxNumbers; i++){
            q.add(i);
            set.add(i);
        }
    }

    public int get(){
        if(q.isEmpty()){
            return -1;
        }

        int removedNumber = q.poll();
        set.remove(removedNumber);
        return removedNumber;
    }

    public boolean check(int number){
        if(set.contains(number)){
            return true;
        }

        return false;
    }

    public void release(int number){
        if(set.contains(number)){
            return;
        }
        set.add(number);
        set.add(number);
    }


}
