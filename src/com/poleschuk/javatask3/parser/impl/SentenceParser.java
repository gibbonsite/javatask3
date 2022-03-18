package com.poleschuk.javatask3.parser.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.poleschuk.javatask3.entity.TextComponent;
import com.poleschuk.javatask3.entity.TextComposite;
import com.poleschuk.javatask3.entity.TextElementType;
import com.poleschuk.javatask3.parser.TextParser;

public class SentenceParser implements TextParser {

    private static final String SENTENCE_PATTERN =
    		"[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)";
    private final LexemeParser lexemeParser = new LexemeParser();

    @Override
    public TextComposite parseText(String text) {

        TextComposite sentenceComposite = new TextComposite(TextElementType.SENTENCE);
        Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
        Matcher matcher = pattern.matcher(text);

        List<String> sentences = new ArrayList<>();
        while (matcher.find()) {
            sentences.add(matcher.group());
        }

        for (String sentence : sentences) {
            TextComponent sentenceComponents = lexemeParser.parseText(sentence);
            sentenceComposite.add(sentenceComponents);
        }

        return sentenceComposite;
    }
}
