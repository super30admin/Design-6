TC- O(N) 
SC -O(N)

/**
 * @param {number} maxNumbers
 */
var PhoneDirectory = function(maxNumbers) {
    this.dic = new Array(maxNumbers).fill(0);

};

/**
 * @return {number}
 */
PhoneDirectory.prototype.get = function() {
    const firstEmptyIndex = this.dic.indexOf(0);
    if(firstEmptyIndex === -1){
        return -1;
    }
    this.dic[firstEmptyIndex] = 1;
    return firstEmptyIndex;
    
};

/** 
 * @param {number} number
 * @return {boolean}
 */
PhoneDirectory.prototype.check = function(number) {
    return this.dic[number] === 0;
};

/** 
 * @param {number} number
 * @return {void}
 */
PhoneDirectory.prototype.release = function(number) {
    this.dic[number] = 0;
};

/** 
 * Your PhoneDirectory object will be instantiated and called as such:
 * var obj = new PhoneDirectory(maxNumbers)
 * var param_1 = obj.get()
 * var param_2 = obj.check(number)
 * obj.release(number)
 */