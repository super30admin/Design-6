package main

type PQNode struct {
	Count    int
	Sentence string
}

type PriorityQueue []*PQNode

func (pq PriorityQueue) Len() int {
	return len(pq)
}

func (pq PriorityQueue) Less(a, b int) bool {
	if pq[a].Count == pq[b].Count {
		return pq[b].Sentence < pq[a].Sentence
	}
	return pq[a].Count < pq[b].Count
}

func (pq PriorityQueue) Swap(a, b int) {
	pq[a], pq[b] = pq[b], pq[a]
}

func (pq *PriorityQueue) Push(x interface{}) {
	item := x.(*PQNode)
	*pq = append(*pq, item)
}

func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	x := old[n-1]
	old[n-1] = nil
	*pq = old[0 : n-1]
	return x
}
