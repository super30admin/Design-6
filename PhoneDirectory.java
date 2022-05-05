import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//Time Complexity : O(n) for initializing, rest operations O(1)
//Space Complexity : O(n)
public class PhoneDirectory {	
	/**Approach: HashSet + Queue**/
	Set<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) { //O(n)
        this.set= new HashSet<>(maxNumbers);
        this.q= new LinkedList<>();
        for(int i=0; i<maxNumbers; i++){
            set.add(i);
            q.add(i);
        }
    }
    
    public int get() { //O(1)
        if(q.isEmpty()) return -1;
        int res= q.poll();
        set.remove(res);
        return res;
    }
    
    public boolean check(int number) { //O(1)
        return set.contains(number);
    }
    
    public void release(int number) { //O(1)
        if(!set.contains(number)) {
        	set.add(number);
        	q.add(number);
        }
    }
	
	// Driver code to test above
	public static void main (String[] args) {
		PhoneDirectory ob= new PhoneDirectory(3);
		System.out.println("Got the number "+ob.get());      // It can return any available phone number. Here we assume it returns 0.
		System.out.println("Got the number "+ob.get());      // Assume it returns 1.
		System.out.println("Is number 2 available? "+ob.check(2));   // The number 2 is available, so return true.
		System.out.println("Got the number "+ob.get());      // It returns 2, the only number that is left.
		System.out.println("Is number 2 available? "+ob.check(2));   // The number 2 is no longer available, so return false.
		ob.release(2); // Release number 2 back to the pool.
		System.out.println("Is number 2 available? "+ob.check(2));   // Number 2 is available again, return true.	
	}	
}
