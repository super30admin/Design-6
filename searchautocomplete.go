// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
/*
intially create trie for all sentences and store for each letter store all sentences and their counts as a map

then when input is given then search input till that point and get the map of sentences and their counts
add these results to a priority queue of length 3 and filter to get most occured and lexiographically bigger elements and return as results
*/
package main

import (
	"container/heap"
	"fmt"
	"strings"
)

type TrieNode struct {
	children []*TrieNode
	count    map[string]int
}

var root *TrieNode
var input1 string

func insert(sentence string, count int) {
	curr := root
	for i := 0; i < len(sentence); i++ {
		if curr.children[sentence[i]-' '] == nil {
			curr.children[sentence[i]-' '] = &TrieNode{
				children: make([]*TrieNode, 256),
				count:    map[string]int{},
			}
		}
		curr = curr.children[sentence[i]-' ']
		v, e := curr.count[sentence]
		if e {
			curr.count[sentence] = v + count
		} else {
			curr.count[sentence] = count
		}
	}
}

func search(prefix string) map[string]int {
	curr := root
	for i := 0; i < len(prefix); i++ {
		if curr.children[prefix[i]-' '] == nil {
			return map[string]int{}
		}
		curr = curr.children[prefix[i]-' ']
	}
	return curr.count
}

func input(c byte) []string {
	if c == '#' {
		insert(input1, 1)
		input1 = ""
		return []string{}
	}
	input1 += string(c)
	results := search(input1)
	// add results in priorityqueue and return results
	pq := make(PriorityQueue, 0)
	heap.Init(&pq)
	for k, v := range results {
		if strings.HasPrefix(k, input1) {
			x := &PQNode{
				Count:    v,
				Sentence: k,
			}
			heap.Push(&pq, x)
			if pq.Len() > 3 {
				heap.Pop(&pq)
			}
		}
	}

	ans := []string{}
	for pq.Len() > 0 {
		item := heap.Pop(&pq).(*PQNode)
		ans = append(ans, item.Sentence)
	}
	return ans
}

func MainAutoComplete() {
	sentences := []string{"i love you", "island", "ironman", "i love leetcode"}
	times := []int{5, 3, 2, 2}

	root = &TrieNode{
		children: make([]*TrieNode, 256),
		count:    map[string]int{},
	}

	for i := 0; i < len(sentences); i++ {
		insert(sentences[i], times[i])
	}

	fmt.Println(input('i'))
	fmt.Println(input(' '))
	fmt.Println(input('a'))
	fmt.Println(input('#'))
}
