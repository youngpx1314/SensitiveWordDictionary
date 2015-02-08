package com.yougox1314.sensitiveworddictionary.dictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author youngpx1314
 *
 */
public class NodeData {
	private static final Logger log = Logger.getLogger(NodeData.class);
	private char word;
	private char[] endWords = new char[0];
	private char[] nextWords = new char[0];
	
	private Map<Character,NodeData> nextNodesMap = null; 
	
	public NodeData(char word){
		this.word = word;
	}
	/**
	 * 增加结束单词
	 * @param word
	 * @return
	 */
	public boolean addEndWord(char word){
		char[] newWords = new char[endWords.length+1];
		System.arraycopy(endWords, 0, newWords, 0, endWords.length);
		newWords[newWords.length-1] = word;
		this.endWords = newWords;
		return true;
	}
	public boolean addNextWord(char word,NodeData node){
		char[] newWords = new char[nextWords.length+1];
		System.arraycopy(nextWords, 0, newWords, 0, nextWords.length);
		newWords[newWords.length-1] = word;
		this.nextWords = newWords;
		
		if(this.nextNodesMap==null){
			this.nextNodesMap = new HashMap<Character,NodeData>();
		}
		this.nextNodesMap.put(word, node);
		return true;
	}
	public void sort(){
		Arrays.sort(this.endWords);
		Arrays.sort(this.nextWords);
	}
	public int findInEndWords(char word){
		return Arrays.binarySearch(this.endWords, word);
	}
	public int findInNextWords(char word){
		return Arrays.binarySearch(this.nextWords, word);
	}
	public NodeData getNextNode(char word){
		return this.nextNodesMap.get(word);
	}
}
