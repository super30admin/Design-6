class PhoneDirectory {

    Queue<Integer> queue;
    HashSet<Integer> set;

    public PhoneDirectory(int maxNumbers) {

        set = new HashSet();
        queue = new LinkedList();

        // If we have maxNumbers, we consider elements from 0 till maxNumbers - 1
        for(int i = 0; i < maxNumbers; i++)
        {
            set.add(i);
            queue.add(i);
        }
    }

    public int get() {

        if(queue.isEmpty()) return -1;

        // Remove the element from the queue as well as fromm set
        int polled_element = queue.poll();
        set.remove(polled_element);

        return polled_element;
    }

    public boolean check(int number) {

        return set.contains(number);
    }

    public void release(int number) {

        // Release the number only if it is already used. If we don'tcheck the below condition and directly add
        // the element in to set and queue. It creates a problem. I set there would be no problem bcoz it doesn't
        // allow duplicates but in queue it will have duplicate entries

        if(!set.contains(number))
        {
            set.add(number);
            queue.add(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
