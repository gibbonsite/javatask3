package com.poleschuk.javatask3.parser.impl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.poleschuk.javatask3.entity.Symbol;
import com.poleschuk.javatask3.entity.TextComponent;
import com.poleschuk.javatask3.entity.TextComposite;
import com.poleschuk.javatask3.entity.TextElementType;
import com.poleschuk.javatask3.interpreter.Context;
import com.poleschuk.javatask3.interpreter.MathExpression;
import com.poleschuk.javatask3.interpreter.MathExpressionCreator;
import com.poleschuk.javatask3.parser.TextParser;
import com.poleschuk.javatask3.polishnotation.ReversePolishNotationParser;

public class LexemeParser implements TextParser {

    private static final String LEXEME_SEPARATOR = "\\s+";
    private static final String PUNCTUATION_PATTERN = "[,\\.\\u2026]";
    private static final String CALCULATION_PATTERN = "([0-9]+[\\+\\-\\*\\/]{1}[0-9]+)+([\\+\\-\\*\\/]{1}[0-9]+)*";

    private final WordParser wordParser = new WordParser();

    @Override
    public TextComposite parseText(String text) {
        TextComposite lexemeComposite = new TextComposite(TextElementType.LEXEME);
        String[] lexemes = text.strip().split(LEXEME_SEPARATOR);
        Pattern calculationPattern = Pattern.compile(CALCULATION_PATTERN);
        for (String lexeme : lexemes) {
            if (calculationPattern.matcher(lexeme).find()) {
                lexeme = calculate(lexeme).orElse(lexeme);
                TextComposite numberComposite = new TextComposite(TextElementType.NUMBER); 
                char[] letters = lexeme.toCharArray();
                for (char letter : letters) {
                	numberComposite.add(new Symbol(letter, TextElementType.LETTER));
                }
                lexemeComposite.add(numberComposite);
            } else {
            	TextComponent lexemeComponent = wordParser.parseText(lexeme);
	            lexemeComposite.add(lexemeComponent);
	            Pattern punctuationPattern = Pattern.compile(PUNCTUATION_PATTERN);
	            Matcher punctuationMatcher = punctuationPattern.matcher(lexeme);
	            if (punctuationMatcher.find()) {
	                String group = punctuationMatcher.group();
	                TextComponent punctuationComponent = new Symbol(group.charAt(0), TextElementType.PUNCTUATION);
	                lexemeComposite.add(punctuationComponent);
	            }            
            }
        }
        return lexemeComposite;
    }

    private Optional<String> calculate(String input) {
    	ReversePolishNotationParser reversePolishNotation = new ReversePolishNotationParser(input);
		String polishNotationExpression = reversePolishNotation.parse();
		if (reversePolishNotation.isParsingOK()) {
			MathExpressionCreator interpreter = new MathExpressionCreator();
			List<MathExpression> list = interpreter.parse(polishNotationExpression);
			Context context = new Context();
			for (MathExpression terminal : list) {
				terminal.interprete(context);
			}
	        String result = String.valueOf(context.pop());
	        return Optional.of(result);
		} else {
			return Optional.empty();
		}
    }
}
