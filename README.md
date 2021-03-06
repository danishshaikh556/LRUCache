LRUCache
========

LRU Cache implemented in Java

Your cache should store simple key/value strings of length up to 10 characters. It should also have a customizable upper bound to the number of keys that can be stored in the cache at any time. You do not have to be thread safe.

Possible Inputs:

BOUND    :  Set the upper bound. If the cache size is currently greater than this number, then extra entries must be removed following the LRU policy

SET   :  Set the value of this key

GET   :  Get the value of this key and prints to stdout.

PEEK   :  Gets the value of the key but does not mark it as being used. Prints the value to standard out.

DUMP  :  Prints the current state of the cache as a list of key/value pairs in alphabetical order by key.

 

Input Format:

First line of input contains an integer N,the number of commands.

The following N lines each describe a command.

Note: The first command will always be BOUND.

Output Format:

Print the appropriate outputs for GET , PEEK and DUMP commands. In case for GET/PEEK command if the key does not exist in the cache just output the string "NULL"(quotes are for clarity).

 

Sample Input

8
BOUND 2
SET a 2
SET b 4
GET b
PEEK a
SET c 5
GET a
DUMP

Sample Output

4
2
NULL
b 4
c 5

Constraints:

Total number of lines in input will be no more than 1,000,000(10^6)
