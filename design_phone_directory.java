class PhoneDirectory {
    HashSet<Integer> set; //we take a hashset named as set
    Queue<Integer> q; //we take a queue named as q
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>(); //set is initialized
        q = new LinkedList<>(); //q is initialized
        for(int i = 0; i < maxNumbers; i++) { //we go through all the elements
            set.add(i); //we add them to set
            q.add(i); //we add them to queue
        }
    }

    public int get() {
        if(q.isEmpty()) return -1; //if q is empty we return -1;
        int number = q.poll(); //if not empty, we remove the element from the queue
        set.remove(number); //we remove the element from the set
        return number; //we return that number
    }

    public boolean check(int number) {
        return set.contains(number); //we return true if the element is present the set. we return false if the element is not present
    }

    public void release(int number) {
        if(set.contains(number)) return; //if the set contains the number, we return
        q.add(number); //if the set dosent contains the number, we add it to the queue
        set.add(number); //we add it to the set
    }
}
//tc and sc - O(1)