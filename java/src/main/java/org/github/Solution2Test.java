package org.github;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

/*
Given a string s with the value 'atlas',
the source string is formed by repeating the string s multiple
times up to a length of 1 billion characters.
Your task is to count the number of occurrences of the character 'a'
in the source string.
*/
public class Solution2Test {

    @Test
    public void BillionCharacters() {
        int targetCharsInString = 1_000_000_000;
        String data = "atlas";
        String billionString = multiToBillionCharacters(data, targetCharsInString);
        Assertions.assertEquals(targetCharsInString, billionString.length());
    }

    @Test
    void CountOccurrencesOfString() {
        int targetCharsInString = 1_000_000_000;
        String data = "atlas";
        String billionString = multiToBillionCharacters(data, targetCharsInString);
        int occurrenceOfChar = getCharOccurrenceFromString(billionString, 'a');

        Assertions.assertEquals(400000000, occurrenceOfChar, String.format("expected occurrence: %d, but got: %d", 8, occurrenceOfChar));
    }

    @ParameterizedTest(name = "{index}: For string ''{0}'' and target char ''{1}'' with length {2}, expected count is {3}")
    @CsvSource({
            "'atlas', 'a', 101, 41",
            "'atlas', 't', 101, 20",
            "'atlas', 'l', 101, 20",
            "'atlas', 's', 101, 20",
    })
    void GetOccurrenceOfChars(String data, char targetChar, int lengthToConsider, int expected) {
        int result = countCharacterOccurrences(data, targetChar, lengthToConsider);
        System.out.println("Number of occurrences of '" + targetChar + "': " + result);
        Assertions.assertEquals(expected, result);

    }

    private int countCharacterOccurrences(String data, char targetChar, int lengthToConsider) {
        int count = 0;
        for (char c : data.toCharArray()) {
            if (c == targetChar) {
                count++;
            }
        }

        // iterate over string to get the full repeats of the string
        int fullRepeats = lengthToConsider / data.length();

        // check if in lengthToConsider are remainders
        int remainder = lengthToConsider % data.length();

        int totalCount = fullRepeats * count;

        for (int i = 0; i < remainder; i++) {
            if (data.charAt(i) == targetChar) {
                totalCount++;
            }
        }
        return totalCount;
    }

    private String multiToBillionCharacters(String data, int targetCharsInString) {
        int lenStr = data.length();
        int multi = targetCharsInString / lenStr;
        return data.repeat(multi);
    }

    private int getCharOccurrenceFromString(String str, char sing) {
        HashMap<Character, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            Integer value = resultMap.get(currentChar);

            if (value == null) {
                resultMap.put(currentChar, 1);
            } else {
                resultMap.put(currentChar, value + 1);
            }
        }
        return resultMap.getOrDefault(sing, 0);
    }
}
