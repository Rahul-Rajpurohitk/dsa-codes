/*
Given an array of integers sorted in ascending order and a target value, 
return the indexes of any pair of numbers in the array that sum to the target. 
The order of the indexes in the result doesn't matter. If no pair is found, return an empty array.

Example 1:
Input: nums = [-5, -2, 3, 4, 6], target = 7
Output: [2, 3]

Hints: Sorted array in ascending
Inward traversal
*/


public class Main
{
    
    public static int[] pairSum(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        
        while(left < right){
            int sum = nums[left] + nums[right];
            if (sum == target){
                return new int[]{left, right};
            } else if (sum < target){
                left++;
            } else right--;
        }
        
        return new int[0];
        
    }
    

	public static void main(String[] args) {
		int[] nums = {-5, -2, 3, 4, 6};
		int target = 7;
		
		int[] res = pairSum(nums, target);
		if(res.length == 2){
		    System.out.printf("Found at [%d, %d]%n", res[0], res[1]);
		} else{
		    System.out.println("No pair found");
		}
		
		
		
	}
}