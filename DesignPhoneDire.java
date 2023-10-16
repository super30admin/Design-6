//TC  get = O(n)
//check = O(1)
//release = O(1)
//SC = O(n)
class PhoneDirectory {

    private boolean[] isSlotAvailable;

    public PhoneDirectory(int maxNumbers) {
        isSlotAvailable = new boolean[maxNumbers];
        Arrays.fill(isSlotAvailable,true);
    }
    
    public int get() {
        for (int i = 0; i < isSlotAvailable.length; ++i) {
            if (isSlotAvailable[i]) {
                isSlotAvailable[i] = false;
                return i;
            }
        }
        
        // Otherwise, return -1 when all slots are occupied.
        return -1;
    }
    
    public boolean check(int number) {
        return isSlotAvailable[number];
    }
    
    public void release(int number) {
        isSlotAvailable[number] = true;
    }
}
