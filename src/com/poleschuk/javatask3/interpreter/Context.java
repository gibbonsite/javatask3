package com.poleschuk.javatask3.interpreter;

import java.util.ArrayDeque;

public class Context {
	private ArrayDeque<Double> contextValue = new ArrayDeque<>();
	public void push(Double number) {
		contextValue.push(number);
	}
	public Double pop() {
		return contextValue.pop();
	}
}
