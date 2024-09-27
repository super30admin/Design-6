
public class PhoneDirectory
{
    int max;
    HashSet<Integer> set;
    LinkedList<Integer> queue;

    //to initialise all the required data structures

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

    // To get an available number which is not assigned to anyone
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

    //Check if the number is available or assigned
    public boolean check(int number)
    {
        return !set.contains(number) && number<=max;
    }
    //remove a number
    public void remove(int num)
    {
        if(set.contains(num))
        {
            set.remove(num);
            queue.offer(num);
        }
    }

}

