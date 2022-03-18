package com.poleschuk.javatask3.parser.impl;

import com.poleschuk.javatask3.entity.TextComponent;
import com.poleschuk.javatask3.entity.TextComposite;
import com.poleschuk.javatask3.entity.TextElementType;
import com.poleschuk.javatask3.parser.TextParser;

public class ParagraphParser implements TextParser {
    private static final String PARAGRAPH_PATTERN = "[\\n\\t]+";
    private final SentenceParser sentenceParser = new SentenceParser();
	
    @Override
    public TextComposite parseText(String text) {
        TextComposite paragraphComposite = new TextComposite(TextElementType.PARAGRAPH);
        String[] paragraphs = text.strip().split(PARAGRAPH_PATTERN);
        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = sentenceParser.parseText(paragraph);
            paragraphComposite.add(paragraphComponent);
        }
        return paragraphComposite;
    }
}
