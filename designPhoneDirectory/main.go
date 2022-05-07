
type PhoneDirectory struct {
    q *queue
    max int
    reserved map[int]bool
}


func Constructor(maxNumbers int) PhoneDirectory {
    return PhoneDirectory{
        q: NewQueue(maxNumbers),
        max: maxNumbers,
        reserved: map[int]bool{},
    }
}

// time: o(1)
// space: o(1)
func (this *PhoneDirectory) Get() int {
    if len(this.reserved) == this.max {
        return -1
    }
    dq := this.q.dequeue()
    this.reserved[dq] = true
    return dq
}

// time: o(1)
// space: o(1)
func (this *PhoneDirectory) Check(number int) bool {
    _, ok := this.reserved[number]
    return !ok
}

// time: o(1)
// space: o(1)
func (this *PhoneDirectory) Release(number int)  {
    _, ok := this.reserved[number]
    if ok {
        delete(this.reserved, number)
        this.q.enqueue(number)
    }
}

/************ Queue impl *****************/
type listNode struct {
    val int
    next *listNode
}

type queue struct {
    head *listNode
    tail *listNode
}

func NewQueue(size int) *queue {
    head := &listNode{val: 0}
    tail := head
    // size 3
    // 0->1->2
    for i := 1; i < size; i++ {
        tail.next = &listNode{val: i}
        tail = tail.next
    }
    return &queue{
        head: head,
        tail: tail,
    }
}

func (q *queue) dequeue() int {
    if q.head == nil {
        panic("head is nil")
    }
    current := q.head
    newHead := current.next
    q.head = newHead
    current.next = nil
    return current.val
}

func (q *queue) enqueue(val int) {
    newNode := &listNode{val: val}
    if q.head == nil {
        q.head = newNode
        q.tail = newNode
        return
    }
    q.tail.next = newNode
    q.tail = newNode
}
/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * obj := Constructor(maxNumbers);
 * param_1 := obj.Get();
 * param_2 := obj.Check(number);
 * obj.Release(number);
 */
