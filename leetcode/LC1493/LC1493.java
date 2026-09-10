package LC1493;

/*

Given a binary array nums, you should delete one element from it.
Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

Example 1:
Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.

Example 2:
Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].

Example 3:
Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.
 
Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.

abcdefghijklmnopqrstuvwxyz
*/

public class LC1493 {
    public static int longestContinuousSubstring(String s) {
        int c = 1;
        int maxLen = 1;
        for (int i = 1; i < s.length(); i++) {
            char expectChar = (char) (s.charAt(i - 1) + 1);
            if (s.charAt(i) == expectChar){
                c++;
                maxLen = Math.max(maxLen, c);
            }else{
                c = 1;
            }
        }
        return maxLen;
    }

    public static int maxPower(String s) {
        int count = 0;
        int maxLen = 0;
        char curr = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == curr) {
                count++;
                maxLen = Math.max(maxLen, count);
            } else {
                curr = s.charAt(i);
                count = 1;
            }
        }
        return maxLen;
    }

    public static int longestSubarray(int[] nums) {
        int i = 0;
        int j = 0;
        int count = 0;
        int maxLen = 0;
        while (j < nums.length) {
            if (nums[j] == 0) {
                count++;
            }
            while (count > 1) {
                if (nums[i] == 0) {
                    count--;
                }
                i++;
            }
            maxLen = Math.max(maxLen, j - i);
            j++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        // System.out.println(longestSubarray(new int[]{1,1,0,1}));
        // System.out.println(longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}));
        // System.out.println(longestSubarray(new int[] { 1, 1, 1 }));
        // System.out.println(findMaxConsecutiveOnes(new int[] { 1, 1, 0, 1, 1, 1 }));
        // System.out.println(findMaxConsecutiveOnes(new int[] { 0 }));
        // System.out.println(maxPower("abbcccddddeeeeedcba"));
        // System.out.println(longestContinuousSubstring("abacaba"));
        System.out.println(longestContinuousSubstring(
                "znyqwiofvtjdzuvoxvjptotayadazyunotpgojjsdmasflbvwrcgqoxxygtfwawsujdxkjcnakwocvgypfzpkacnnhhavzwqukyeptnkfieguxdosqufoazzjihbvnmkftqobtgoxrgkuzdeeqlxjcesloztaewugpnlcmtbcnpgqjzryrhosseajogholevjncyxpcnerjrglncfgvfeekdafmtnecylvrmyinfztxbwamslrklrozutodblyofzolvkjafibuxucfapzdkwaooecbemgimdpfnujyjqajshaudugkqpijoikwdtivlwjikfawuihynjkfcgsryvcwxxlwzokdflohqubonwlljukorwbbzfxtbzdeeawltmkmhmqlfamcxuxtahbchdpmyzinaqekikjjkdfjtojscjgmxzuwrwbnsocufvgdbkqcvanqhyxbpjlrxkusmfsbtadhtxltpeqyhaduhvpxixhggcigmvbcwgzkzcgmwoxrflzupkzmlbtxkosbetxoilaaxpwqewufjqxowcrrorvgnjoyhtzfnbgxamdwgrtmbgknmviufufvwvdhdzvnalwycfwlbkvtvdlseunvpvkgffticrjmpnylgupxcorzfbddxlqhatdtlqnuoyglqlytqwbslwruopfzpxpamnrhuxeqcsxkuqubpkfrguknnyfrppusvneziekwibfvnpvnebokrjkvwbdrlvcsclfulhjsnumrbmgaqgenkfxahyhtgkejbjiqjatcjygnwtcrxnhieijbucxpufdulqtftvjnbygdqeiacthpqwhmckjqqsmlqaztwfxdjzabjvqautfcoswtrcpyiywnhkkzemxwxrchdircmrpmwcweobbfsgphnmwuiupxvicoirmypdcoccgjknueyruqingwpvcyqdriefvuzjmfueoryletmlzmemfklpvaamizmavtgofquimnzvnbxcnlogzbjubukrxntaynencyalxezcszbpfoskxzbjvrscwyvhngmmszydfimkcsappzfatjzbefwkegqorybbmfszdrrlzjdjzlfvbyjkczyzukvimqamwpvnmwqjzyodfjpzmzznfutdfwybsxnlsbecarbjnboezgwstnmuxzrwnmurajepvxggqjtdotdisfwykcquvlmdevwhcdaydmqatifwjtmpltiyurppqppvsvvfxtdajttjziduelgtzixppqbnzoaobbyszqzpuwamvclwldhuwunybfceonngzjfqpupdsmuquwztrmzvmblozvaecycblxzmyhotucxawnufmlmrrfeqsgyglpyzzsuzyjxukqolxcfhrayrwxtmtxooeqqiorqilqwqkgvwvecfesldxvwnltlwckmrnuginydvgrjhnrulmhzulgtglwacjbcxqx"));

    }
}