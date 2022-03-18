package com.poleschuk.javatask3.service;

import java.util.Map;
import java.util.stream.Collectors;

import com.poleschuk.javatask3.entity.TextComponent;
import com.poleschuk.javatask3.entity.TextElementType;

public class FindCountOfWords {
    public Map<String, Integer> findCountOfWords(TextComponent text) {
        Map<String, Integer> words;
        words = text.getComponents().stream()
                .flatMap(paragraph -> paragraph.getComponents().stream())
                .flatMap(sentence -> sentence.getComponents().stream())
                .filter(word -> word.getElementType().equals(TextElementType.WORD))
                .map(word -> word.toString().toLowerCase())
                .collect(Collectors.toMap(str -> str, i -> 1, (i1, i2) -> i1 + i2));
        return words;
    }
}
