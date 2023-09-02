class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.maxNumbers = maxNumbers
        self.HashMap = {}
        for i in range(self.maxNumbers):
            self.HashMap[i] = True 

    def get(self) -> int:
        for i in range(self.maxNumbers):
            if self.HashMap[i] == True:
                self.HashMap[i] = False
                return i
        return -1

    def check(self, number: int) -> bool:
        return self.HashMap[number]
        
    def release(self, number: int) -> None:
        self.HashMap[number] = True 
        
