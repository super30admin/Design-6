class PhoneDirectory:
  def __init__(self,maxNumber):
    self.hset=set()
    self.avail=[]
    for i in range(1,maxNumber+1):
      self.hset.add(i)
      self.avail.append(i)
  def get(self):
    if len(self.avail)!=0:
      ele=self.avail.pop()
      self.hset.remove(ele)
      return ele
    return -1
  def check(self,number):
    if number in self.hset: return True
    return False
  def release(self,number):
    if number not in self.hset:
      self.hset.add(number)
      self.avail.append(number)
p=PhoneDirectory(3)
print(p.get(),p.get(),p.check(2),p.get(),p.check(2),p.release(2),p.check(2))