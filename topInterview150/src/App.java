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

}

