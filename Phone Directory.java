// Time Complexity : O(1)
// Space Complexity : O(maxNumbers)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach
// We will maintain a hashset and a queue to store the available numbers. We will put it in hashset to make the search optimal and we put it in the queue to get the available numbers by popping them. When the queue is empty, it means no element is available. Whenever we want a number, we just pop the number from the queue. To release a number i.e to make a number available in the queue, we add it to the set and the queue.

class PhoneDirectory 
{
    HashSet <Integer> set;
    Queue <Integer> q;

    public PhoneDirectory(int maxNumbers) 
    {
        this.set=new HashSet<>();
        this.q=new LinkedList<>();

        for(int i=0;i<maxNumbers;i++)
        {
            set.add(i); //add the number to the set
            q.add(i);   //add the number to the queue
        }
    }
    
    public int get() 
    {
        if(q.isEmpty()) return -1;  //if no number is available return -1
        int re=q.poll();    //else return the popped number from the queue
        set.remove(re);     //remove the same number from the set as well
        return re;
    }
    
    public boolean check(int number) 
    {
        return set.contains(number);    //if the number is present in the set, return true else false
    }
    
    public void release(int number) 
    {
        //if the number is not present in the set, then add it 
        if (!set.contains(number))
        {
            set.add(number);
            q.add(number);
        }
    }
}
