// Time Complexity : Get() -> O(1), Check() -> O(1), Release() -> O(1)
// Space Complexity : O(n) = total combined space for numSet and released queue
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

/*
1. Initialize numSet which contains set of allocated numbers and released queue which contains set of previously allocated numbers that have been released. Store maxNumbers in max variable and
    set current = 0 initially.
2. Get() -> If released is not empty, remove first element from released. Add it to numSet and return it to user. 
    If released is empty, if counter is equal to max return -1 (all numbers are allocated) else add counter to numSet, increment counter by 1 and return previous value of counter.
3. Check(int) -> If numSet contains number return false else return true. 
4. Release(int) -> If numSet contains number, remove it from numSet and add it to released queue for future allocation.
*/

public class PhoneDirectory
{
    int counter;
    int max;
    HashSet<int> numSet;
    Queue<int> released;

    public PhoneDirectory(int maxNumbers)
    {
        counter = 0;
        max = maxNumbers;
        numSet = new();
        released = new();
    }

    public int Get()
    {
        if (released.Count > 0)
        {
            int releasedNumber = released.Dequeue();
            numSet.Add(releasedNumber);
            return releasedNumber;
        }

        else
        {
            if (counter == max)
            {
                return -1;
            }

            int number = counter;
            numSet.Add(counter);
            counter += 1;
            return number;
        }
    }

    public bool Check(int number)
    {
        if (numSet.Contains(number))
        {
            return false;
        }

        return true;
    }

    public void Release(int number)
    {
        if (numSet.Contains(number))
        {
            numSet.Remove(number);
            released.Enqueue(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.Get();
 * bool param_2 = obj.Check(number);
 * obj.Release(number);
 */