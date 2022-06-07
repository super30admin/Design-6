/*
Problem: https://leetcode.com/problems/design-phone-directory/
TC: O(1)
SC: O(maxNumbers)
*/
class PhoneDirectory {

    Queue<Integer> queue = null;
    HashSet<Integer> usedNumbers = null;
    
    public PhoneDirectory(int maxNumbers) {
        queue = new LinkedList<>();
        usedNumbers = new HashSet<>();
        
        for (int i = 0; i < maxNumbers; ++i) {
            queue.offer(i);
        }
    }
    
    public int get() {
        
        if (queue.isEmpty())
            return -1;
        int number = queue.poll();
        usedNumbers.add(number);
        return number;
    }
    
    public boolean check(int number) {
        if (!usedNumbers.contains(number)) {
            return true;
        }
        return false;
    }
    
    public void release(int number) {
        
        if (usedNumbers.contains(number)) {
            queue.offer(number);
            usedNumbers.remove(number);
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