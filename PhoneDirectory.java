import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//Time Complexity=O(1)
//Space Complexity=O(n)
public class PhoneDirectory {

    HashSet<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        set=new HashSet<>();
        q=new LinkedList<>();
        for(int i=0;i<maxNumbers;i++){
            q.add(i);set.add(i);
        }
    }

    public int get() {
        if(q.isEmpty()) return -1;
        int re=q.poll();
        set.remove(re);
        return re;
    }

    public boolean check(int number) {
        return set.contains(number);
    }

    public void release(int number) {
        if(!set.contains(number)){
            q.add(number);
            set.add(number);
        }
    }
}
