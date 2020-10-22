//https://leetcode.com/problems/design-phone-directory/
//runtime:
//  get O(1)
//  has O(1)
//  release O(1)
//storage: O(n)
//passing leetcode?: yes
//obstacles: remember the javascript Set() data structure methods
//explanation:
//  store numbers 0-maxnumbers in an 'available' hashset
//  get -> return first value in 'available'
//  has -> query hashset for number
//  release -> add number back to hashset

/**
 * Initialize your data structure here
 @param maxNumbers - The maximum numbers that can be stored in the phone directory.
 * @param {number} maxNumbers
 */
var PhoneDirectory = function(maxNumbers) {
  let set = new Set()
  for(let i = 0; i != maxNumbers; ++i) {
    set.add(i)
  }
  return Object.assign(
    Object.create(PhoneDirectory.prototype), {
      available: set
    }
  )
};

/**
 * Provide a number which is not assigned to anyone.
 @return - Return an available number. Return -1 if none is available.
 * @return {number}
 */
PhoneDirectory.prototype.get = function() {
  if (this.available.size < 1) return -1

  let result = this.available.values().next().value
  this.available.delete(result)
  return result
};

/**
 * Check if a number is available or not.
 * @param {number} number
 * @return {boolean}
 */
PhoneDirectory.prototype.check = function(number) {
  return this.available.has(number)
};

/**
 * Recycle or release a number.
 * @param {number} number
 * @return {void}
 */
PhoneDirectory.prototype.release = function(number) {
  this.available.add(number)
};

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * var obj = new PhoneDirectory(maxNumbers)
 * var param_1 = obj.get()
 * var param_2 = obj.check(number)
 * obj.release(number)
 */
