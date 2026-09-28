package neetcode150.problems.p211;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 211. Design Add and Search Words Data Structure  [Medium]  -  Tries
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/
 *
 * Problem:
 *   Support addWord(word) and search(word), where '.' in the search matches any letter.
 *
 * Example:
 *   addWord("bad"); addWord("dad"); addWord("mad");
 *   search("pad") -> false; search("bad") -> true; search(".ad") -> true; search("b..") -> true
 *
 * Solution - trie + DFS on wildcards:
 *   addWord is a normal trie insert. search walks the trie; on '.', it tries every existing
 *   child recursively for the rest of the word.
 *   Time: addWord O(L); search O(L) without dots, up to O(26^L) with dots   Space: O(total chars)
 */
/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

class WordDictionary {
    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        print(dict.search("pad")); // false
        print(dict.search("bad")); // true
        print(dict.search(".ad")); // true
        print(dict.search("b..")); // true
    }

    Node root;

    private class Node {

        char value;
        boolean isWord;
        Node[] children;

        public Node(char value) {
            this.value = value;
            isWord = false;
            children = new Node[26];
        }
    }

    public WordDictionary() {
        root = new Node('\0');
    }

    public void addWord(String word) {
        Node curr = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new Node(ch);
            }

            curr = curr.children[ch - 'a'];
        }

        curr.isWord = true;
    }

    // TC O(m^2)
    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }

    private boolean searchHelper(String word, Node curr, int index) {
        for (int i = index; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch == '.') {
                for (Node temp : curr.children) {
                    if (temp != null && searchHelper(word, temp, i + 1)) {
                        return true;
                    }
                }

                return false;
            }

            if (curr.children[ch - 'a'] == null) {
                return false;
            }

            curr = curr.children[ch - 'a'];
        }

        return curr.isWord;
    }
}
