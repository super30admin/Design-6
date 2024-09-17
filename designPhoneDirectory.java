import java.util.*;
public class designPhoneDirectory {

	HashSet<Integer> availableNumbers;  // SC: O(N) for storing values onto Set
	// store all possible numbers onto Set
	public designPhoneDirectory(int maxNumbers) {  // TC: O(N) - to store all values in Set
		
		availableNumbers = new HashSet<>();
		for(int i=0;i<maxNumbers;i++) {
			availableNumbers.add(i); // add all the possible numbers on the Set because we know we cannnot have duplicate numbers in set
		}
		
	}
	
	public int get() {  // TC: O(1)-> retrieve the next elemet from Set
		
		if(availableNumbers.isEmpty())
			return -1;
		int val = availableNumbers.iterator().next();  // get the next number from the set, remove it from set because we can no longer use it
		// unless someone releases this number
		availableNumbers.remove(val);
		return val; // return the number retrieved from set
	}
	
	public boolean check(int number) { // O(1) - check if number is present
		
		return availableNumbers.contains(number);	 // check if the number is present in set.
	}
	
	public void release(int number) { //TC -> O(1) check if not present, adding value takes O(1)
		
		if(!availableNumbers.contains(number))  // if the number is not present, we add these numbers onto set.
			availableNumbers.add(number);
	}
	
	
}
