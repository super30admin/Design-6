
//TC - O(1) for each operation

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/design-phone-directory/

/**
 * use hashset to maintain the available numbers
 * use queue to maintain available numbers in the order they were added or released
 * 
 *
 */
public class PhoneDirectory {
  
	//set to maintain available numbers
	HashSet<Integer> set;
	
	//queue to maintain available numbers and returning them in the order when requested using get()
	Queue<Integer> q;
	
	int maxNumber;
	public PhoneDirectory(int maxNumber) {
		this.set = new HashSet<>();
		this.q = new LinkedList<Integer>();
		this.maxNumber = maxNumber;
		for(int i=0; i< maxNumber; i++) {
			set.add(i);
			q.add(i);
		}
	}
	
	//O(1) time
	//provide number which is not assigned to anyone, return -1 if none available
	public int get() {
		if(!q.isEmpty()) {
			int n = q.poll();
			set.remove(n);
			return n;
		}
		
		return -1;
	}
	
	//O(1) time
	//check if num is available or not
	public boolean check(int num) {
		return set.contains(num);
	}
	
	//O(1) time 
	//release previously assigned number
	public void release(int number) {
		if(!set.contains(number)) {
			set.add(number);
			q.add(number);
		}
	}
}
