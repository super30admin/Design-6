/*Problem:
Design a Phone Directory which supports the following operations:
get(): Provide a number that is not assigned to anyone.
check(number): Check if a number is available or not.
release(number): Release a number that is no longer used and can be reused.
The phone directory should contain a pool of unique numbers starting from 0 to a maximum of maxNumbers-1. 
Once a number is requested for the first time, it should be marked as "assigned" and should not be available for subsequent requests. 
When a number is released, it becomes available for the next request.

Example1:
Input:
maxNumbers = 3
PhoneDirectory directory = new PhoneDirectory(maxNumbers);
directory.get(); // returns 0
directory.get(); // returns 1
directory.check(2); // returns true
directory.get(); // returns 2
directory.check(2); // returns false
directory.release(2);
directory.check(2); // returns true

Output:
0
1
true
2
false
true
*/

//-----------------------------------------------------------------------------------------------------------------------

//Time = O(n)
//Space = O(n)

class PhoneDirectory {
    int maxNumbers;
    Queue<Integer> availableNumbers;
    Set<Integer> assignedNumbers;
    
    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        this.availableNumbers = new LinkedList<>();
        this.assignedNumbers = new HashSet<>();
        
        // Add all numbers to available numbers queue initially
        for (int i = 0; i < maxNumbers; i++) {
            availableNumbers.add(i);
        }
    }
    
    public int get() {
        if (availableNumbers.isEmpty()) {
            return -1; // No available numbers
        }
        
        int number = availableNumbers.poll();
        assignedNumbers.add(number); // Mark number as assigned
        return number;
    }
    
    public boolean check(int number) {
        if (number < 0 || number >= maxNumbers) {
            return false; // Invalid number
        }
        
        return !assignedNumbers.contains(number);
    }
    
    public void release(int number) {
        if (assignedNumbers.remove(number)) {
            availableNumbers.add(number); // Add released number to available numbers queue
        }
    }
}
