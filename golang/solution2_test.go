package main_test

import (
	"fmt"
	"testing"
)

/*
Given a string s with the value 'atlas',
the source string is formed by repeating the string s multiple
times up to a length of 1 billion characters.
Your task is to count the number of occurrences of the character 'a'
in the source string.
*/
func Test_CountOccurrencesChar(t *testing.T) {
	type testCase struct {
		data             string
		targetChar       rune
		lengthToConsider int
		expected         int
	}

	for i, tc := range []testCase{
		{"atlas", 'a', 101, 41,},
		{"atlas", 't', 101, 20,},
		{"atlas", 'l', 101, 20,},
		{"atlas", 's', 101, 20,},
	} {
		t.Run(fmt.Sprintf("%d: For string: '%s' and target char: '%c', with length: %d, expected count is: %d",
			i, tc.data, tc.targetChar, tc.lengthToConsider, tc.expected), func(t *testing.T) {
			got := countCharacterOccurrences(tc.data, tc.targetChar, tc.lengthToConsider)
			if got != tc.expected {
				t.Errorf("expected: %d but got: %d", tc.expected, got)
			}
		})
	}
}

func countCharacterOccurrences(str string, targetChar rune, lengthToConsider int) int {
	count := 0
	for _, char := range str {
		if targetChar == char {
			count++
		}
	}

	fullRepeats := lengthToConsider / len(str)
	reminder := lengthToConsider % len(str)

	total := fullRepeats * count
	for i := 0; i < reminder; i++ {
		if targetChar == rune(str[i]) {
			total++
		}
	}
	return total
}
