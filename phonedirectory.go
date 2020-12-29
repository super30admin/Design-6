// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
/*
// assign available numbers and check or release them
create map with available numbers (can use set too)
create queue to get next number
to get available numbers pop from q and set available false in map
return the number

to check return if its present or not in map

to release add into q and set true in map
*/
package main

import "fmt"

type PhoneDirectory struct {
	m map[int]bool
	q []int
}

func (p *PhoneDirectory) get() int {
	if len((*p).q) > 0 {
		n := (*p).q[0]
		(*p).q = (*p).q[1:]
		(*p).m[n] = false
		return n
	}
	return -1
}

func (p *PhoneDirectory) check(n int) bool {
	return (*p).m[n]
}

func (p *PhoneDirectory) release(n int) {
	(*p).q = append((*p).q, n)
	(*p).m[n] = true
}

func MainPhoneDirectory() {
	pd := &PhoneDirectory{
		m: make(map[int]bool),
		q: []int{},
	}

	for i := 0; i < 10; i++ {
		(*pd).m[i] = true
		(*pd).q = append((*pd).q, i)
	}

	fmt.Println((*pd).get())    // 0
	fmt.Println((*pd).get())    // 1
	fmt.Println((*pd).check(2)) // true
	fmt.Println((*pd).get())    // 2
	fmt.Println((*pd).check(2)) // false
	(*pd).release(2)            // nil
	fmt.Println((*pd).check(2)) // true

}
