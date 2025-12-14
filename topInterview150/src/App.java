import java.util.*;

// 88. Merge Sorted Array
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;         // last valid element in nums1
        int j = n - 1;         // last element in nums2
        int k = m + n - 1;     // last position in nums1

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
    }

// 27. Remove Element
    public int removeElement(int[] nums, int val) {
        int k = 0; // index for where to write the next valid element

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i]; // keep this element
                k++;
            }
        }
        return k; // number of elements not equal to val
    }

// 26. Remove Duplicates from Sorted Array
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int k = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[k - 1]) {
                if (i != k) nums[k] = nums[i]; // only write if needed
                k++;
            }
        }
        return k;
    }

// 80. Remove Duplicates from Sorted Array II
    public int removeDuplicatesII(int[] nums) {
        if (nums.length <= 2) return nums.length;

        int k = 2; // Start from 3rd position
        for (int i = 2; i < nums.length; i++) {
            // Compare current element with nums[k-2]
            // If it's the same, that means we already have 2 copies, skip it
            if (nums[i] != nums[k - 2]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }


// 169. Majority Element
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

// 189. Rotate Array
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // In case k > n

        // Step 1: reverse entire array
        reverse(nums, 0, n - 1);
        // Step 2: reverse first k
        reverse(nums, 0, k - 1);
        // Step 3: reverse rest
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

// 121. Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // track lowest price so far
        int maxProfit = 0; // track best profit

        for (int price : prices) {
            // update minimum price
            if (price < minPrice) {
                minPrice = price;
            }
            // check profit if sold today
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }

// 122. Best Time to Buy and Sell Stock II
    public int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

// 55. Jump Game
    public boolean canJump(int[] nums) {
        int farthest = 0; // Tracks the farthest index we can reach
        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) {
                // If the current index is beyond the farthest reachable index, return false
                return false;
            }
            // Update the farthest reachable index
            farthest = Math.max(farthest, i + nums[i]);
            // If the farthest reachable index is beyond or at the last index, return true
            if (farthest >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

// 45. Jump Game II
    public int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0; // No jumps needed if we are already at the last index.

        int jumps = 0; // Number of jumps made
        int currentReach = 0; // Current range covered by jumps
        int maxReach = 0; // Farthest we can go with current and next jumps

        for (int i = 0; i < n - 1; i++) {
            maxReach = Math.max(maxReach, i + nums[i]); // Update max reachable index

            // If we reach the end of the current jump's coverage
            if (i == currentReach) {
                jumps++; // Increment the jump counter
                currentReach = maxReach; // Update the range for the next jump

                // If we can already reach the end, break out of the loop
                if (currentReach >= n - 1) break;
            }
        }
        return jumps;
    }

// 274. H-Index
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int h = 0;
        
        for (int i = 0; i < n; i++) {
            int papers = n - i; // number of papers with citations >= citations[i]
            h = Math.max(h, Math.min(citations[i], papers));
        }
        
        return h;
    }

// 380. Insert Delete GetRandom O(1)
    private List<Integer> list;
    private Map<Integer, Integer> map;
    private Random rand;

    public void RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }
    
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int idx = map.get(val);
        int lastElement = list.get(list.size() - 1);
        
        // Swap element with the last one if it's not the same
        list.set(idx, lastElement);
        map.put(lastElement, idx);
        
        // Remove the last element
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }
    
    public int getRandom() {
        int randomIndex = rand.nextInt(list.size());
        return list.get(randomIndex);
    }

// 238. Product of Array Except Self

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];
        
        // calculate left products
        output[0] = 1; // No element on the left of the first element
        for (int i = 1; i < n; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }
        
        // calculate right products and update output array
        int rightProduct = 1; // No element on the right of the last element
        for (int i = n - 1; i >= 0; i--) {
            output[i] = output[i] * rightProduct;
            rightProduct *= nums[i];
        }
        
        return output;
    }

// 134. Gas Station
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;
        int currTank = 0;
        int startingStation = 0;

        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            totalTank += diff;
            currTank += diff;

            // If we can't reach the next station
            if (currTank < 0) {
                // Start from the next station
                startingStation = i + 1;
                currTank = 0;
            }
        }

        return totalTank >= 0 ? startingStation : -1;
    }

// 135. Candy
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        Arrays.fill(candies, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }

        return totalCandies;
    }

// 42. Trapping Rain Water
    public int trap(int[] height) {
        if (height.length <= 1) {
            return 0;
        }
        
        // set up variables
        int totalRain = 0;
        int frontPointer = 0;
        int backPointer = height.length - 1;
        int frontMax = height[frontPointer];
        int backMax = height[backPointer];

        // reset front and back pointers
        frontPointer = 0;
        backPointer = height.length - 1;

        while (frontPointer < backPointer) {
            if (frontMax < backMax) {
                frontPointer += 1;
                frontMax = Math.max(frontMax, height[frontPointer]);
                totalRain += frontMax - height[frontPointer];
            } else {
                backPointer -= 1;
                backMax = Math.max(backMax, height[backPointer]);
                totalRain += backMax - height[backPointer];
            }
        }
        return totalRain;

    }

// 13. Roman to Integer
    public static int romanToInt(String s) {
        // Map of Roman numerals
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        int prev = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = map.get(s.charAt(i));

            if (curr < prev) {
                total -= curr;
            } else {
                total += curr;
            }

            prev = curr;
        }

        return total;
    }

// 12. Integer to Roman

    public String intToRoman(int num) {
        // Define the values and corresponding Roman symbols
        int[] values =    {1000, 900, 500, 400, 100, 90,  50, 40,  10, 9,   5,  4,   1};
        String[] symbols = {"M",  "CM","D", "CD","C","XC","L","XL","X","IX","V","IV","I"};
        
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                roman.append(symbols[i]);
                num -= values[i];
            }
        }

        return roman.toString();
    }

// 58. Length of Last Word
    public int lengthOfLastWord(String s) {
        int length = 0;
        int i = s.length() - 1;
        
        // Skip trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        
        // Count the length of the last word
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }
        
        return length;
    }

// 14. Longest Common Prefix
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            // While the current string doesn't start with the prefix
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }

// 151. Reverse Words in a String
    public String reverseWords(String s) {
        // trim to remove spaces and split
        s = s.trim();
        String[] words = s.split("\\s+");

        // rebuild string in reverse
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

// 6. Zigzag Conversion
    public String convert(String s, int numRows) {
        // Edge case: if only 1 row, zigzag is same as original
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // Initialize a StringBuilder for each row
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        // Iterate over characters and place them in the correct row
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);

            // Change direction if at top or bottom row
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            currentRow += goingDown ? 1 : -1;
        }

        // Combine all rows into one result
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

// 28. Find the Index of the First Occurrence in a String
    public int strStr(String haystack, String needle) {
        // literally one liner calling a method in Java
        // return haystack.indexOf(needle);
        // building it out manually though...
        int hLen = haystack.length();
        int nLen = needle.length();

        if (nLen == 0) return 0; // per convention
        if (nLen > hLen) return -1;

        for (int i = 0; i <= hLen - nLen; i++) {
            int j = 0;
            while (j < nLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == nLen) return i;
        }

        return -1;
    }

    // 68. Text Justification
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;

        while (index < words.length) {
            // Step 1: Determine how many words fit in the current line
            int lineStart = index;
            int lineLength = words[index].length();
            index++;
            while (index < words.length && lineLength + 1 + words[index].length() <= maxWidth) {
                lineLength += 1 + words[index].length(); // add space + word
                index++;
            }

            // Step 2: Build the line
            StringBuilder line = new StringBuilder();
            int numberOfWords = index - lineStart;
            boolean isLastLine = (index == words.length);

            if (numberOfWords == 1 || isLastLine) {
                // Left-justify (only one word or last line)
                line.append(words[lineStart]);
                for (int i = lineStart + 1; i < index; i++) {
                    line.append(" ").append(words[i]);
                }
                // Fill remaining spaces
                while (line.length() < maxWidth) {
                    line.append(" ");
                }
            } else {
                // Fully justify
                int totalChars = 0;
                for (int i = lineStart; i < index; i++) {
                    totalChars += words[i].length();
                }

                int totalSpaces = maxWidth - totalChars;
                int gaps = numberOfWords - 1;
                int spacesPerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int i = lineStart; i < index; i++) {
                    line.append(words[i]);
                    if (i < index - 1) { // not last word
                        int spacesToApply = spacesPerGap + (i - lineStart < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spacesToApply; s++) {
                            line.append(" ");
                        }
                    }
                }
            }

            result.add(line.toString());
        }

        return result;
    }

    // 125. Valid Palindrome
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            // Move left pointer if not alphanumeric
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Move right pointer if not alphanumeric
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // 392. Is Subsequence
    public boolean isPalindrome2(String s) {
        // lowercase the whole string
        s = s.toLowerCase();

        // use regex to remove anything except alpha-numerics
        s = s.replaceAll("[^a-z0-9]", "");

        // set up point for end (length - 1)
        int rearIndex = (s.length() - 1);

        // string ABCBA, length = 5, last position = 4, length/2 = 5/2 = 2 per Java int rules; middle letter of odd length is at length/2; , don't want to compare the middle char
        // string ABCCBA, length/2 = 6/2 = 3, don't want to compare that char
        // iterate through each character in the string up to and including (length/2 - 1)
        for (int i = 0; i <= (s.length() - 1); i++) {
            


            // if char at i != char at (end - i), return false
            if (s.charAt(i) != s.charAt(rearIndex - i)) {
                return false;
            }
        }
        // every character matches, so return true
        return true;
    }

