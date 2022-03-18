package com.poleschuk.javatask3.entity;

import java.util.List;

public class Symbol implements TextComponent {
    private final TextElementType elementType;
    private final char value;

    public Symbol(char value, TextElementType elementType) {
        this.value = value;
        this.elementType = elementType;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean add(TextComponent component) {
        throw new UnsupportedOperationException("Can't add something for the symbol");
    }

    @Override
    public boolean remove(TextComponent component) {
        throw new UnsupportedOperationException("Can't remove something for the symbol");
    }

    @Override
    public TextElementType getElementType() {
        return elementType;
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException("Can't get components for the symbol");
    }
}

