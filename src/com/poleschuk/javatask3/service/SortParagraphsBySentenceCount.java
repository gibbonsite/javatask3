package com.poleschuk.javatask3.service;

import java.util.List;

import com.poleschuk.javatask3.entity.TextComponent;

public class SortParagraphsBySentenceCount {
	
	public List<TextComponent> sort(TextComponent text) {
        List<TextComponent> paragraphs = text.getComponents();
        paragraphs.sort((o1, o2) -> {
            Integer sizeO1 = o1.getComponents().size();
            Integer sizeO2 = o2.getComponents().size();
            return sizeO1.compareTo(sizeO2);
        });
        return paragraphs;
	}
}
