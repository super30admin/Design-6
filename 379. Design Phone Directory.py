from queue import Queue

class PhoneDirectory:
    def __init__(self, maxNumbers):
        """
        Constructor to initialize the PhoneDirectory with available phone numbers.
        Time Complexity: O(maxNumbers)
        Space Complexity: O(maxNumbers)
        """
        self.q = Queue()           # Queue to store available phone numbers
        self.set = set()           # HashSet to efficiently check the availability of a number
        for i in range(maxNumbers):
            self.set.add(i)        # Adding numbers to the set
            self.q.put(i)          # Enqueuing numbers to the queue

    def get(self):
        """
        Get an available phone number from the PhoneDirectory.
        Time Complexity: O(1)
        Space Complexity: O(1)
        """
        if self.q.empty():
            return -1
        num = self.q.get()        # Dequeue a number from the queue
        self.set.remove(num)      # Remove the number from the set since it's now assigned
        return num

    def check(self, number):
        """
        Check if a given phone number is available in the PhoneDirectory.
        Time Complexity: O(1)
        Space Complexity: O(1)
        """
        return number in self.set

    def release(self, number):
        """
        Release a phone number and make it available again in the PhoneDirectory.
        Time Complexity: O(1)
        Space Complexity: O(1)
        """
        if number not in self.set:
            self.set.add(number)   # Add the released number back to the set
            self.q.put(number)     # Enqueue the released number back to the queue
