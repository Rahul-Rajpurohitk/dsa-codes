/*
Shift zeros to the end.

Given an array of integers, 
modify the array in place to move all zeros to the end while maintaining the relative order of non-zero elements.

Example:
Input: nums = [0, 1, 0, 3, 2] / [0,0,0,0,0,2,0,5,0,0,8,9]
Output: [1, 3, 2, 0, 0]


Hints:
unidirectional traversing for two pointer,
one pointer(left) to keep track of the information
other pointer(right) to find the information.


*/
import java.util.*;

public class Main
{
    
    public static int[] zerosToTheEnd(int[] nums) {
        int left = 0;
        
        for(int right = 0; right < nums.length; right++){
            if(nums[right]!=0){
                int temp = 0;
                temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            
        }
        return nums;
        
    }
    

	public static void main(String[] args) {
		int[] nums = {0, 1, 0, 3, 2};
		
		int[] res = zerosToTheEnd(nums);
		for(int n:nums){
            System.out.println(n);
		}
	}
}