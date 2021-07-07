// building phone directory - O(n)
// get - O(1)
// check - O(1)
// release - O(1)

class PhoneDirectory {
    
    Queue<Integer> q;
    Set<Integer> st;

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        
        q = new LinkedList();
        st = new HashSet<Integer>();
        
        for(int i = 0; i < maxNumbers; ++i)
        {
            st.add(i);
            q.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        
        if(q.isEmpty()) return -1;
        
        int num = q.poll();
        st.remove(num);
        return num;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return st.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(!st.contains(number))
        {
            st.add(number);
            this.q.add(number);
        }
    }
}
