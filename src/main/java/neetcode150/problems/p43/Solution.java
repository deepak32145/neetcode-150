package neetcode150.problems.p43;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 43. Multiply Strings  [Medium]  -  Math & Geometry
 * https://leetcode.com/problems/multiply-strings/
 *
 * Problem:
 *   Multiply two non-negative integers given as strings and return the product as a string,
 *   without converting the whole input to a number.
 *
 * Example:
 *   Input:  num1 = "123", num2 = "456"
 *   Output: "56088"
 *
 * Solution - grade-school multiplication:
 *   Reverse both strings so index 0 is the ones digit. digit i times digit j adds into
 *   res[i + j]; push the carry to res[i + j + 1]. Finally reverse res, skip leading zeros and
 *   build the string.
 *   Time: O(m * n)   Space: O(m + n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().multiply("2", "3")); // "6"
        print(new Solution().multiply("123", "456")); // "56088"
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int[] res = new int[num1.length() + num2.length()];

        num1 = reverseString(num1);
        num2 = reverseString(num2);

        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                int digit =
                    Integer.valueOf(String.valueOf(num1.charAt(i))) *
                    Integer.valueOf(String.valueOf(num2.charAt(j)));
                res[i + j] += digit;
                res[i + j + 1] += res[i + j] / 10;
                res[i + j] = res[i + j] % 10;
            }
        }

        reverseArrayInPlace(res);

        // Get the proper starting point to avoid leading zeros
        int startIndex = 0;
        while (startIndex < res.length) {
            if (res[startIndex] != 0) {
                break;
            }
            startIndex++;
        }

        StringBuilder buildResponse = new StringBuilder();
        for (int i = startIndex; i < res.length; i++) {
            buildResponse.append(res[i]);
        }

        return buildResponse.toString();
    }

    private String reverseString(String str) {
        StringBuilder reversedStr = new StringBuilder(str);
        reversedStr.reverse();
        return reversedStr.toString();
    }

    private void reverseArrayInPlace(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
}
