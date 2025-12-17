# 📝 LEETCODE IDEAS CHEAT SHEET

**Tổng hợp ý tưởng giải quyết 21 bài LeetCode đã làm**

*Tạo ngày: 18/12/2025 - 6:15 AM*
*Phỏng vấn: 18/12/2025 - 9:00 AM*

---

## 🎯 PHÂN LOẠI THEO PATTERN

### 1️⃣ HASH TABLE / HASH MAP (7 bài)

#### **Contains Duplicate**
```
Ý tưởng: Dùng HashSet kiểm tra duplicate
- Duyệt qua array
- Nếu element đã có trong Set → return true
- Không → add vào Set
Time: O(n), Space: O(n)
```

#### **Valid Anagram**
```
Ý tưởng: Đếm tần suất ký tự bằng HashMap
- 2 string có length khác → false
- Đếm tần suất mỗi ký tự trong s
- Trừ đi tần suất mỗi ký tự trong t
- Nếu tất cả = 0 → true
Time: O(n), Space: O(26) = O(1) nếu chỉ lowercase
```

#### **Two Sum**
```
Ý tưởng: HashMap lưu (value → index)
- Duyệt array, với mỗi nums[i]:
  - Tính complement = target - nums[i]
  - Nếu complement có trong map → return [map.get(complement), i]
  - Không → map.put(nums[i], i)
Time: O(n), Space: O(n)
```

#### **Group Anagrams**
```
Ý tưởng: HashMap với key = sorted string
- Map<String, List<String>>
- Với mỗi string: sort làm key
- Các anagram cùng key → group lại
- VD: "eat", "tea", "ate" → key = "aet"
Time: O(n * k log k), k = avg length
```

#### **Top K Frequent Elements**
```
Ý tưởng: HashMap + PriorityQueue (Heap)
1. HashMap đếm tần suất: Map<Integer, Integer>
2. PriorityQueue sắp xếp theo frequency (max heap)
3. Poll K elements từ heap
Time: O(n log n), Space: O(n)
```

#### **Find First Unique Character**
```
Ý tưởng: HashMap đếm tần suất
1. Đếm frequency mỗi ký tự
2. Duyệt lại string, tìm ký tự đầu tiên có freq = 1
Time: O(n), Space: O(26) = O(1)
```

#### **Encode and Decode Strings**
```
Ý tưởng: Length-prefix encoding
Encode: "4#word5#hello" (length + "#" + word)
Decode: Read length → skip "#" → read word
Time: O(n), Space: O(1)
```

---

### 2️⃣ TWO POINTERS (7 bài)

#### **Valid Palindrome**
```
Ý tưởng: 2 pointers từ 2 đầu
- left = 0, right = length - 1
- Skip non-alphanumeric
- So sánh toLowerCase
- left++ và right--
Time: O(n), Space: O(1)
```

#### **Two Sum II (Sorted Array)**
```
Ý tưởng: 2 pointers (array đã sorted)
- left = 0, right = length - 1
- sum = arr[left] + arr[right]
- sum == target → return
- sum < target → left++
- sum > target → right--
Time: O(n), Space: O(1)
```

#### **Three Sum**
```
Ý tưởng: Sort + Two Pointers
1. Sort array
2. Fix i, tìm 2 số còn lại bằng Two Pointers
3. Skip duplicates: if nums[i] == nums[i-1] continue
Time: O(n²), Space: O(1) không kể output
```

#### **Container With Most Water**
```
Ý tưởng: 2 pointers greedy
- left = 0, right = n-1
- area = min(height[left], height[right]) * (right - left)
- Di chuyển pointer có chiều cao NHỎ hơn
Time: O(n), Space: O(1)
```

#### **Move Zeroes**
```
Ý tưởng: 2 pointers (slow-fast)
- slow = 0 (vị trí để đặt non-zero)
- fast duyệt array
- Nếu nums[fast] != 0 → swap(nums[slow++], nums[fast])
Time: O(n), Space: O(1)
```

#### **Reverse String**
```
Ý tưởng: 2 pointers swap
- left = 0, right = length - 1
- Swap chars[left] và chars[right]
- left++, right--
Time: O(n), Space: O(1)
```

#### **Merge Two Sorted Lists**
```
Ý tưởng: 2 pointers + dummy node
- Tạo dummy node
- Compare l1 và l2, chọn nhỏ hơn
- Di chuyển pointer của list được chọn
Time: O(n + m), Space: O(1)
```

---

### 3️⃣ ARRAY / MATH (5 bài)

#### **Product of Array Except Self**
```
Ý tưởng: Prefix + Suffix product
1. result[i] = product của tất cả bên TRÁI i
2. Nhân với product của tất cả bên PHẢI i
- Pass 1 (left to right): prefix product
- Pass 2 (right to left): suffix product
Time: O(n), Space: O(1) không kể output
```

#### **Missing Number**
```
Ý tưởng: Sum công thức hoặc XOR
Cách 1: expectedSum = n*(n+1)/2, return expectedSum - actualSum
Cách 2: XOR tất cả index và values (duplicate bị cancel)
Time: O(n), Space: O(1)
```

#### **FizzBuzz**
```
Ý tưởng: Check chia hết
- i % 15 == 0 → "FizzBuzz"
- i % 3 == 0 → "Fizz"
- i % 5 == 0 → "Buzz"
- Else → String.valueOf(i)
Time: O(n), Space: O(1)
```

