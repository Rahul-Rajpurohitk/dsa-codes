/*
You are given an array of numbers, each representing the height of a vertical line on a graph. 
A container can be formed with any pair of these lines, along with the x-axis of the graph. 
Return the amount of water which the largest container can hold.

input: heights = [2,5,8,3,5,6,8,0,10]
output: 48

Hints:
array goes from 0 - n so that is sorted here in this problem.
here, we have to do l * b.
For length we will always select the min(left, right)  
For breadth it will the distance between left, right(right - left).
store max through out the traverse and return the max at the end.


*/
import java.util.*;

public class Main
{
    
    public static int largestContainer(int[] nums) {
        int max = 0;
        int left = 0;
        int right = nums.length - 1;
        
        while(left<right) {
            int result = Math.min(nums[left], nums[right]) * (right - left); 
            max = Math.max(max, result);
            
            if(nums[left] < nums[right]){
                // we know buy either direction we move the breadth is going to decrease.
                // so we check for the height, if left is less, 
                // then we move left in a hope we will encounter a number that would be greater than current left.
                left++; 
            } else if(nums[left] > nums[right]) {
                // if right is small than we look for something on right to be bigger than current left
                right--;
            } else {
                // if both are equal than move both.
                left++;
                right--;
            }
            
        }
        
        return max;
        
    }
    

	public static void main(String[] args) {
		int[] nums = {2,5,8,3,5,6,8,0,10};
		
		int res = largestContainer(nums);
        System.out.println(res);
		
		
	}
}