package com.poleschuk.javatask3.main;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.poleschuk.javatask3.entity.TextComponent;
import com.poleschuk.javatask3.entity.TextComposite;
import com.poleschuk.javatask3.entity.TextElementType;
import com.poleschuk.javatask3.parser.impl.ParagraphParser;


public class TestStructure {
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

	private TextComposite parsed;
	
	@BeforeMethod
	public void prepare() {
		ParagraphParser textParser = new ParagraphParser();
		parsed = textParser.parseText(DATA);
	}
	
    @Test
    public void testStructure() {
    	TextComposite text = parsed;
    	List<TextComponent> components = text.getComponents();
		TextComponent firstSentence = components.get(0);
    	List<TextComponent> sentenceComponents = firstSentence.getComponents();
		TextComponent firstLexeme = sentenceComponents.get(0);
    	List<TextComponent> lexemeComponents = firstLexeme.getComponents();
		TextComponent firstWord = lexemeComponents.get(0);
    	List<TextComponent> wordComponents = firstWord.getComponents();
		TextComponent firstSymbol = wordComponents.get(0);
		assertTrue(components.size() == 4 && firstSentence.getElementType() == TextElementType.SENTENCE &&
				firstLexeme.getElementType() == TextElementType.LEXEME && firstWord.getElementType() == TextElementType.WORD &&
		        firstSymbol.getElementType() == TextElementType.LETTER);
    }
    
    @Test
    public void testWordAndSymbol() {
    	TextComposite text = parsed;
    	List<TextComponent> paragraphs = text.getComponents();
		TextComponent firstParagraph = paragraphs.get(0);
    	List<TextComponent> sentenceComponents = firstParagraph.getComponents();
		TextComponent firstSentence = sentenceComponents.get(0);
    	List<TextComponent> wordComponents = firstSentence.getComponents();
		TextComponent firstWord = wordComponents.get(0);
    	List<TextComponent> symbolComponents = firstWord.getComponents();
		TextComponent firstSymbol = symbolComponents.get(0);
		TextComponent secondSymbol = symbolComponents.get(1);
		assertTrue(firstWord.toString().equals("It") && firstSymbol.toString().equals("I") &&
				secondSymbol.toString().equals("t"));
    }
}
