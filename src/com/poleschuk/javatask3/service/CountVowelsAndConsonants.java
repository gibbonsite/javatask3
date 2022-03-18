package com.poleschuk.javatask3.service;

import java.util.regex.Pattern;

import com.poleschuk.javatask3.entity.TextComponent;
import com.poleschuk.javatask3.entity.TextElementType;

public class CountVowelsAndConsonants {
    private static final String VOWEL_PATTERN = "(?ui)[aeiouyуеыаоэяиюё]";
    private static final String CONSONANT_PATTERN = "(?ui)[a-zа-я&&[^aeiouyуеыаоэяию]]";
    
    public long countVowels(TextComponent text) {
        Pattern pattern = Pattern.compile(VOWEL_PATTERN);
        long counter = text.getComponents().stream()
                .flatMap(paragraph -> paragraph.getComponents().stream())
                .flatMap(sentence -> sentence.getComponents().stream())
                .filter(word -> word.getElementType().equals(TextElementType.WORD))
                .flatMap(word -> word.getComponents().stream())
                .map(Object::toString)
                .filter(letter -> pattern.matcher(letter).matches())
                .count();
        return counter;
    }

    public long countConsonants(TextComponent text) {
        Pattern pattern = Pattern.compile(CONSONANT_PATTERN);
        long counter = text.getComponents().stream()
                .flatMap(paragraph -> paragraph.getComponents().stream())
                .flatMap(sentence -> sentence.getComponents().stream())
                .filter(word -> word.getElementType().equals(TextElementType.WORD))
                .flatMap(word -> word.getComponents().stream())
                .map(Object::toString)
                .filter(letter -> pattern.matcher(letter).matches())
                .count();
        return counter;
    }
}