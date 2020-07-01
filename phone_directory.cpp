/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
 
 // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);

class PhoneDirectory {

	unordered_set<int>directory;
	auto it=directory.begin();
    /** Initialize your data structure here

        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */

    public PhoneDirectory(int maxNumbers) 
	{
        for(int i=0; i<maxNumbers; i++)
			directory.insert(i);
    }

    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */

    public int get()
	{
        if(directory.size()==0)
			return -1;
		int num=*it;
		it++;
        directory.erase(num);
        return num;
    }    

    /** Check if a number is available or not. */

    public boolean check(int number) 
	{
        return directory.count(number);
    }    

    /** Recycle or release a number. */

    public void release(int number)
	{
        directory.insert(number);
    }

}