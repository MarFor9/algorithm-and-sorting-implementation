package main_test

import (
	"fmt"
	"sort"
	"testing"
)

/*
Given list of unordered non-distinct int numbers, find the Kth largest number
input: List<Integer> nums; int k;
output: return k-th largest number;

example:
nums = 1, 3, 2, 6, 7, 6, 2, 5, 2, 10
k = 2

answer = 7
*/

func Test_FindKthLargestNumber(t *testing.T) {
	type testConfig struct {
		nums     []int
		k        int
		expected int
	}

	for i, tc := range []testConfig{
		{
			nums:     []int{1, 3, 2, 6, 7, 6, 2, 5, 2, 10},
			k:        2,
			expected: 7,
		}, {
			nums:     []int{11, 8, 3, 5, 5, 3, 2, 5, 1},
			k:        1,
			expected: 11,
		},
		{
			nums:     []int{1, 5, 5, 6, 2, 3, 5, 7, 1, 8},
			k:        3,
			expected: 6,
		},
	} {
		t.Run(fmt.Sprintf("Case:%d", i), func(t *testing.T) {
			got := findKthLargestNumber(tc.nums, tc.k)
			if got != tc.expected {
				t.Errorf("expectedIndex: %d but got: %d", tc.expected, got)
			}
		})
	}
}

/*
Find number in a slice
*/
func Test_FindNumberInSlice(t *testing.T) {
	type testConfig struct {
		nums []int
		find int
	}

	for i, tc := range []testConfig{
		{
			nums: []int{1, 3, 2, 6, 7, 6, 2, 5, 2, 10},
			find: 6,
		}, {
			nums: []int{11, 8, 3, 5, 5, 3, 2, 5, 1},
			find: 8,
		},
		{
			nums: []int{1, 5, 5, 6, 2, 3, 5, 7, 1, 8},
			find: 5,
		},
	} {
		t.Run(fmt.Sprintf("Case_v_0_:%d", i), func(t *testing.T) {
			sorted := quickSort(tc.nums)
			index := binarySearch(sorted, tc.find)
			got := sorted[index]

			if got != tc.find {
				t.Errorf("expected value: %d but got value: %d", tc.find, got)
			}
		})
		t.Run(fmt.Sprintf("Case_v_1_:%d", i), func(t *testing.T) {
			sort.Slice(tc.nums, func(i, j int) bool {
				return tc.nums[i] < tc.nums[j]
			})
			index := binarySearch(tc.nums, tc.find)
			got := tc.nums[index]

			if got != tc.find {
				t.Errorf("expected value: %d but got value: %d", tc.find, got)
			}
		})
	}
}

func findKthLargestNumber(nums []int, k int) int {
	sortedList := quickSort(nums)
	return sortedList[len(sortedList)-k]
}

// Best O(n log(n)), average O(n log(n)), worst O(n^2)
// ASC
// If needed DESC change only condition in if statement
func quickSort(nums []int) []int {
	if len(nums) < 2 {
		return nums
	}
	pivot := nums[0]
	var left, right []int

	for i := 1; i < len(nums); i++ {
		if nums[i] <= pivot {
			left = append(left, nums[i])
		} else {
			right = append(right, nums[i])
		}
	}
	return append(append(quickSort(left), pivot), quickSort(right)...)
}

// O(log(n))
func binarySearch(nums []int, key int) int {
	lowIndex := 0
	highIndex := len(nums) - 1

	for lowIndex <= highIndex {
		midIndex := lowIndex + (highIndex-lowIndex)/2
		midVal := nums[midIndex]

		if midVal < key {
			lowIndex = midIndex + 1
		} else if midVal > key {
			highIndex = midIndex - 1
		} else {
			return midIndex
		}
	}
	return -1
}
