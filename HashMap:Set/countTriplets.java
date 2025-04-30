/*
A geometric sequence triplet is a sequence of three numbers where each successive number is
obtained by multiplying the preceding number by a constant called the common ratio.
Let's examine three triplets to understand how this works:
● (1, 2, 4): Geometric sequence with a ratio of 2 (i.e., [1, 1·2 = 2, 2·2 = 4]).
● (5, 15, 45): Geometric sequence with a ratio of 3 (i.e., [5, 5·3 = 15, 15·3 = 45]).
● (2, 3, 4): Not a geometric sequence.
Given an array of integers and a common ratio r, find all triplets of indexes (i, j, k) that follow a
geometric sequence for i < j < k. It’s possible to encounter duplicate triplets in the array.
Example:
Input: nums = [2, 1, 2, 4, 8, 8], r = 2
Output: 5
Explanation: Triplet [2, 4, 8] occurs at indexes (0, 3, 4), (0, 3, 5), (2, 3, 4), (2, 3, 5). Triplet
[1, 2, 4] occurs at indexes (1, 2, 3).

Intuition:
to find x, x*r, x*r*r.
fix the middle of {i, j, k}. i.e. j, then we only need to look for x/r and x*r, 
now for the order to be maintained, i < j < k.(has to be sequence), we now that x < x*r < x*r*r.
meaning for x, 
How many i<j have nums[i] == nums[j]/r?
How many k>j have nums[k] == nums[j]*r?
also, the check to see. if nums[x]%r == 0. then only proceed to move, 
for every i<j maintain left map and for every j<k maintain right map.
in the end multiple the frequencies present for each x in left(x/r) and in right(x*r) meaning.
for {2,4,8} we have two's of 2 and two's of 8, then 2 * 2 =4 total of 4 instances of the triplet; duplicaiton maintained.
during each tranverse, remove the j from the right so that it is no longer part of the right



*/


import java.util.*;

public class Main
{
    
    public static long countTriplets(long[] nums, long r) {
        Map<Long, Long> left = new HashMap<>(); // freq of values to the left of j
        Map<Long, Long> right = new HashMap<>(); // freq of values to the right of j
        
        // populate the right map with complete frequencies.
        for(long x: nums){
            right.put(x, right.getOrDefault(x, 0L) + 1);
        }
        
        long count = 0;
        
        //[2, 1, 2, 4, 8, 8] r = 2
        
        for(long x: nums){
            // remove this x from right (it's now the 'center')
            right.put(x, right.get(x) - 1);
            
            // if x is divisible by r, it can be a middle term
            if(x % r == 0){
                long leftval = x/r; // find i = x/r
                long rightval = x*r; // find k = x*r
                long c1 = left.getOrDefault(leftval, 0L);
                long c2 = right.getOrDefault(rightval, 0L);
                count += c1 * c2; // store the multiplication in count.
            }
            
            // add x into left (for future centers)
            left.put(x, left.getOrDefault(x, 0L) + 1);
            
        }
        
        return count;
        
    }
    

	public static void main(String[] args) {
        long[] nums = { 2, 1, 2, 4, 8, 8 };
        long r = 2;
        System.out.println(countTriplets(nums, r));
       
    }

}