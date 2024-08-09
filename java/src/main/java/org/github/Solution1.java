package org.github;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Given list of unordered non-distinct int numbers, find the Kth largest number
input: List<Integer> nums; int n;
output: return n-th largest number;

example:
nums = 1, 3, 2, 6, 7, 6, 2, 5, 2, 10
n = 2

answer = 7
 */
public class Solution1 {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 3, 2, 6, 7, 6, 2, 5, 2, 10);
        int result = findKthLargestNumber(nums, 2);
        System.out.println("Result: " + result);

        List<Integer> sortedAsc = nums.stream().sorted().toList(); // natural order
        List<Integer> sortedDesc = nums.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println(sortedDesc);
        System.out.println(sortedAsc);

        List<Integer> sortedQuickList = quickSort(nums);
        int key = 5;
        int index = binarySearch(sortedQuickList, key);
        if (index == -1) {
            System.out.println("Index not found");
        } else {
            System.out.printf("Index for the value: %d found at index: %d \n", key, index);
            System.out.printf("Value under the index: %d \n", sortedQuickList.get(index));
        }
    }

    // O(log(n))
    public static int binarySearch(List<Integer> nums, int key) {
        int low = 0;
        int high = nums.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = nums.get(mid);

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid -1;
            } else{
                return mid;
            }
        }
        return -1;
    }

    public static int findKthLargestNumber(List<Integer> nums, int k) {
        List<Integer> sortedList = quickSort(nums);
        System.out.println(sortedList);
        return sortedList.get(nums.size() - k);
    }

    // Best O(n log(n)), average O(n log(n)), worst O(n^2)
    // ASC
    // If needed DESC change only condition in if statement
    public static List<Integer> quickSort(List<Integer> nums) {
        if (nums.size() < 2) {
            return nums;
        }
        int pivot = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) <= pivot) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }
        // Recursively sort the left and right sublists
        List<Integer> sortedLeft = quickSort(left);
        List<Integer> sortedRight = quickSort(right);

        // Combine the sorted lists with the pivot in between
        List<Integer> sortedList = new ArrayList<>(sortedLeft);
        sortedList.add(pivot);
        sortedList.addAll(sortedRight);

        return sortedList;
    }
}
