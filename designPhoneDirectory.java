// Time complexity = O(1)
// Space complexity = O(n)

class PhoneDirectory {
  Queue<Integer> queue;
  Set<Integer> set;
  public PhoneDirectory(int maxNumbers) {
    queue = new LinkedList<>();
    set = new HashSet<>();
    for(int i = 0; i<maxNumbers; i++) {
      queue.add(i);
      set.add(i);
    }
  }
  public int get() {
    if(queue.isEmpty()) return -1;
    int number = queue.poll();
    set.remove(number);
    return number;
  }
  public boolean check(int number) {
    if(!queue.isEmpty()) return set.contains(number);
    return false;
  }
  public void release(int number) {
    if(set.contains(number)) return;
    queue.add(number);
    set.add(number);
  }
}