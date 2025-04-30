/*
Pair Sum - Unsorted

Given an array of integers, 
return the indexes of any two numbers that add up to a target. 
The order of the indexes in the result doesn't matter. If no pair is found, return an empty array.

Example:
Input: nums = [-1, 3, 4, 2], target = 3
Output: [0, 2]
Explanation: nums[0] + nums[2] = -1 + 4 = 3



*/


import java.util.*;

public class Main
{
    
    public static int[] pairSumUnsorted(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < arr.length; i++){
            int comp = k - arr[i];
            if(map.containsKey(comp)){
                return new int[]{i, map.get(comp)};
            }
            
            map.put(arr[i], i);
            
        }
        return new int[0];
    }
    

	public static void main(String[] args) {
		int[] tests = {-1, 3, 4, 2};
		int k = 3;
        
        int[] res = pairSumUnsorted(tests, k);
		System.out.println("The result array:" + Arrays.toString(res));
	}
}