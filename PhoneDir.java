public class PhoneDirectory
{
    int max;
    HashSet<Integer> set;
    LinkedList<Integer> queue;
    public PhoneDirectory(int maxNumbers)
    {
        set = new HashSet<Integer>();
        queue = new LinkedList<Integer>();
        for(int i=0; i<maxNumbers; i++)
        {
            queue.offer(i);
        }
        max=maxNumbers-1;
    }
    public int get()
    {
        if(queue.isEmpty())
        {
            return -1;
        }
        int newNum = queue.poll();
        set.add(newNum);
        return newNum;
    }

    public boolean check(int number)
    {
        return !set.contains(number) && number<=max;
    }
    public void remove(int num)
    {
        if(set.contains(num))
        {
            set.remove(num);
            queue.offer(num);
        }
    }

}
//SC:O(N)
//TC:O(N)
//Store in queue the numbers, then poll when we check for the number from queue and add into set to keep the track of the removed numbers.
