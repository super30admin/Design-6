from collections import deque


class PhoneDirectory(object):

  def __init__(self, maxNumbers):
    """
    Initialize your data structure here
    @param maxNumbers - The maximum numbers that can be stored in the phone directory.
    :type maxNumbers: int
    """
    self.set = set()
    self.queue = deque()

    for i in range(maxNumbers):
      self.set.add(i)
      self.queue.append(i)
    
  def get(self):
    """
    Provide a number which is not assigned to anyone.
    @return - Return an available number. Return -1 if none is available.
    :rtype: int
    """
    if len(self.queue) == 0:return -1
    res = self.queue.popleft()
    self.set.remove(res)
    return res
    

  def check(self, number):
    """
    Check if a number is available or not.
    :type number: int
    :rtype: bool
    """
    return number in self.set

  def release(self, number):
    """
    Recycle or release a number.
    :type number: int
    :rtype: void
    """
    if number not in self.set:
        self.set.add(number)
        self.queue.append(number)
    

# Your PhoneDirectory object will be instantiated and called as such:
# maxNumbers = 10
# obj = PhoneDirectory(maxNumbers)
# print(obj.get())
# print(obj.check(1))
# print(obj.release(2))