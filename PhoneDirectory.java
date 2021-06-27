import java.util.*;

public class PhoneDirectory {

    HashSet<Integer> set;
    
    Queue<Integer> queue;
    
    //O(N)
    public PhoneDirectory(int maxNumbers) { 
        set = new HashSet();
        queue = new LinkedList();
        for(int i=0;i<maxNumbers;i++) {
            set.add(i);
            queue.add(i);
        }
    }
    
    //O(1)
    public int get() { 
        if(queue.size() <= 0) return -1;
        int current = queue.poll();
        set.remove(current);
        return current;
    }
    
    //O(1)
    public boolean check(int number) {
        return set.contains(number);
    }
    
    //O(1)
    public void release(int number) {
        if(set.contains(number)) return;
        set.add(number);
        queue.add(number);
    }

    public static void main(String[] args) {
        // ["PhoneDirectory","release","get","check","check","release","check","get","check","check","check"]
        // [[2],[1],[],[1],[1],[1],[1],[],[0],[1],[1]]

        PhoneDirectory phoneDirectory = new PhoneDirectory(2);
        phoneDirectory.release(1);
        System.out.println("Number released:");
        System.out.println("The get: "+phoneDirectory.get());
        System.out.println("The check: "+phoneDirectory.check(1));
        System.out.println("The check: "+phoneDirectory.check(1));
        phoneDirectory.release(1);
        System.out.println("Number released:");
        System.out.println("The check: "+phoneDirectory.check(1));
        System.out.println("The get: "+phoneDirectory.get());
        System.out.println("The check: "+phoneDirectory.check(1));
        System.out.println("The check: "+phoneDirectory.check(1));
        System.out.println("The check: "+phoneDirectory.check(1));
    
    }
}