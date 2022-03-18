package com.poleschuk.javatask3.main;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.poleschuk.javatask3.entity.TextComponent;
import com.poleschuk.javatask3.entity.TextComposite;
import com.poleschuk.javatask3.parser.impl.ParagraphParser;
import com.poleschuk.javatask3.service.FindCountOfWords;

public class TestFindCountOfWords {
	private static final String DATA =
			"    It has survived - not only (five) centuries, but also the leap into electronic typesetting, "	+ 
			"remaining -3-5 essentially 6*9/(3+4) unchanged. It was popularised in the 5*(1+2*(3/(4-(1-56-47)*73)+(-89+4/(42/7)))+1) "	+ 
			"with the release of Letraset sheets containing Lorem Ipsum passages, " +
			"and more recently with desktop publishing software like Aldus PageMaker including versions of " +
			"Lorem Ipsum.\n" +
			"\tIt is a long established fact that a reader will be distracted by the readable content of a page " +
			"when looking at its layout. The point of using (-71+(2+3/(3*(2+1/2+2)-2)/10+2))/7 Ipsum is that it has " +
			"a more-or-less normal distribution of letters, as opposed to using (Content here), content here', " +
			"making it look like readable English.\n" +
			"\tIt is a (7+5*12*(2+5-2-71))/12 established fact that a reader will be of a page when looking " +
			"at its layout.\n" +
			"\tBye. ";
	private static final String COUNT_OF_WORDS_THE = "the";
	private static final int COUNT_OF_WORDS_THE_VALUE = 5;
	private TextComposite parsed;
	
	@BeforeMethod
	public void prepare() {
		ParagraphParser textParser = new ParagraphParser();
		parsed = textParser.parseText(DATA);
	}
    @Test
    public void testFindCountOfWords() {
    	TextComponent text = parsed;
    	FindCountOfWords findCountOfWords = new FindCountOfWords();
    	Map<String, Integer> result = findCountOfWords.findCountOfWords(text);
    	assertEquals((int) result.get(COUNT_OF_WORDS_THE), COUNT_OF_WORDS_THE_VALUE);
    }
}
