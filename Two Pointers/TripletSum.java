/*
Given an array of integers, return all triplets [a, b, c] such that a + b + c = 0 . 
The solution must not contain duplicate triplets (e.g., [1, 2, 3] and [2, 3, 1] are considered duplicates). 
If no such triplets are found, return an empty array.

Each triplet can be arranged in any order, and the output can be returned in any order.
Example:
Input: nums = [0, -1, 2, -3, 1]
Output: [[-3, 1, 2], [-1, 0, 1]]

Hints:
a+b=-c

Approach:
sort the Arrays.sort()
traverse the array by locking in each index
for each index i pass the array with starting index and target as i to find the pair that sums to i

*/
import java.util.*;

public class Main
{
    
    public static List<List<Integer>> tripletSum(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        //List are better to use here, for easy add.
        
        Arrays.sort(nums);
        int n = nums.length;
        
        
        //nums = [-3, -1, 0, 1, 2]
        // for each i till the 3rd last i, we will pass the array with starting index and target as i to find a pair that sums to it.
        for(int i = 0 ; i < n - 2; i++){
            //if at any point nums[i] is positive we will never find a triplet with all a, b, c as positive
            if(nums[i]>0) break;
            
            
            if(i>0 && nums[i] == nums[i - 1]) continue; // here we skip the i if it is repeating, avoid duplicate 'a' edge case.
            
            // to enforce  b+c=-a to a+b+c=0
            int target = -nums[i];
            
            List<List<Integer>> pairs = pairSum(nums, i + 1, target);
            for(List<Integer> p: pairs){
                //here we add each 2 pair list with the current i
                res.add(Arrays.asList(nums[i], p.get(0), p.get(1)));
            }
        }
        return res;
    }
    
    public static List<List<Integer>> pairSum(int[] nums, int start, int target){
        List<List<Integer>> pairs = new ArrayList<>();
        int left = start;
        int right = nums.length - 1;
        
        
        while(left < right){
            int sum = nums[left] + nums[right];
            if (sum == target){
                //we add the left and right in a pair.
                pairs.add(Arrays.asList(nums[left], nums[right]));
                //keep traversing for i until we find all the pairs for i.
                left++;
                while(left<right && nums[left] == nums[left - 1]) left++; // avoid duplicate 'b'
            } else if (sum < target){
                //if sum is less than target than we move left towards right so that we increase the summation in the sorted array
                left++;
            } else right--; // is sum is greater than target then move right to left to decrease the sum in a sorted array
        }
        
        return pairs;
        
    }
    

	public static void main(String[] args) {
		int[] nums = {0, -1, 2, -3, 1, -4, -10, -6, 6, 9, 7, 5, 0, 0, 1, 2, 2, 2, 4, -8, -6, -3};
		int target = 7;
		
		List<List<Integer>> res = tripletSum(nums);
		List<List<Integer>> triplets = tripletSum(nums);
        System.out.println(triplets);
		
		
	}
}