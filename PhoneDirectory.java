// Time Complexity : 0(1)
// Space Complexity : 0(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class PhoneDirectory {
    //set of used numbers
    Set<Integer> used = new HashSet<Integer>();
    //queue of available numbers
    Queue<Integer> available = new LinkedList<Integer>();
    int max;

    public PhoneDirectory(int maxNumbers) {
        max = maxNumbers;
        //add all the numbers in queue
        for (int i = 0; i < maxNumbers; i++) {
            available.offer(i);
        }
    }

    public int get() {
        //take 1st available number
        Integer num = available.poll();
        //if null return -1
        if (num == null) {
            return -1;
        }
        //add num in used set & return
        used.add(num);
        return num;
    }

    public boolean check(int number) {
        if (number >= max || number < 0) {
            return false;
        }
        //see if number is present in used set or not
        return !used.contains(number);
    }

    public void release(int number) {
        //remove number from used and add it to available
        if (used.remove(number)) {
            available.offer(number);
        }
    }
}
