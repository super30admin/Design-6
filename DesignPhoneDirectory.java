import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//TC: O(1)
//TC: O(n) ; n - maxNumbers

public class DesignPhoneDirectory {

    Queue<Integer> q;
    Set<Integer> set;

    public DesignPhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet<>();

        for(int i = 0; i < maxNumbers; i++){
            set.add(i);
            q.add(i);
        }
    }

    public int get() {

        if(q.isEmpty()) return -1;

        int result = q.poll();
        set.remove(result);
        return result;
    }

    public boolean check(int number) {
        return set.contains(number);
    }

    public void release(int number) {
        if(set.contains(number)) return;
        q.add(number);
        set.add(number);
    }
}
