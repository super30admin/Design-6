class PhoneDirectory {
HashMap<Integer, Boolean>hm;
    int size;
    public PhoneDirectory(int maxNumbers) {
        this.hm = new HashMap<>();
        this.size=maxNumbers;
        for(int i=0;i<size;i++){
            hm.put(i,true);
        }
        
    }
    
    public int get() {
        for(int x:hm.keySet()){
            if(this.hm.get(x)){
                hm.put(x,false);
                return x;}
            
        }
        return -1;
    }
    
    public boolean check(int number) {
        if(this.hm.get(number)){
            return true;
        }
        return false;
    }
    
    public void release(int number) {
        this.hm.put(number,true);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
