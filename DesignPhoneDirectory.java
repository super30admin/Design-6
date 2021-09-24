//https://leetcode.com/problems/next-permutation/
/*
Time: O(1) for get(), check() and release
Space: O(n) for HashSet and LinkedList
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/
public class DesignPhoneDirectory {
    LinkedList<Integer> availableList;
    Set<Integer> takenSet;

    public PhoneDirectory(int maxNumbers) {
        availableList = new LinkedList<Integer>();
        
        for(int i=0;i<maxNumbers;i++)
        {
            availableList.addLast(i); //initially all are available
        }
        takenSet = new HashSet<Integer>(); //nothing is taken
        
    }

    // Get from available list and put into takenSet
    public int get() {
        if (availableList.isEmpty())
            return -1;

        int result = availableList.removeFirst();
        takenSet.add(result);
        return result;

    }

    // check takenset
    public boolean check(int number) {
        return takenSet.contains(number) ? false : true;

    }

    // Release from takenSet
    // Add to availableList
    public void release(int number) {

        if (takenSet.contains(number)) {
            takenSet.remove(number);
            availableList.addLast(number);
        }

    }

}
