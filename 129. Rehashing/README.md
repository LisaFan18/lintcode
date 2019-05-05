# Summary
1. 由于hashtable的size 已经double，所以%运算得到的index完全不同。在old hashtable里的所有的元素都需要rehashing。是一个很费时的操作
2. old hashtable的数据结构是: array of LinkedList。遍历时需要two layers of for - loop
```java
for(int i=0; i<hashTable.length; i++){
        while(hashTable[i] != null) {
            //...
            hashTable[i] = hashTable[i].next;
        }
}
```
3. 如果%结果为负数，怎么办？利用%的特性：a % b = (a % b + b) % b to make it is a non negative integer.

# Solution
```java
public ListNode[] rehashing(ListNode[] hashTable) {
       if(hashTable == null || hashTable.length ==0 ){
           return hashTable;
       }
       
       int newCapacity = 2*hashTable.length;
       ListNode[] newHashTable = new ListNode[newCapacity];
       
       // rehashing by iterating each node 
       // for(ListNode node:hashTable){ // ERROR: hashTable is an array of linkedList
       for(int i=0; i<hashTable.length; i++){
            while(hashTable[i] != null) {
            //If val is negative, val%newCapacity will get a negative. negative: -4 % 3 you will get -1. 
            //You can use function: a % b = (a % b + b) % b to make it is a non negative integer.
               int newIndex = (hashTable[i].val % newCapacity + newCapacity) % newCapacity;
               
               ListNode newNode =  new ListNode(hashTable[i].val);
               
               // use open hashing to resolve collision
               if(newHashTable[newIndex] == null){
                   newHashTable[newIndex] = newNode;
               }else{
                   ListNode dummy = newHashTable[newIndex];
                   // find the last node in the LinkedList
                   while(dummy.next != null){
                       dummy = dummy.next;
                   }
                   dummy.next = newNode;
               }
               
               // process to the next node in the linkedList !!!!
               hashTable[i] = hashTable[i].next;
            }
       }
       
       return newHashTable;
    }
```
