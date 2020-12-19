# Time Complexity: O(1)
# Space Complexity: O(1)
# Approach: Maintain a phone number status list and a unused numbers linked list of range 0 -> maxNumbers
#			get: if length of unused numbers is 0 return -1 else take a number from unused list and mark its phone number status as True
#			check: check the number's index in phone number status list
#			release: Check if number is being used or not. If so, mark its status in the phone number status list as False and add number to the unused number list.
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.phone_number_status = [False] * (maxNumbers)
        self.unused_numbers  = list(range(maxNumbers))
        

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if len(self.unused_numbers) == 0:
            return -1
        number = self.unused_numbers.pop(0)
        self.phone_number_status[number] = True
        return number

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return not self.phone_number_status[number]
        
        

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if not self.phone_number_status[number]:
            return
        self.phone_number_status[number] = False
        self.unused_numbers.append(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)