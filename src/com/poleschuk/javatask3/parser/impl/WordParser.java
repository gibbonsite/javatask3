package com.poleschuk.javatask3.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.poleschuk.javatask3.entity.Symbol;
import com.poleschuk.javatask3.entity.TextComposite;
import com.poleschuk.javatask3.entity.TextElementType;
import com.poleschuk.javatask3.parser.TextParser;

public class WordParser implements TextParser {
    private static final String WORD_PATTERN = "[0-9a-zA-Zа-яА-ЯёЁ'\\(\\)-]+";
	
    @Override
    public TextComposite parseText(String text) {
        TextComposite wordComposite = new TextComposite(TextElementType.WORD);
        Pattern pattern = Pattern.compile(WORD_PATTERN);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String group = matcher.group();
            char[] letters = group.toCharArray();
            for (char letter : letters) {
                wordComposite.add(new Symbol(letter, TextElementType.LETTER));
            }            
        }
        return wordComposite;
    }
}
