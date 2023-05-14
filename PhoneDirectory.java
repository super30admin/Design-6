/*
Time Complexity - O(1)
Space Complexity - O(n)
*/
class PhoneDirectory {
    Set<Integer> used = new HashSet<Integer>();
    Queue<Integer> available = new LinkedList<Integer>();
    int max;
    
    public PhoneDirectory(int maxNumbers) {
        max = maxNumbers;
        for (int i = 0; i < maxNumbers; i++)
            available.offer(i);
    }
    
    public int get() {
        Integer ret = available.poll();
        if (ret == null)
            return -1;
        used.add(ret);
        return ret;
    }
    
    public boolean check(int number) {
        if (number >= max || number < 0)
            return false;
        return !used.contains(number);
    }
    
    public void release(int number) {
        if (used.remove(number))
            available.offer(number);
    }
}

/* avoid all numbrrs to data structure before hand. 
public class PhoneDirectory {
private int max;
private Set<Integer> used;
private LinkedList<Integer> free;

public PhoneDirectory(int maxNumbers) {
    max = maxNumbers;
    used = new HashSet<>();
    free = new LinkedList<>();
}

public int get() {
    if(used.size() == max) return -1;
    Integer ret = free.isEmpty() ? used.size() : free.remove();
    used.add(ret);
    return ret;
}

public boolean check(int number) {
    return !used.contains(number);
}

public void release(int number) {
    if(used.remove(number))
        free.add(number);
}
}
*/
