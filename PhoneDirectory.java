//Space complexity is O(N)
class PhoneDirectory {
    boolean[] phones;
    int max;
    public PhoneDirectory(int maxNumbers) {
        phones = new boolean[maxNumbers];
        max=maxNumbers;
    }
//Time complexity is O(N)  
    public int get() {
        for(int i=0;i<max;i++){
            if(!phones[i])
            {
                phones[i]=true;
                return i;
            }
        }
        return -1;
    }
   //Time complexity is O(1) 
    public boolean check(int number) {
        return !phones[number];
    }
    //Time complexity is O(1)
    public void release(int number) {
        phones[number]=false;
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */