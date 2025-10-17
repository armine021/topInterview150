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
    public int removeDuplicates(int[] nums) {
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
}

// 380. Insert Delete GetRandom O(1)
    private List<Integer> list;
    private Map<Integer, Integer> map;
    private Random rand;

    public RandomizedSet() {
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
        
    }

}

