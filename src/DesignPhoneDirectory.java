// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : No
// Any problem you faced while coding this : No
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class DesignPhoneDirectory {
	HashSet<Integer> set;
	Queue<Integer> q;
	public DesignPhoneDirectory(int maxNumbers){
		set = new HashSet<>();
		q = new LinkedList<>();
		for(int i = 0; i < maxNumbers; i++){
			q.add(i);
		}
	}
	
	public int get(){
		if(!q.isEmpty()){
			int result = q.poll();
			set.add(result);
			return result;
		}
		return -1;
	}
	
	public boolean check(int number){
		return !set.contains(number);
	}
	
	public void release(int number){
		if(set.contains(number)){
			set.remove(number);
			q.add(number);
		}
	}
	
}
