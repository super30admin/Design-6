// Space Complexity: O(n)
public class PhoneDirectory {
    Set<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers)
    {
        set = new HashSet<>();
        q = new LinkedList<>();
        for(int i = 0 ; i < maxNumbers ; i++)
        {
            set.add(i);
            q.offer(i);
        }
    }

    // O(1)
    public int get()
    {
        if(!q.isEmpty())
        {
            int val = q.poll();
            set.remove(val);
            return val;
        }
        return -1;
    }

    // O(1)
    public boolean check(int number)
    {
        return set.contains(number);
    }

    // O(1)
    public void release(int number)
    {
        if(set.contains(number)) // prevent invalid number
            return;
        q.offer(number);
        set.add(number);
    }
}

