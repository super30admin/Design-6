/*
Problem:
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). 
For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of the sentence already typed.
Here are the specific requirements:
The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character '#', you need to save the sentence and increase its hot degree.
The input sentence will always start with a letter and end with '#', and only contain lowercase letters and spaces.
You may assume that the input sentence is always valid.

Examples:
"i love you", freq = 5
"island", freq = 3
"ironman", freq = 2
"i love leetcode", freq = 2

Then, we want to implement a search autocomplete system where given the input "i", it will return the top 3 sentences with highest frequencies that start with "i". 
In this case, the expected output would be:

["i love you", "island", "i love leetcode"]

*/

//-----------Write the code----------------------
//Premium Problem
