package com.dict.test;

import org.junit.Test;

import com.dict.service.WordBreakService;

public class WordBreakTest {
	private WordBreakService wb = new WordBreakService();
	
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
