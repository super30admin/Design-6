// ## Problem1 Design Phone Directory (https://leetcode.com/problems/design-phone-directory/)

//time: O(n)
//space: O(n)


/**
 * Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
 * @param {number} maxNumbers
 */
class PhoneDirectory {
    arr = [];
    constructor(maxNumbers) {
        this.maxNumbers = maxNumbers;
        for(let i = 0; i < this.maxNumbers; i++) {
            this.arr[i] = i;
        }
    }
        /**
     * Provide a number which is not assigned to anyone.
            @return - Return an available number. Return -1 if none is available.
     * @return {number}
     */
    get() {
        //if(this.arr.length === 0) return -1;
        let res;
        for(let i = 0; i < this.arr.length; i++) {    
            if(this.arr[i] !== -1) {
                res = this.arr[i]
                this.arr[i] = -1;
                return res;
            } 
        }
        return -1;
    };

    /**
     * Check if a number is available or not. 
     * @param {number} number
     * @return {boolean}
     */
    check(number) {
        if(this.arr[number] >= 0) return true;
        else return false;
    };

    /**
     * Recycle or release a number. 
     * @param {number} number
     * @return {void}
     */
    release(number) {
        this.arr[number] = number;
    };
};


/** 
 * Your PhoneDirectory object will be instantiated and called as such:
 * var obj = new PhoneDirectory(maxNumbers)
 * var param_1 = obj.get()
 * var param_2 = obj.check(number)
 * obj.release(number)
 */