/*
Next Lexicographical Sequence

Given a string of lowercase English letters, 
rearrange the characters to form a new string representing the next immediate sequence in lexicographical (alphabetical) order. 
If the given string is already last in lexicographical order among all possible arrangements, 
return the arrangement that's first in lexicographical order.

Example 1:
Input: s = 'abcd'
Output: 'abdc'

Hints:
start from right to look for pivot; i < i+1;

find the pivot, where i < i+1; meaning check the string for i < i+1, right most i.
if reached at the end of the string, then simply swap the last two digit.

TL;
orderScan right→left to find the first place where things “go downhill” (pivot).
If no such place, you’re at the last permutation—reverse the whole thing and stop.
Otherwise, in that downhill suffix find the smallest element that’s bigger than your pivot—swap them.
Finally, reverse the suffix (to ascending) so you get the minimal increase.

*/


import java.util.*;

public class Main
{
    
    public static String lexicographical(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        
        // 1) Find pivot: rightmost i with arr[i] > arr[i+1]; where it goes downhill; from where it is non-increasing.
        int pivot = n - 2;
        while(pivot > 0 && arr[pivot] >= arr[pivot + 1]){
            
            pivot --;
        }
        
        // 2) If no pivot, whole array is descending => reverse it all
        if(pivot < 0){
            reverse(arr, 0, n-1);
        }
        
        // after finding pivot, check with the values on the right side of it and look for any value that is smallest in the right and yet bigger than pivot
        int successor = n - 1;
        while(arr[successor] < arr[pivot]){
            successor -- ;
        }
        
        swap(arr, successor, pivot);
        
        reverse(arr, pivot + 1, n-1);
        
        return new String(arr);
    }
    
    public static void swap(char[] arr, int successor, int pivot){
        char temp = arr[successor];
        arr[successor] = arr[pivot];
        arr[pivot] = temp;
    }
    
    public static void reverse(char[] arr, int from, int to){
        while(from < to){
            swap(arr, from++, to--); // this first pass the orginal value and then just increment and decrement.
        }
    }
    

	public static void main(String[] args) {
		String[] tests = {
            "abcd",    // normal
            "abdc",    // pivot at pos1 (b)
            "dcba",    // last perm
            "hefg",    
            "dhck", 
            "dkhc"
        };

		for(String t:tests){
		    System.out.printf("%s → %s%n", t, lexicographical(t));
		}
	}
}