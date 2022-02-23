// Time Complexity : O(n)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class PhoneDirectory {
    int maxNumbers;
    int[] directory;
    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        this.directory = new int[maxNumbers];
    }
    
    public int get() {
        for(int i = 0; i < maxNumbers; i++) {
            if(this.directory[i] == 0) {
                this.directory[i] = i + 1;
                return i;
            }
        }
        return -1;
    }
    
    public boolean check(int number) {
        return this.directory[number] == 0 ? true : false;
    }
    
    public void release(int number) {
        this.directory[number] = 0;
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */