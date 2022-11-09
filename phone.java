  // Time ologn add, ologn remove, ologn check
//Space O(N)
class PhoneDirectory {

    
  
    
    TreeSet<Integer> set; 
    public PhoneDirectory(int maxNumbers) {
        set=new TreeSet<Integer>();
        for(int i=0;i<maxNumbers;i++){
            set.add(i);
        }
    }
    
    public int get() {
        return set.size()==0?-1:set.pollFirst();
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        set.add(number);
    }
}