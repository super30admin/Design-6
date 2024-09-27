# Time Complexity : O(1)
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
'''
1. Use hashset to check and release
2. To get use and iterator on hashset and call next
'''
class PhoneDirectory:
    
    def __init__(self, num):
        
        self.num = num
        self.num_set = set()
        
        for i in range(num):
            self.num_set.add(i)
        
    
    def get(self):
        
        dict_it = iter(self.num_set)
        key = next(dict_it)
        
        self.num_set.remove(key)
        return key
        
        
    def check(self, key):
        
        return key in self.num_set
        
        
    def release(self, key):
        self.num_set.add(key)