package com.poleschuk.javatask3.entity;

public enum TextElementType {

    PARAGRAPH("\n"),
    SENTENCE,
    LEXEME(" "),
    WORD,
    LETTER,
    PUNCTUATION,
    NUMBER;

    private final String delimiter;

    TextElementType() {
    	delimiter = "";
    }
    
    TextElementType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}