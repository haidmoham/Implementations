/**
 * Created by mohammad on 8/21/16.
 */

import java.util.*;

public class Tries {
    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }
    private final TrieNode root;
    public Trie(){
        root = new TrieNode();
    }

    /*Iterative insertion into trie*/
    public void insert(String word){
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null){
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        //Mark end of word as true
        current.endOfWord = true;
    }
    /*Recursive insert*/
    public void insertRec(String word){
        insertRec(root, word, 0);
    }
    private void insertRec(TrieNode current, String word, int index){
        if (index == word.length()){
            //if end of word, mark as end of word
            current.endOfWord = true;
            return;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);

        //If node doesn't exist in map, create and put in
        if (node == null){
            node = new TrieNode();
            current.children.put(ch, node);
        }
        insertRec(node, word, index + 1);
    }
    /*Iterative search*/
    public boolean search(String word){
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            /*If node doesn't exist, return false*/
            if (node == null)
                return false;
            current = node;
        }
        //return true if current's end of word is true, otherwise, false
        return current.endOfWord;
    }
    /*Recursive Search*/
    public boolean searchRec(String word){
        return searchRec(root, word, 0);
    }
    private boolean searchRec(TrieNode current, String word, int index){
        if (index == word.length()){
            //return true if current's end of word, is true, else return false;
            return current.endOfWord;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        //if node does not exist for given char then return false
        if (node == null) return false;
        return searchRec(node, word, index + 1);
    }
    /*Delete word from trie*/
    public void delete(String word){
        delete(root, word, 0);
    }
    /*Returns true if parent should delete the mapping*/
    private boolean delete(TrieNode current, String word, int index){
        if (index == word.length()){
            //when end of word is reached only delete if current.endOfWord is true
            if (!current.endOfWord) return false;
            current.endOfWord = false;
            /*if current has no mapping, return true*/
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) return false;
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        //If true is returned then delete the mapping of character, and trienode from reference map
        if (shouldDeleteCurrentNode){
            current.children.remove(ch);
            //return true if no mappings are left in the map
            return current.children.size() == 0;
        }
        return false;
    }
}
