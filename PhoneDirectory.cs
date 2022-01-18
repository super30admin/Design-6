using System;
using System.Collections.Generic;
using System.Text;

namespace Design
{
    internal class PhoneDirectoryLC
    {
        class PhoneDirectory
        {
            Queue<int> q;
            HashSet<int> set;

            public PhoneDirectory(int maxNumbers)
            {
                q = new Queue<int>();
                set = new HashSet<int>();
                for (int i = 0; i < maxNumbers; i++)
                {
                    q.Enqueue(i);
                    set.Add(i);
                }
            }
            //Provide a number which is not assigned to anyone
            //Return an availble number. Return -1 is none is availble
            public int get()
            {
                if (q.Count == 0)
                {
                    return -1;
                }
                int number = q.Dequeue();
                set.Remove(number);
                return number;
            }
            //Check is a number is availble or not
            public bool Check(int number)
            {
                return set.Contains(number);
            }
            //Recycle or release a number
            public void release(int number)
            {
                if (set.Contains(number)) return;
                set.Add(number);
                q.Enqueue(number);
            }
        }
    }
}
