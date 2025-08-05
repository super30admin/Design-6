// TC : O(N) to populate set and queue
// SC : O(N)
public class PhoneDirectory {

    private HashSet<Integer> availableSet = new HashSet<Integer>();
    private Queue<Integer> availableQueue = new LinkedList<Integer>();
    public PhoneDirectory(int maxNumbers) {
        for(int i =0; i< maxNumbers; i++){
            availableSet.add(i);
            availableQueue.add(i);
        }
    }

    /**
     * @return: the available number of the phone directory
     */
    public int get() {
        if(availableSet.isEmpty()) return -1;
        int number = availableQueue.poll();
        availableSet.remove(number);
        return number;
    }

    /**
     * @param number: the number to be checked
     * @return: check whether the number of the phone directory is available
     */
    public boolean check(int number) {
        return availableSet.contains(number);
    }

    /**
     * @param number: the number of the phone directory to be released
     * @return: nothing
     */
    public void release(int number) {
        if(!availableSet.contains(number)){
            availableSet.add(number);
            availableQueue.add(number);
        }
    }
}