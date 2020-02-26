package com.test;

import org.junit.Test;

import com.test.service.WordBreak;

public class WordBreakTest {
	private WordBreak wb = new WordBreak();
	
	@Test
	public void testWordBreak1(){
		//first requirement
		String str = "ilikesamsungmobile";
		wb.wordBreak(str).forEach(s -> System.out.println(s));
	}
	
	@Test
	public void testWordBreak2(){
		//second requirement
		String str = "ilikesamsungmobile";
		String[] custdict = {"i","like","sam","sung","mobile","icecream","man","go","mango"};
		wb.wordBreak(str, custdict).forEach(s -> System.out.println(s));
	}
	
	@Test
	public void testWordBreak3(){
		//third requirement
		String str = "ilikeicecreamandmango";
		String[] custdict = {"i","like","sam","sung","mobile","icecream","man","go","mango"};
		wb.wordBreak(str, "all", custdict).forEach(s -> System.out.println(s));
	}
}
