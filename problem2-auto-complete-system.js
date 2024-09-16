//https://leetcode.com/problems/design-search-autocomplete-system/
//runtime: input method: O(nlgn)
//storage: O(n) nodes in the priority queue
//passing leetcode?: yes
//obstacles: lexical comparison in javascript was different than in the library i used (true / false vs -1, 0, 1)
//explanation:
//  initialize a hashmap of the initial sentences
//  for each keyboard input
//    if it is a #, update or create entry in hashmap and return
//
//  for each sentence
//     if it startsWith(current string) add to a priority queue
//  pop top 3 entries from priority queue
//
//  Note: priority queue insertion will be based first on count, then on lexicographic order

// priorityqueue from https://stackoverflow.com/questions/42919469/efficient-way-to-implement-priority-queue-in-javascript
const mytop = 0,
  myparent = (c) => ((c + 1) >>> 1) - 1,
  myleft = (c) => (c << 1) + 1,
  myright = (c) => (c + 1) << 1;
class PriorityQueue {
  constructor(c = (d, e) => d > e) {
    (this._heap = []), (this._comparator = c);
  }
  size() {
    return this._heap.length;
  }
  isEmpty() {
    return 0 == this.size();
  }
  peek() {
    return this._heap[mytop];
  }
  push(...c) {
    return (
      c.forEach((d) => {
        this._heap.push(d), this._siftUp();
      }),
        this.size()
    );
  }
  pop() {
    const c = this.peek(),
      d = this.size() - 1;
    return d > mytop && this._swap(mytop, d), this._heap.pop(), this._siftDown(), c;
  }
  replace(c) {
    const d = this.peek();
    return (this._heap[mytop] = c), this._siftDown(), d;
  }
  _greater(c, d) {
    return this._comparator(this._heap[c], this._heap[d]);
  }
  _swap(c, d) {
    [this._heap[c], this._heap[d]] = [this._heap[d], this._heap[c]];
  }
  _siftUp() {
    for (let c = this.size() - 1; c > mytop && this._greater(c, myparent(c)); ) this._swap(c, myparent(c)), (c = myparent(c));
  }
  _siftDown() {
    for (let d, c = mytop; (myleft(c) < this.size() && this._greater(myleft(c), c)) || (myright(c) < this.size() && this._greater(myright(c), c)); )
      (d = myright(c) < this.size() && this._greater(myright(c), myleft(c)) ? myright(c) : myleft(c)), this._swap(c, d), (c = d);
  }
}

/**
 * @param {string[]} sentences
 * @param {number[]} times
 */
var AutocompleteSystem = function(sentences, times) {
  let sentencesMap = new Map()

  sentences.forEach((sentence, index) => {
    sentencesMap.set(sentence, times[index])
  })

  return Object.assign(
    Object.create(AutocompleteSystem.prototype), {
      sentencesMap, input_str: ''
    }
  )
};

/**
 * @param {character} c
 * @return {string[]}
 */
AutocompleteSystem.prototype.input = function(c) {
  if (c === '#') {
    if (this.input_str.length > 0) {
      let curCount = this.sentencesMap.get(this.input_str) == null ? 0 : this.sentencesMap.get(this.input_str)
      this.sentencesMap.set(this.input_str, curCount + 1)
      this.input_str = ''
    }
    return []
  }
  this.input_str += c

  let priorityQueue = new PriorityQueue((a, b) => {
    if (a.count !== b.count) {
      return a.count > b.count
    }
    return a.sentence.localeCompare(b.sentence) == -1
  })

  this.sentencesMap.forEach((value, key) => {
    if (key.startsWith(this.input_str)) {
      priorityQueue.push({count: value, sentence: key})
    }
  })

  let output = []
  while (!priorityQueue.isEmpty() && output.length < 3) {
    output.push(priorityQueue.pop().sentence)
  }

  return output;
};

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * var obj = new AutocompleteSystem(sentences, times)
 * var param_1 = obj.input(c)
 */
let macs = new AutocompleteSystem(["i love you","island","iroman","i love leetcode"],[5,3,2,2])
let result;
result = macs.input('i')
result = macs.input(' ')
result = macs.input('a')
result = macs.input('#')


// ["AutocompleteSystem","input","input","input","input","input","input","input","input","input","input","input","input"]
// [[["i love you","island","iroman","i love leetcode"],[5,3,2,2]],["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"]]
