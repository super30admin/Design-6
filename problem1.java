import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class PhoneDirectory{
    Queue<Integer> q;
    HashSet<Integer> set;
    public PhoneDirectory(int maxNumbers){
        q = new LinkedList<>();
        set = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++){
            q.add(i);
        }
    }

    public int get(){
        if(q.isEmpty()) return -1;
        int polled = q.poll();
        set.add(polled);
        return polled;
    }

    public boolean check(int number){
        return !set.contains(number);
    }

    public void release(int number){
        if(!set.contains(number)) return;
        q.add(number);
        set.remove(number);
    }
}

//time complexity O(1)
//space complexity O(n) where n is space of queue and set use 