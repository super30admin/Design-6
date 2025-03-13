class PhoneDirectory:
    # tc O(1) for all operations, sc O(n).
    def __init__(self, maxNumbers: int):
        self.available = {num for num in range(maxNumbers)}
        self.unavailable = set()

    def get(self) -> int:
        if not self.available:
            return -1
        num = self.available.pop()
        self.unavailable.add(num)
        return num        

    def check(self, number: int) -> bool:
        return number in self.available
        
    def release(self, number: int) -> None:
        if number in self.unavailable:
            self.unavailable.remove(number)
            self.available.add(number)