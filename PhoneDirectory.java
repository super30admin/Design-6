// All the operation get(), check(), release() are TC=O(1)
// Approach we used a hashset to make 'get' and 'check' O(1) and used queue(can use any linear data structure) to make 'release' O(1)
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> set;
    public PhoneDirectory(int maxNumbers){
        q = new LinkedList<>();
        set = new HashSet<>();
        for(int i=0;i<=maxNumbers;i++){
            q.add(i);
            set.add(i);
        }
    }

    public int get(){
        int result = q.poll();
        set.remove(result);
        return result;
    }

    public boolean check(int number){
        return set.contains(number);
    }

    public void release(int number){
        if(!set.contains(number)){
            set.add(number);
            q.add(number);
        }
    }

    public static void main(String[] args){
        int maxNumbers = 10;
        int number = 2;
        PhoneDirectory pdir = new PhoneDirectory(maxNumbers);
        int param_1 = pdir.get();
        System.out.println("Got the number: " + param_1);
        boolean param_2 = pdir.check(number);
        System.out.println("Check outcome: " + param_2);
        int param_3 = pdir.get();
        System.out.println("Got another number: " + param_3);
        System.out.println("Set before release: "+ pdir.set);
        pdir.release(param_3);
        System.out.println("Set after release: "+ pdir.set);
        int param_4 = pdir.get();
        int param_5 = pdir.get();
        int param_6 = pdir.get();
        int param_7 = pdir.get();
        pdir.release(param_5);
        pdir.release(param_6);
        System.out.println(pdir.set);
    }
}
