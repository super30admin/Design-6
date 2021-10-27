# // Time Complexity :O(n)
# // Space Complexity :O(n)
# // Did this code successfully run on Leetcode :no leetcode premium
# // Any problem you faced while coding this :no leetcode premium


# // Your code here along with comments explaining your approach



from collections import deque

class SnakeGame(object):
    def __init__(self, width, height, food):
        self.width = width
        self.height = height
        self.snake = deque( [ (0,0) ] )
        self.food=food
        self.foodinx=0
        self.bodySet = { (0,0) }
    def move(self,direction):
        oldHead = self.snake[-1]
         newHead = ( oldHead[0] + int(direction=='D') - int(direction=='U'),
                    oldHead[1] + int(direction=='R') - int(direction=='L') )
        #hits the border
         if not ( 0<=newHead[0]<self.height and 0<=newHead[1]<self.width ):
            return -1
        
        #eats body
        if newHead in self.bodySet:
            return -1

        #eat food
        eatFood = ( newHead == tuple( self.food[ self.foodInx ] ) )
        if eatFood:
            self.foodInx += 1
        #move
        if not eatFood:
            oldTail = self.snake.popleft()
            self.bodySet.remove( oldTail )
        self.bodyQueue.append( newHead )
        self.bodySet.add( newHead )
        return len(self.bodySet) - 1

obj=SnakeGame(6,4,[[0,1],[1,1],[2,1]])
