/**
LeetCode Problem 379: https://leetcode.com/problems/design-phone-directory/

Time Complexity : O(N)
Space Complexity : O(N)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
*/
class PhoneDirectory
{

    // This queue is used to hold the currently available phone numbers
    Queue<Integer> queue;

    // This set is used to hold all the phone number which have already been taken
    Set<Integer> set;

    public PhoneDirectory(int maxNumbers) {

        this.queue = new LinkedList<>();
        this.set = new HashSet<>();

        // adding all possible number to the queue at initialization
        for(int i =0; i < maxNumbers; i++)
            this.queue.add(i);
    }

    /**
     * This method checks if there is any number available to be assigned in the queue
     * if queue is empty then -1 is returned
     * else the number is obtained from the queue and added to set which contains all the
     * number which have already been taken
     *
     * @return
     */
    public int get() {

        if(!this.queue.isEmpty())
        {
            int number = this.queue.poll();
            this.set.add(number);
            return number;
        }

        return -1;

    }

    /**
     * Checks is the number is present in set
     *
     * @param number
     * @return
     */
    public boolean check(int number) {

        return !this.set.contains(number);
    }

    /**
     * This method checks if the number is currently being used or not
     * if it is used then it is removed from the set of used numbers
     * and it is inserted into the queue of available number
     * @param number
     */
    public void release(int number) {

        if(this.set.contains(number))
        {
            this.set.remove(number);
            this.queue.add(number);
        }
    }
}