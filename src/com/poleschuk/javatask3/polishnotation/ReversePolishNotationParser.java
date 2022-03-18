package com.poleschuk.javatask3.polishnotation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReversePolishNotationParser {
	private static Logger logger = LogManager.getLogger();
	private static String operators = "+-*/";
	private static String delimiters = "() " + operators;
	private boolean parsingOK = true;
	List<String> postfix = new ArrayList<String>();
	
	private static boolean isDelimiter(String token) {
		if (token.length() != 1) return false;
		for (int i = 0; i < delimiters.length(); i++) {
			if (token.charAt(0) == delimiters.charAt(i)) return true;
		}
		return false;
	}
	private static boolean isOperator(String token) {
		if (token.equals("u")) return true;
		for (int i = 0; i < operators.length(); i++) {
			if (token.charAt(0) == operators.charAt(i)) return true;
		}
		return false;
	}
	private static int priority(String token) {
		if (token.equals("(")) return 1;
		if (token.equals("+") || token.equals("-")) return 2;
		if (token.equals("*") || token.equals("/")) return 3;
		return 4;
	}
	public ReversePolishNotationParser(String infix) {
		Deque<String> stack = new ArrayDeque<String>();
		StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
		String prev = "";
		String curr = "";
		while (tokenizer.hasMoreTokens()) {
			curr = tokenizer.nextToken();
			if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
				logger.error("Некорректное выражение.");
				parsingOK = false;
				return;
			}
			if (curr.equals(" ")) continue;
			if (isDelimiter(curr)) {
				if (curr.equals("(")) {
					stack.push(curr);
				}
				else if (curr.equals(")")) {
					while (!stack.peek().equals("(")) {
						postfix.add(stack.pop());
						if (stack.isEmpty()) {
							logger.error("Скобки не согласованы.");
							parsingOK = false;
							return;
						}
					}
					stack.pop();
					/*if (!stack.isEmpty() && isFunction(stack.peek())) {
						postfix.add(stack.pop());
					}*/
				} else {
					if (curr.equals("-") && (prev.equals("") ||	(isDelimiter(prev) && !prev.equals(")")))) {
						// унарный минус
						curr = "u";
					} else {
						while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
							postfix.add(stack.pop());
						}
					}
					stack.push(curr);
				}
			} else {
				postfix.add(curr);
			}
			prev = curr;
		}
		while (!stack.isEmpty()) {
			if (isOperator(stack.peek())) {
				postfix.add(stack.pop());
			} else {
				logger.error("Скобки не согласованы.");
				parsingOK = false;
				return;
			}
		}
	}
	
	public String parse() {
		StringJoiner joiner = new StringJoiner(" ");
		for (String x : postfix) {
			joiner.add(x);
		}
		return joiner.toString();
	}

	public boolean isParsingOK() {
		return parsingOK;
	}
}
		