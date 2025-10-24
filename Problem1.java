/*
379. Design Phone Directory
Ran on leetcode: Yes
TC: O(1) for all the operations
SC: O(n)
Create a queue for all the unused numbers and set for used numbers, get - get the number from queue and add to set, check - check if number is used in set, release - check if it is set and remove from it and add it to the queue
*/
class PhoneDirectory {

    private Queue<Integer> unused;
    private Set<Integer> used;

    public PhoneDirectory(int maxNumbers) {
        this.unused = new LinkedList<>();
        this.used = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++){ // add all the numbers to the queue
            unused.offer(i);
        }
    }
    
    public int get() { // get the number from queue and add to set
        if(unused.size() == 0) return -1; // no numbers remaining
        int num = unused.poll();
        used.add(num);
        return num;
    }
    
    public boolean check(int number) { // check if number is used in set
        return !used.contains(number);
    }
    
    public void release(int number) { // check if it is set and remove from it and add it to the queue
        if(used.contains(number)){
            used.remove(number);
            unused.offer(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */