# using hash set and queue.
# hash set to store the numbers that have been taken.
# available numbers queue to store all the numbers that are available.
# get - pop a number from the queue and add to the taken set. 
# check - check if the number is not present in the taken set.
# release - remove the number from taken set and add the number back to available queue.
# time complexity for user performed operations-
# GET O(1), CHECK O(1), RELEASE O(1)
# space complexity - O(2n) # set to hold the taken numbers, queue to store the available numbers
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        # hash set to store the numbers which have been taken.
        self.taken_numbers = set()
        
        # queue to store the available numbers.
        self.available_numbers = deque(list(range(maxNumbers)))     

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if len(self.available_numbers)!=0:
            num = self.available_numbers.popleft() # you can choose to pop either from left or right (optional).
            self.taken_numbers.add(num)
            return num
        return -1

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return number not in self.taken_numbers
        
    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        # remove the number from the taken numbers set.
        # add the number back to available numbers queue.
        if number in self.taken_numbers:
            self.taken_numbers.remove(number)
            self.available_numbers.append(number)
        

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)


# using only hash sets to store the numbers and perform operations.
# hash set to store the available numbers.
# get - pop a number available in the set. (order doesn't matter)
# check - check if the number is present in the available set.
# release - add the number back to available set.
# time complexity for user performed operations-
# GET O(1), CHECK O(1), RELEASE O(1)
# space complexity - O(n) # set to hold the available numbers
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        # hash set to store the numbers that are available.
        # we can use hash set because it doesn't matter in which order the numbers are returned.
        self.available_numbers = set(list(range(maxNumbers)))
 

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if len(self.available_numbers)!=0:
            return self.available_numbers.pop() # you can choose to pop either from left or right (optional).
        return -1

    
    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return number in self.available_numbers
        

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        # add the number to the available numbers set
        if number not in self.available_numbers:
            self.available_numbers.add(number)
            