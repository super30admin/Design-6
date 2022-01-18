// Time: O(1)
//Space: O(n) where n = maxNumbers

class PhoneDirectory{
	
	Queue<Integer> q;
	HashSet<Integer> set;

	public PhoneDirectory(int maxNumbers){
		q = new LinkedList<>();
		set = new HashSet<>();
		for(int i=0; i<= maxNumbers; i++){
			set.add(i); q.add(i);
		}
	}

	public int get(){
		if(q.isEmpty()) return -1;
		int result = q.poll();
		set.remove(result);
		return result;
	}

	public boolean cheack(int number){
		return set.contains(number);
	}

	public void release(int number){
		if(set.contains(number)) return;
		set.add(number);
		q.add(number);
	}
}