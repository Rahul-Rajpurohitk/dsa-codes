/*
isPalindrome

A palindrome is a sequence of characters that reads the same forward and backward.

Given a string, determine if it's a palindrome after removing all non-alphanumeric characters. 
A character is alphanumeric if it's either a letter or a number.

Example 1:
Input: s = 'a dog! a panic in a pagoda.'
Output: True
Example 2:
Input: s = 'abc123'
Output: False


Hints:
palindrome is clear sign of inward two pointers.
traverse left and right from start and end inwardly.
run a main while, 
    inside run a while to check if left is a letter/digit or not.
        left++
    inside run a while to check if right is a letter/digit or not.
        right--
    compare and return;


*/
import java.util.*;

public class Main
{
    
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while(left<right) {
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))) { 
            left++;
            }
        
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))) {
            right--;
            }
            
            char a = Character.toLowerCase(s.charAt(left));
            char b = Character.toLowerCase(s.charAt(right));
        
            if(a != b) {
                return false;
            } else {
                left++;
                right--;
            }
            
        }
        return true;
        
        
    }
    

	public static void main(String[] args) {
		String s = "a dog! a panic in a pagoda.";
		
		boolean res = isPalindrome(s);
        System.out.println(res);
		
	}
}