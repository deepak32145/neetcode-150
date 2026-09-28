package neetcode150.problems.p208;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 208. Implement Trie (Prefix Tree)  [Medium]  -  Tries
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * Problem:
 *   Implement insert(word), search(word) (exact word) and startsWith(prefix).
 *
 * Example:
 *   insert("apple"); search("apple") -> true; search("app") -> false;
 *   startsWith("app") -> true; insert("app"); search("app") -> true
 *
 * Solution - node with 26 children:
 *   insert walks / creates child nodes for each letter and marks the last one isWord.
 *   getLast walks the letters and returns the final node (or null). search needs that node and
 *   isWord; startsWith only needs the node.
 *   Time: O(L) per operation   Space: O(total inserted characters * 26)
 */
class Trie {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        print(trie.search("apple")); // true
        print(trie.search("app")); // false
        print(trie.startsWith("app")); // true
        trie.insert("app");
        print(trie.search("app")); // true
    }

    Node root;

    public Trie() {
        root = new Node('\0'); //dummy node
    }

    public void insert(String word) {
        Node curr = root;
        for (char x : word.toCharArray()) {
            if (curr.children[x - 'a'] == null) {
                curr.children[x - 'a'] = new Node(x);
            }
            curr = curr.children[x - 'a'];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        Node res = getLast(word);
        return (res != null && res.isWord);
    }

    //helper method
    public Node getLast(String word) {
        Node curr = root;
        for (char x : word.toCharArray()) {
            if (curr.children[x - 'a'] == null) {
                return null;
            }

            curr = curr.children[x - 'a'];
        }
        return curr;
    }

    public boolean startsWith(String prefix) {
        Node res = getLast(prefix);
        if (res == null) return false;
        return true;
    }

    class Node {

        private char value;
        private boolean isWord;
        private Node[] children;

        public Node(char val) {
            this.value = val;
            this.isWord = false;
            this.children = new Node[26];
        }
    }
}
