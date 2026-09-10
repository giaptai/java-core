/*
Given a string s, reverse only all the vowels in the string and return it.
The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 

Example 1:
Input: s = "IceCreAm"
Output: "AceCreIm"
Explanation:
The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".

Example 2:
Input: s = "leetcode"
Output: "leotcede"

Constraints:
    1 <= s.length <= 3 * 105
    s consist of printable ASCII characters.
*/

function isVowel(c) {
    return (c === 'a' || c === 'e' || c === 'i' || c === 'o' || c === 'u' || c === 'A' || c === 'E' || c === 'I' || c === 'O'
        || c === 'U');
}

function reverseVowels(s) {
    let i = 0;
    let j = s.length - 1;
    let chars = [...s];
    while (i < j) {
        while (i < j && !isVowel(chars[i])) {
            i++;
        }
        while (i < j && !isVowel(chars[j])) {
            j--;
        }
        const temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        i++;
        j--;
    }
    return chars.join("");
}
