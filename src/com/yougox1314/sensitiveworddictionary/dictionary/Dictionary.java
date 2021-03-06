package com.yougox1314.sensitiveworddictionary.dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author youngpx1314
 *
 */
public class Dictionary {
	private static final Logger log = Logger.getLogger(Dictionary.class);
	private final NodeData root = new NodeData(Character.MIN_VALUE);
	private static final Dictionary dictionay = new Dictionary();
	private Dictionary(){
		
	}
	public static Dictionary getInstance(){
		return dictionay;
	}
	public boolean init(String[] strs){
		if(strs==null||strs.length<1){
			return false;
		}
		for(String str:strs){
			this.addSenstiveWord(str);
		}
		return true;
	}
	/**
	 * @param str
	 */
	private void addSenstiveWord(String str) {
		if(str==null||str.trim().length()<1)
			return;
		char[] words = str.toCharArray();
		NodeData currentNode = this.root;
		for(int i=0;i<words.length;i++){
			if(i==words.length-1){
				if(currentNode.findInEndWords(words[i])>=0){
					continue;
				}else{
					currentNode.addEndWord(words[i]);
				}
			}
			else{
				if(currentNode.findInNextWords(words[i])>=0){
					currentNode = currentNode.getNextNode(words[i]);
				}else{
					NodeData node = new NodeData(words[i]);
					currentNode.addNextWord(words[i], node);
					currentNode = node;
				}
			}
			
			currentNode.sort();
			
		}			
	}
	/**
	 * @return
	 */
	public String changeSensWord(String input){
		if(input==null||"".equals(input))
			return "";
		char[] chars = input.toCharArray();
		for(int start=0;start<chars.length;start++){
			int cursor = start;
			if((cursor = this.findword(root, chars, cursor))!=-1){
				Arrays.fill(chars, start, cursor+1, '*');
			}
		}
		return String.valueOf(chars);
	}
	private int findword(NodeData node,char[] word,int index){
		if(node==null)
			return -1;
		if(node.findInEndWords(word[index])>=0)
			return index;
		if(node.findInNextWords(word[index])>=0){
			return findword(node.getNextNode(word[index]), word, ++index);
		}
		return -1;
	}
}
