'''
379 Design Phone Directory

Design a phone directory that initially has maxNumbers empty slots that can store numbers. The directory should store numbers, check if a certain slot is empty or not, and empty a given slot.

Implement the PhoneDirectory class:
    PhoneDirectory(int maxNumbers) Initializes the phone directory with the number of available slots maxNumbers.
    int get() Provides a number that is not assigned to anyone. Returns -1 if no number is available.
    bool check(int number) Returns true if the slot number is available and false otherwise.
    void release(int number) Recycles or releases the slot number.


Example 1:
Input
["PhoneDirectory", "get", "get", "check", "get", "check", "release", "check"]
[[3], [], [], [2], [], [2], [2], [2]]
Output
[null, 0, 1, true, 2, false, null, true]

Explanation
PhoneDirectory phoneDirectory = new PhoneDirectory(3);
phoneDirectory.get();      // It can return any available phone number. Here we assume it returns 0.
phoneDirectory.get();      // Assume it returns 1.
phoneDirectory.check(2);   // The number 2 is available, so return true.
phoneDirectory.get();      // It returns 2, the only number that is left.
phoneDirectory.check(2);   // The number 2 is no longer available, so return false.
phoneDirectory.release(2); // Release number 2 back to the pool.
phoneDirectory.check(2);   // Number 2 is available again, return true.

Constraints:
1 <= maxNumbers <= 10^4
0 <= number < maxNumbers
At most 2 * 10^4 calls will be made to get, check, and release.

Solution:
1. Hash set and Queue
Hash set to store numbers that are currently in use
Queue to store numbers that are currently not in use (hence available)

When get(num) is called, a user requests a number from the pool of available nos. This is achieved by pulling a number from the top (left end) of the queue. The number is then added to the hash set.

When the user releases a number, it is enqueued in the back of the queue (at the right end) only if it was actually in use (number present in hash set)

https://youtu.be/gd6jTRJf85I?t=4389
Time: O(1) (get, release, check), Space: O(2N) = O(N), (N = maxNumbers, 2N = store N nos. in queue + N nos. in set)
'''

from collections import defaultdict, deque

class PhoneDirectory:
    def __init__(self, maxNumbers: int):
        self.q = deque() # stores avaialble nos.
        self.inuse = set() # stores nos. currently in use
        for i in range(maxNumbers):
            self.q.append(i)

    def get(self) -> int:
        if not self.q:
            return -1
        number = self.q.popleft()
        self.inuse.add(number)
        return number

    def check(self, number: int) -> bool:
        if number not in self.inuse:
            return True
        return False

    def release(self, number: int) -> None:
        if number not in self.inuse:
            return
        self.inuse.remove(number)
        self.q.append(number)

def run_PhoneDirectory():
    tests = [ (["PhoneDirectory", "get", "get", "check", "get", "check", "release", "check"],
               [[3], [], [], [2], [], [2], [2], [2]],
               [None, 0, 1, True, 2, False, None, True]),
    ]
    for test in tests:
        operations, nums, ans = test[0], test[1], test[2]
        result = []
        for operation, num in zip(operations, nums):
            if operation == "PhoneDirectory":
                pd = PhoneDirectory(num[0])
                x = None
            elif operation == "get":
                x=pd.get()
            elif operation == "check":
                x=pd.check(num[0])
            elif operation == "release":
                x=pd.release(num[0])
            print(f"{operation}({num}): {x}")
            #print(f"{lfu}\n")
            result.append(x)

        print(f"\nOperations = {operations}")
        print(f"nums = {nums}")
        print(f"result = {result}")
        success = (ans==result)
        print(f"Pass: {success}")
        if not success:
            print("Failed")
            return

run_PhoneDirectory()