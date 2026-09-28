# NeetCode 150 (Java)

Java solutions for every problem in the official [NeetCode 150](https://leetcode.com/problem-list/plakya4j/) list on LeetCode.

Solutions are adapted from [NeetCode’s open-source implementations](https://github.com/neetcode-gh/leetcode) (same approaches shown on [NeetCode.io](https://neetcode.io)).

## Layout

```
src/main/java/neetcode150/
  01-arrays-hashing/
  02-two-pointers/
  03-stack/
  ...
  16-bit-manipulation/
```

Each file is named `{leetcodeNumber}_{slug}.java` and contains a `Solution` class ready to paste into LeetCode’s Java editor.

## How to submit on LeetCode

1. Open the matching file under the topic folder.
2. Copy the entire file (or everything inside `class Solution { ... }` if LeetCode already provides the method signature).
3. Submit on the problem page.

## Regenerate from upstream

Requires a shallow clone of `neetcode-gh/leetcode` in `_upstream/`:

```powershell
git clone --depth 1 https://github.com/neetcode-gh/leetcode.git _upstream
powershell -ExecutionPolicy Bypass -File scripts/setup_neetcode150.ps1
powershell -ExecutionPolicy Bypass -File scripts/strip_packages.ps1
```

## Problem index

| Category | Count |
|----------|------:|
| Arrays & Hashing | 9 |
| Two Pointers | 5 |
| Stack | 7 |
| Sliding Window | 6 |
| Binary Search | 7 |
| Linked List | 11 |
| Trees | 15 |
| Tries | 3 |
| Heap | 7 |
| Backtracking | 9 |
| Graphs | 19 |
| Dynamic Programming | 23 |
| Intervals | 6 |
| Greedy | 8 |
| Math & Geometry | 8 |
| Bit Manipulation | 7 |
| **Total** | **150** |

Some list entries are LeetCode Premium (e.g. Encode and Decode Strings, Walls and Gates); implementations are included for completeness.
