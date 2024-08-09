package org.github;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
/*
Given list of unordered non-distinct int numbers, find the Kth largest number
input: List<Integer> nums; int k;
output: return k-th largest number;

example:
nums = 1, 3, 2, 6, 7, 6, 2, 5, 2, 10
k = 2

answer = 7
*/
public class Solution1Test {

    @Test
    public void findKthLargestNumber() {
        List<Integer> nums = List.of(5, 4, 3, 2, 1, 10, 9, 8, 7, 6);
        int k = 2;
        int got = Solution1.findKthLargestNumber(nums, k);
        int want = 9;
        Assertions.assertEquals(got, want);

        k = 1;
        got = Solution1.findKthLargestNumber(nums, k);
        want = 10;
        Assertions.assertEquals(got, want);
    }

    @Test
    public void quickSort() {
        List<Integer> data = List.of(5, 6, 4, 3, 2, 7, 9, 8, 10, 1);

        List<Integer> got = Solution1.quickSort(data);
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Assertions.assertEquals(got, expected, String.format("expected: %s but got: %s", expected, got));
    }

    @Test
    public void binarySearch() {
        List<Integer> data = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int expectedIndex = 6;
        int gotIndex = Solution1.binarySearch(data, 7);

        Assertions.assertEquals(expectedIndex, gotIndex, String.format("expected: %d but got: %d", expectedIndex, gotIndex));

    }

}
