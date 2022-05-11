// Time Complexity : o(1) all opratios
// Space Complexity : o(maxNumbers)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach: use assigned and un assigned set before assiging the number by current count, check if unassigned is available
class PhoneDirectory {

    int maxNumbers;
    int count;

    Set<Integer> assigned;
    Set<Integer> unAssigned;

    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        count = 0;
        assigned = new HashSet<>();
        unAssigned = new HashSet<>();
    }

    public int get() {

        int number = -1;

        if(unAssigned.isEmpty() && count == maxNumbers) return number;

        if(unAssigned.isEmpty()){
            number = count;
            count++;
        }
        else{
            for(int x : unAssigned){
                number = x;
                break;
            }
            unAssigned.remove(number);
        }
        assigned.add(number);
        return number;
    }

    public boolean check(int number) {
        return !assigned.contains(number);
    }

    public void release(int number) {
        if(!check(number)){
           assigned.remove(number);
           unAssigned.add(number);
        }
    }
}
