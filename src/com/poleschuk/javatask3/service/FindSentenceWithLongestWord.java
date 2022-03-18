package com.poleschuk.javatask3.service;

import java.util.ArrayList;
import java.util.List;

import com.poleschuk.javatask3.entity.TextComponent;
import com.poleschuk.javatask3.entity.TextComposite;
import com.poleschuk.javatask3.entity.TextElementType;

public class FindSentenceWithLongestWord {

    public List<TextComponent> find(TextComponent text) {
    	int sizeOfLongestWord = 0;
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : paragraph.getComponents()) {
                for (TextComponent word : sentence.getComponents()) {
                    if (word.getElementType().equals(TextElementType.WORD)) {
                        List<TextComponent> letters = word.getComponents();
                        if (letters.size() > sizeOfLongestWord) {
                            sizeOfLongestWord = letters.size();
                        }
                    }
                }
            }
        }
        List<TextComponent> result = new ArrayList<>();
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : paragraph.getComponents()) {
                for (TextComponent word : sentence.getComponents()) {
                    if (word.getElementType().equals(TextElementType.WORD)) {
                        List<TextComponent> letters = word.getComponents();
                        if (letters.size() == sizeOfLongestWord) {
                            result.add(word);
                        }
                    }
                }
            }
        }
        return result;
    }

}
