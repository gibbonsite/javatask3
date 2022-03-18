package com.poleschuk.javatask3.service;

import java.util.List;

import com.poleschuk.javatask3.entity.TextComponent;
import com.poleschuk.javatask3.entity.TextElementType;

public class DeleteSentencesWithWordsLessThan {
    public void delete(TextComponent text, int countWord) {
        List<TextComponent> sentenceList;

        for (TextComponent paragraph : text.getComponents()) {
            sentenceList = paragraph.getComponents();
            for (TextComponent sentence : sentenceList) {
                int countOfWords = 0;
                for (TextComponent word : sentence.getComponents()) {
                    if (word.getElementType().equals(TextElementType.WORD)) {
                        countOfWords++;
                    }
                }
                if (countOfWords < countWord) {
                    paragraph.remove(sentence);
                }
            }
        }
    }
}