// 167. Two Sum II - Input Array Is Sorted
    public int[] twoSum(int[] numbers, int target) {
        int indexOne = 1;
        int indexTwo = numbers.length;

        // GOAL: find the 2 correct indexes
        while (indexOne < indexTwo) {
            // calculate the current sum
            int currentSum = numbers[indexOne - 1] + numbers[indexTwo - 1];

            // if target is met, return the indices
            if (currentSum == target) {
                return new int[]{indexOne, indexTwo};
            // else if current sum is too low, move the left pointer up
            } else if (currentSum < target) {
                indexOne++;
            // else move the right pointer down
            } else {
                indexTwo--;
            }
        }

        // return empty array if no answer is found (should be impossible per problem setup)
        return new int[]{};
    }

// 11. Container With Most Water
    public int maxArea(int[] height) {
        // Brute force: calculate the max area at each possible left/right pointer

        // put a pointer at the front and back
        int front = 0;
        int back = height.length - 1;
        int maxArea = 0;

        // for loop, iterate through n/2 (double check math for < <= >)
        while (front < back) {
            int tryArea = (back - front) * Math.min(height[front], height[back]);
            maxArea = Math.max(maxArea, tryArea);

            // if front is smaller, move up 1 spot
            if (height[front] < height[back]) {
                front += 1;                
            // else move back back 1 spot
            } else {
                back -= 1;                
            }
        }

        // return maxArea
        return maxArea;
    }

// 15. 3Sum

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for the first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Optimization: since nums is sorted
            if (nums[i] > 0) break; // no three numbers can sum to 0 if the smallest is > 0
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) break; // smallest possible sum > 0 → stop early
            if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] < 0) continue; // largest possible sum < 0 → skip this i

            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Move past duplicates
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // need a larger sum
                } else {
                    right--; // need a smaller sum
                }
            }
        }

        return result;
    }

// 209. Minimum Size Subarray Sum
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;

        for (int end = 0; end < n; end++) {
            sum += nums[end];

            while (sum >= target) {
                minLen = Math.min(minLen, end - start + 1);
                sum -= nums[start];
                start++;
            }
        }

        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }

