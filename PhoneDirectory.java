//sc : O(n)

class PhoneDirectory {

  // Hash set to store all available slots.
  private Set<Integer> slotsAvailable;

  public PhoneDirectory(int maxNumbers) {
    // Initially, all slots are available.
    slotsAvailable = new HashSet<>();
    for (int i = 0; i < maxNumbers; ++i) {
      slotsAvailable.add(i);
    }
  }

  //tc: O(1)
  public int get() {
    // If the hash set is empty it means no slot is available.
    if (slotsAvailable.isEmpty()) {
      return -1;
    }

    // Otherwise, remove and return the first element from the hash set.
    int slot = slotsAvailable.iterator().next();
    slotsAvailable.remove(slot);
    return slot;
  }

  //tc: O(1)
  public boolean check(int number) {
    // Check if the slot at index 'number' is available or not.
    return slotsAvailable.contains(number);
  }

  //tc: O(1)
  public void release(int number) {
    // Mark the slot 'number' as available.
    slotsAvailable.add(number);
  }
}
