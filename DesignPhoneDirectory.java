//Time Complexity: O(1)
//Space Complexity: (n)

/*
 * store the numbers in a hashset and a queue. to get the number remove
 * it from queue and set. for check search in hashset and release
 * if it is not in hashset add it to queue and set.
 */

 class PhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> map;

  public PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
      this.map = new HashSet<>();
      for(int i = 0; i < maxNumbers; i++){
          q.add(i);
          map.add(i);
      }
  }

  public int get() {
        if(q.isEmpty()) return -1;
      int curr = q.poll();
      map.remove(curr);
      return curr;
  }

  /** Check if a number is available or not. */
  public boolean check(int number) {
        return map.contains(number);
  }

  /** Recycle or release a number. */
  public void release(int number) {
        if(!map.contains(number)){
            q.add(number);
            map.add(number);
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


