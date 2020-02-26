package com.dict.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dict.entity.Dictionary;

public class WordBreakService {

	private int maxLength = -1;
	private Set<String> wordSet = new HashSet<>();
	private Map<Integer, List<String>> map = new HashMap<>();

	public List<String> calculateResult(String s, int currentPos) {
		if (currentPos == s.length()) {
			List<String> result = new ArrayList<>();
			result.add("");
			return result;
		}
		if (map.containsKey(currentPos)) {
			return map.get(currentPos);
		}
		List<String> result = new ArrayList<>();
		map.put(currentPos, result);

		for (int i = 1; i <= maxLength && currentPos + i <= s.length(); i++) {
			String subString = s.substring(currentPos, currentPos + i);
			if (wordSet.contains(subString)) {
				List<String> returnStrings = calculateResult(s, currentPos + i);
				returnStrings.forEach(returnString -> {
					if (returnString.equals("")) {
						result.add(subString);
					} else {
						result.add(subString + " " + returnString);
					}
				});
			}
		}
		return result;
	}

	/**
	 * Given a valid sentence without any spaces between the words and a dictionary of valid English words, 
	 * find all possible ways to break the sentence in individual dictionary words
	 * @param s
	 * @return
	 */
	public List<String> wordBreak(String s) {
		List<String> wordDict = Arrays.asList(new Dictionary().getDict());
		wordDict.forEach(word -> {
			wordSet.add(word);
			maxLength = Math.max(maxLength, word.length());
		});
		return calculateResult(s, 0);
	}

	/**
	 * If user provide a customized dictionary of valid English words as additional input, 
	 * and the program will only find in the user customized dictionary
	 * @param s
	 * @param custdict
	 * @return
	 */
	public List<String> wordBreak(String s, String[] custdict) {
		List<String> wordDict = Arrays.asList(custdict);
		wordDict.forEach(word -> {
			wordSet.add(word);
			maxLength = Math.max(maxLength, word.length());
		});
		return calculateResult(s, 0);
	}

	/**
	 * If user provide a customized dictionary of valid English words as additional input, 
	 * and the program will find all the valid words in the both dictionaries
	 * @param s
	 * @param dicttype
	 * @param custdict
	 * @return
	 */
	public List<String> wordBreak(String s, String dicttype, String[] custdict) {
		List<String> dict = Arrays.asList(new Dictionary().getDict());
		List<String> cust_dict = Arrays.asList(custdict);
		// distinct the dictionary
		List<String> wordDict = Stream.of(dict, cust_dict).flatMap(Collection::stream).distinct()
				.collect(Collectors.toList());

		wordDict.forEach(word -> {
			wordSet.add(word);
			maxLength = Math.max(maxLength, word.length());
		});
		return calculateResult(s, 0);
	}
}
