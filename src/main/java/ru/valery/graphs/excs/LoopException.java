package ru.valery.graphs.excs;

public class LoopException extends RuntimeException{
	public LoopException(String message) {
		super(message);
	}
}
