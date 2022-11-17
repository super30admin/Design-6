// Problem1 Design Phone Directory (https://leetcode.com/problems/design-phone-directory/)

// T.C : O(N) for init, O(1) for methods
// S.C: O(N) for init, O(1) for methods

// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach

/**
 * @param {number} maxNumbers
 */
class PhoneDirectory {

    constructor(maxNumbers) {
        this.maxNumbers = maxNumbers;
        // Maintain available numbers
        this.queue = [];
        for (let i = 0; i < maxNumbers; i++) {
            this.queue.push(i);
        }
        // Maintain in use numbers
        this.set = new Set();
    }

    /**
    * @return {number}
    */
    get = function () {
        if (this.queue.length === 0) {
            return -1;
        }
        let data = this.queue.pop();
        this.set.add(data);
        return data;
    };

    /** 
    * @param {number} number
    * @return {boolean}
    */
    check = function (number) {
        if (this.set.has(number) || number >= this.maxNumbers) {
            return false;
        }
        return true;
    };

    /** 
    * @param {number} number
    * @return {void}
    */
    release = function (number) {
        if (this.set.has(number)) {
            this.set.delete(number);
            this.queue.push(number);
        }
    };
};

/** 
 * Your PhoneDirectory object will be instantiated and called as such:
 * var obj = new PhoneDirectory(maxNumbers)
 * var param_1 = obj.get()
 * var param_2 = obj.check(number)
 * obj.release(number)
 */