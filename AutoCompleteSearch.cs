using System;
using System.Collections.Generic;
using System.Text;

namespace Design
{
    internal class SearchAutoCompleteLC
    {
        class AutoCompleteSystem
        {
            Dictionary<string, int> map;
            StringBuilder sb;
            //TC: O(nlogn)
            //SC: O(n)
            public AutoCompleteSystem(string[] sentences, int[] times)
            {
                map = new Dictionary<string, int>();
                sb = new StringBuilder();
                for (int i = 0; i < sentences.Length; i++)
                {
                    map.Add(sentences[i], map.GetValueOrDefault(sentences[i], 0) + times[i]);
                }
            }
            public List<string> input(char c)
            {
                if (c == '#')
                {
                    string str = sb.ToString();
                    map.Add(str, map.GetValueOrDefault(str, 0) + 1);
                    sb = new StringBuilder();
                    return new List<string>();
                }
                sb.Append(c);
                //System.Collections.Generic.PriorityQueue<string> pq = new System.Collections.Generic.PriorityQueue<string>((a, b) =>
                //{
                //    if (map[a] == map[b])
                //    {
                //        return b.CompareTo(a);
                //    }
                //    return map[a] - map[b];
                //});
                Queue<string> pq = new Queue<string>();
                foreach (string key in map.Keys)
                {
                    if (key.StartsWith(sb.ToString()))
                    {
                        pq.Enqueue(key);
                        if (pq.Count > 3)
                        {
                            pq.Dequeue();
                        }
                    }
                }
                List<string> result = new List<string>();
                while (pq.Count != 0)
                {
                    result.Add(pq.Dequeue());
                }
                return result;
            }
            class TrieNode
            {
                internal Dictionary<string, int> map;
                internal Dictionary<char, TrieNode> children;
                public TrieNode()
                {
                    map = new Dictionary<string, int>();
                    children = new Dictionary<char, TrieNode>();
                }
            }

            TrieNode root;
            private void insert(string sentence, int times)
            {
                TrieNode curr = root;
                for (int i = 0; i < sentence.Length; i++)
                {
                    char c = sentence[i];
                    if (!curr.children.ContainsKey(c))
                    {
                        curr.children.Add(c, new TrieNode());
                    }
                    curr = curr.children[c];
                    curr.map.Add(sentence, curr.map.GetValueOrDefault(sentence, 0) + times);
                }
            }

            private Dictionary<string, int> search(string prefix)
            {
                TrieNode curr = root;
                for (int i = 0; i < prefix.Length; i++)
                {
                    char c = prefix[i];
                    if (!curr.children.ContainsKey(c))
                    {
                        return new Dictionary<string, int>();
                    }
                    curr = curr.children[c];
                }
                return curr.map;
            }

            //TC: O(n)
            //SC: O(n)
            public AutoCompleteSystem(string[] sentences, int[] times, string WithTrieNode)
            {
                sb = new StringBuilder();
                for (int i = 0; i < sentences.Length; i++)
                {
                    insert(sentences[i], times[i]);
                }
            }
            public List<string> inputTrieNodeWithHashMap(char c)
            {
                if (c == '#')
                {
                    string str = sb.ToString();
                    insert(str, 1);
                    sb = new StringBuilder();
                    return new List<string>();
                }
                sb.Append(c);
                Dictionary<string, int> map = search(sb.ToString());
                Queue<string> pq = new Queue<string>();
                //PriorityQueue<string> pq = new PriorityQueue<>((a, b) =>
                //{
                //    if (map[a] == map[b])
                //    {
                //        return b.CompareTo(a);
                //    }
                //    return map[a] - map[b];
                //});
                foreach (string key in map.Keys)
                {
                    if (key.StartsWith(sb.ToString()))
                    {
                        pq.Enqueue(key);
                        if (pq.Count > 3)
                        {
                            pq.Dequeue();
                        }
                    }
                }
                List<string> result = new List<string>();
                while (pq.Count != 0)
                {
                    result.Add(pq.Dequeue());
                }
                return result;
            }

        }        
    }
}
