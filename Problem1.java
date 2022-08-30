//Time Complexity : get- O(1), check - O(1), release - O(1)
//Space Complexity: O(m); where m is the maxNumbers
//Code run successfully on LeetCode.

public class Problem1 {

	class PhoneDirectory {

	    Queue<Integer> q;
	    HashSet<Integer> set;
	    
	    public PhoneDirectory(int maxNumbers) {
	        
	        q = new LinkedList<>();
	        set = new HashSet<>();
	        
	        for(int i =0; i < maxNumbers; i++)
	            q.add(i);
	    }
	    
	    public int get() {
	        
	        if(q.isEmpty())
	            return -1;
	        
	        else
	        {
	        int num = q.poll();
	        set.add(num);
	        return num; 
	            
	        }
	    }
	    
	    public boolean check(int number) {
	        
	        if(set.contains(number))
	            return false;
	        
	        return true;
	    }
	    
	    public void release(int number) {
	        
	        if(set.contains(number))
	        {
	            set.remove(number);
	            q.add(number);
	        }
	    }
	}
}