// 3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            // If character was seen, move left pointer
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }

            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // 30. Substring with Concatenation of All Words
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        
        // handle edge cases
        if (s == null || words == null || words.length == 0) return result;
        int wordLen = words[0].length();
        int totalWords = words.length;
        int windowLen = wordLen * totalWords;
        if (s.length() < windowLen) return result;
        
        // initialize variables and word frequency map
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) freq.put(w, freq.getOrDefault(w, 0) + 1);

        // loop over possible starting offsets
        for (int offset = 0; offset < wordLen; offset++) {

            // use sliding window to track valid word sequences
            int left = offset;
            int count = 0;
            Map<String, Integer> window = new HashMap<>();

            for (int right = offset; right + wordLen <= s.length(); right += wordLen) {
                String word = s.substring(right, right + wordLen);

                // if it's a valid word, add it to the window
                if (freq.containsKey(word)) {
                    window.put(word, window.getOrDefault(word, 0) + 1);
                    count++;

                    // shrink window if word appears too many times
                    while (window.get(word) > freq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // update window and record valid starting indices
                    if (count == totalWords) {
                        result.add(left);
                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                } else {
                    // reset window if invalid word encountered
                    window.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }

        return result;
    }

// 76. Minimum Window Substring
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] need = new int[128];   // frequency map for t
        int required = t.length();   // how many chars still needed

        for (char c : t.toCharArray()) {
            need[c]++;
        }

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;               // start index of best window

        for (int right = 0; right < s.length(); right++) {
            char rc = s.charAt(right);

            // character from s helps satisfy t
            if (need[rc] > 0) {
                required--;
            }
            need[rc]--;   // include rc in the window

            // when fully satisfied, try shrinking from left
            while (required == 0) {
                // update best window
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char lc = s.charAt(left);
                need[lc]++;  // remove lc from the window

                // if removing lc makes window invalid
                if (need[lc] > 0) {
                    required++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    // 36. Valid Sudoku
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];

                if (ch == '.') continue;

                int num = ch - '1'; // convert '1'-'9' → 0-8
                int boxIndex = (r / 3) * 3 + (c / 3);

                if (rows[r][num] || cols[c][num] || boxes[boxIndex][num]) {
                    return false; // duplicate found
                }

                rows[r][num] = cols[c][num] = boxes[boxIndex][num] = true;
            }
        }

        return true;
    }

    // 54. Spiral Matrix
    public List<Integer> spiralOrder(int[][] matrix) {
                List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse from left to right
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // Traverse from top to bottom
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            if (top <= bottom) {
                // Traverse from right to left
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if (left <= right) {
                // Traverse from bottom to top
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }

// 48. Rotate Image
    public void rotate(int[][] matrix) {
              int n = matrix.length;

        // Step 1: Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Swap matrix[i][j] with matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                // Swap matrix[i][j] with matrix[i][n - j - 1]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }  
    }

// 73. Set Matrix Zeroes
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Check if the first row needs to be zero
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Check if the first column needs to be zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Mark rows and columns to be zero
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // Mark the first cell of the row
                    matrix[0][j] = 0; // Mark the first cell of the column
                }
            }
        }

        // Set rows to zero based on markers
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Set columns to zero based on markers
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Handle the first row
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Handle the first column
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    // 289. Game of Life
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int[] dirs = {-1, 0, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = 0;

                // Count live neighbors (use Math.abs to read original states)
                for (int dx : dirs) {
                    for (int dy : dirs) {
                        if (dx == 0 && dy == 0) continue;
                        int x = i + dx, y = j + dy;
                        if (x >= 0 && x < m && y >= 0 && y < n) {
                            if (Math.abs(board[x][y]) == 1) {
                                liveNeighbors++;
                            }
                        }
                    }
                }

                // Rule applications using encoded states
                if (board[i][j] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        board[i][j] = -1; // live -> dead
                    }
                } else { // board[i][j] == 0
                    if (liveNeighbors == 3) {
                        board[i][j] = 2; // dead -> live
                    }
                }
            }
        }

        // Final normalization
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) board[i][j] = 0;
                else if (board[i][j] == 2) board[i][j] = 1;
            }
        }
    }

// 383. Ransom Note
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];

        // Count letters in magazine
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }

        // Try to use letters for ransomNote
        for (char c : ransomNote.toCharArray()) {
            if (--count[c - 'a'] < 0) {
                return false;  // used more than available
            }
        }

        return true;
    }

// 205. Isomorphic Strings
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] mapST = new int[256];
        int[] mapTS = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // If already mapped, check consistency
            if (mapST[c1] != 0 && mapST[c1] != c2) return false;
            if (mapTS[c2] != 0 && mapTS[c2] != c1) return false;

            // Otherwise, create mapping
            mapST[c1] = c2;
            mapTS[c2] = c1;
        }

        return true;
    }


// 290. Word Pattern
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        
        if (pattern.length() != words.length) {
            return false;
        }
        
        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();
        
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            
            if (charToWord.containsKey(c)) {
                // mapping must match the existing one
                if (!charToWord.get(c).equals(w)) {
                    return false;
                }
            } else {
                // if word is already mapped to another character → fail
                if (wordToChar.containsKey(w)) {
                    return false;
                }
                // add the new mapping both ways
                charToWord.put(c, w);
                wordToChar.put(w, c);
            }
        }
        
        return true;
    }

// 242. Valid Anagram
    public boolean isAnagram(String s, String t) {
        // if the strings are different lengths, return false
        if (s.length() != t.length()) {
                System.out.println("different lengths");
                return false;
        }

        // convert each string to a character array
        char[] s_array = new char[s.length()];
        char[] t_array = new char[t.length()];

        for (int i=0; i<s.length(); i++) {
            s_array[i] = s.charAt(i);
        }

        for (int i=0; i<t.length(); i++) {
            t_array[i] = t.charAt(i);
        }

        // sort each character array
        Arrays.sort(s_array); 
        Arrays.sort(t_array); 

        // find the maximum length
        int max = 0;
        if (s.length() >= t.length()) {
            max = s.length();
        } else {
            max = t.length();
        }

        // iterate through the characters and compare if they're the same
        for (int i = 0; i <= (max - 1); i++) {
            // System.out.println("s entry at " + i + " is: " + s_array[i]);
            // System.out.println("t entry at " + i + " is: " + t_array[i]);
            if (s_array[i] != t_array[i]) {
                return false;
            }
        }
        return true;
    }

// 49. Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        
    }
}