#### **Best Time to Buy and Sell Stock**
```
Ý tưởng: Track min price và max profit
- minPrice = Integer.MAX_VALUE
- Duyệt qua prices:
  - Update minPrice
  - Update maxProfit = max(maxProfit, price - minPrice)
Time: O(n), Space: O(1)
```

#### **Valid Sudoku**
```
Ý tưởng: HashSet check duplicate
- Check 9 rows: mỗi row dùng 1 Set
- Check 9 columns: mỗi column dùng 1 Set
- Check 9 sub-boxes: mỗi box dùng 1 Set
- Box index: (row/3, col/3)
Time: O(81) = O(1), Space: O(81) = O(1)
```

---

### 4️⃣ ADVANCED (2 bài)

#### **Longest Consecutive Sequence**
```
Ý tưởng: HashSet + check sequence start
1. Bỏ tất cả vào Set
2. Với mỗi num:
   - Nếu num-1 KHÔNG có trong set → đây là start của sequence
   - Đếm length: num+1, num+2,... có trong set
3. Track max length
Time: O(n), Space: O(n)
```

#### **Trapping Rain Water**
```
Ý tưởng: 2 pointers + track max height
- left = 0, right = n-1
- leftMax = 0, rightMax = 0
- Di chuyển pointer có height NHỎ hơn
- Water trapped = min(leftMax, rightMax) - height[i]
Time: O(n), Space: O(1)
```

---

## 📊 THỐNG KÊ PATTERNS

| Pattern | Số bài | Độ khó |
|---------|--------|---------|
| Hash Table/Map | 7 | ⭐⭐ |
| Two Pointers | 7 | ⭐⭐ |
| Array/Math | 5 | ⭐ |
| Advanced | 2 | ⭐⭐⭐ |

---

## 🎯 TOP 5 BÀI QUAN TRỌNG NHẤT (Hay ra test)

### 1. **Two Sum** ⭐⭐⭐
```java
Map<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int complement = target - nums[i];
    if (map.containsKey(complement)) {
        return new int[]{map.get(complement), i};
    }
    map.put(nums[i], i);
}
```
**Ý tưởng:** HashMap lưu (value → index), tìm complement

---

### 2. **Valid Anagram** ⭐⭐⭐
```java
if (s.length() != t.length()) return false;
Map<Character, Integer> count = new HashMap<>();
for (char c : s.toCharArray()) {
    count.put(c, count.getOrDefault(c, 0) + 1);
}
for (char c : t.toCharArray()) {
    count.put(c, count.getOrDefault(c, 0) - 1);
}
for (int val : count.values()) {
    if (val != 0) return false;
}
return true;
```
**Ý tưởng:** Đếm frequency, so sánh

---

### 3. **Valid Palindrome** ⭐⭐
```java
int left = 0, right = s.length() - 1;
while (left < right) {
    while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
    while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
    if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
        return false;
    }
    left++;
    right--;
}
return true;
```
**Ý tưởng:** Two pointers, skip non-alphanumeric

---

### 4. **Best Time to Buy and Sell Stock** ⭐⭐
```java
int minPrice = Integer.MAX_VALUE;
int maxProfit = 0;
for (int price : prices) {
    minPrice = Math.min(minPrice, price);
    maxProfit = Math.max(maxProfit, price - minPrice);
}
return maxProfit;
```
**Ý tưởng:** Track min price và max profit

---

### 5. **Move Zeroes** ⭐⭐
```java
int slow = 0;
for (int fast = 0; fast < nums.length; fast++) {
    if (nums[fast] != 0) {
        int temp = nums[slow];
        nums[slow] = nums[fast];
        nums[fast] = temp;
        slow++;
    }
}
```
**Ý tưởng:** Two pointers (slow-fast), swap non-zero về đầu

---

## 🧠 GHI NHỚ NHANH THEO PATTERN

### HashMap Pattern:
```
Contains Duplicate → HashSet
Valid Anagram → HashMap count frequency
Two Sum → HashMap (value → index)
Group Anagrams → HashMap (sorted string → list)
Top K Frequent → HashMap + PriorityQueue
```

### Two Pointers Pattern:
```
Valid Palindrome → left/right từ 2 đầu
Two Sum II → left/right (sorted array)
Three Sum → fix i + two pointers
Container Water → left/right greedy (move smaller)
Move Zeroes → slow/fast (slow = vị trí đặt non-zero)
```

### Array/Math Pattern:
```
Product Except Self → prefix * suffix
Missing Number → sum formula hoặc XOR
Best Time Stock → track min + max profit
Valid Sudoku → HashSet check duplicate
```

---

## ⚡ TRICK GHI NHỚ

1. **Tìm duplicate/unique** → HashSet/HashMap
2. **Target sum** → HashMap (Two Sum) hoặc Two Pointers (sorted)
3. **Palindrome/Reverse** → Two Pointers
4. **K elements** → Heap/PriorityQueue
5. **In-place modification** → Two Pointers (slow-fast)
6. **Frequency counting** → HashMap
7. **Consecutive sequence** → HashSet
8. **Max area/water** → Two Pointers greedy

---

## 🎯 CHECKLIST ÔN TẬP (Trước test 30 phút)

- [ ] Đọc lại **TOP 5 bài quan trọng nhất**
- [ ] Nhớ **pattern** của từng loại bài
- [ ] Nhớ **Time/Space complexity**
- [ ] Tự tin giải thích **ý tưởng bằng lời**

---

**GOOD LUCK! 🚀**

*Tạo ngày: 18/12/2025 - 6:15 AM*
*Test: 18/12/2025 - 9:00 AM*
