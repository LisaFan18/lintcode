# Summary
1. Refer to https://www.geeksforgeeks.org 
2. sort in ascending order (minimum is at the beginning index )

# Linear Sorting
## Count Sort
**Time/Space: O(n+k)** k is the range of input.  
example: arr=[2, 3, 1, 2, 1, 1]   
1. 条件： 要排序的element在一个固定的range; 可以转换为count数组的index。 例如element是char或integer,如果integer为negative，通过plus minimum转换到positive 
2. 算法： 
* 首先生成一个count数组：数组index为arr[i]; 数组content为count； 得到count=[0, 3, 2, 1]  
* 再利用count[i] += count[i-1] 计算 arr[i]在output数组里所在的position 得到count=[0, 3, 5, 6]
* 从后往前往output数组里填充element，根据count数组维护的hashing 关系。
* 最后再把output copy到arr数组。

### Pseudo code
```java
void sort(char arr[]) 
    { 
        int n = arr.length; 
  
        // The output character array that will have sorted arr 
        char output[] = new char[n]; 
  
        // Create a count array to store count of inidividul 
        // characters and initialize count array as 0 
        int count[] = new int[256]; 
        for (int i=0; i<256; ++i) 
            count[i] = 0; 
  
        // store count of each character 
        for (int i=0; i<n; ++i) 
            ++count[arr[i]]; 
  
        // Change count[i] so that count[i] now contains actual 
        // position of this character in output array 
        for (int i=1; i<=255; ++i) 
            count[i] += count[i-1]; 
  
        // Build the output character array 
        // To make it stable we are operating in reverse order. 
        for (int i = n-1; i>=0; i--) 
        { 
            output[count[arr[i]]-1] = arr[i]; 
            --count[arr[i]]; 
        } 
  
        // Copy the output array to arr, so that arr now contains sorted characters 
        for (int i = 0; i<n; ++i) 
            arr[i] = output[i]; 
    }
```
## Bucket Sort
**Time/Space: O(n)**
### Pseudo code 
https://www.geeksforgeeks.org/bucket-sort-2/

# Lower bound O(nlogn) of comparison sorting
decision tree:     
* 每一个leaf node 代表数组元素的possible permutaion order.  每一个internal node代表两个element的compare。 
* 其中的一条path代表一种sorting algorithm的一次执行过程
* decision tree是一颗full binary(left branch denotes <=; right branch denotes >). h表示tree的最大高度, 最多有2^h个叶子结点。如果数组里有重复元素，permation数小于n!; 
n! <= 2^h; log2(n!) <= h; [log2(n!) is Theta(nlogn)](https://stackoverflow.com/questions/2095395/is-logn-%CE%98n-logn)

## Selection sort
1. Time: O(n^2) in average and worst case 
2. Space: O(1) 
### Algorithm
1. 数组被划分为左边sorted，右边unsorted两部分。每次都从右边unsorted里面找出minimum的**index(arr[j] < arr[min_idx])**，和unsorted开头元素swap；   
2. repeatedly finding the minimum element from unsorted part and putting it at the beginning.  
3. 优点：最多O(n)次swap，it can be useful when memory write is a costly operation.  
4. The algorithm maintains two subarrays in a given array.  
  * The subarray which is already sorted. (left part)
  * Remaining subarray which is unsorted. (right part)
### Pseudo code
```java
 void sort(int arr[]) 
    { 
        int n = arr.length; 
  
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j; 
  
            // Swap the found minimum element with the first element 
    
        } 
    } 
```

## Bubble Sort
1. Time: O(n^2) in average and worst case   
2. Space: O(1)   
3. Repeatedly swap the **adjandent two element (arr[j] > arr[j+1])** if they're in wrong place.   
可以把数组的左边想象成湖面，右边想象成湖底。    
在compare的过程， 数值小的像泡泡一样往湖面（左边）靠拢，但不一定停在正确的位置； 数组大像石头一样往湖底走，而且一定停在正确的位置。
所以第一轮结束，maximum一定在最右边的位置上。
4. The algorithm maintains two subarrays in a given array.  
  * The subarray which is already sorted. (right part)
  * Remaining subarray which is unsorted. (left part)
### Pseudo code 
```java
void bubbleSort(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (arr[j] > arr[j+1]) 
                { 
                    // swap arr[j+1] and arr[i] 
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
    } 
```
