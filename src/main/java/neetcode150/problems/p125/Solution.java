package neetcode150.problems.p125;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 125. Valid Palindrome  [Easy]  -  Two Pointers
 * https://leetcode.com/problems/valid-palindrome/
 *
 * Problem:
 *   After lowercasing and removing non-alphanumeric characters, does s read the same both
 *   ways?
 *
 * Example:
 *   Input:  s = "A man, a plan, a canal: Panama"
 *   Output: true
 *
 * Solution - two pointers from both ends:
 *   Skip non-alphanumeric characters on each side, compare the rest case-insensitively and
 *   move inward.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isPalindrome("A man, a plan, a canal: Panama")); // true
        print(new Solution().isPalindrome("race a car")); // false
    }

public boolean isPalindrome(String s) {
        
    int i = 0;
    int j = s.length() - 1;
    while (i < j) {
        
        Character start = s.charAt(i);
        Character end = s.charAt(j);
        
        if (!Character.isLetterOrDigit(start)) {
            i++;
            continue;
        }
        
        if (!Character.isLetterOrDigit(end)) {
            j--;
            continue;
        }
        
        if (Character.toLowerCase(start) != Character.toLowerCase(end)) {
            return false;
        }
        
        i++;
        j--;    
    }
    
    return true;
}

}
