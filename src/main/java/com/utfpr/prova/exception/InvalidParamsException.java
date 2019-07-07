package com.utfpr.prova.exception;

public class InvalidParamsException extends IllegalArgumentException {

	public InvalidParamsException() {
		super("Os parâmetros são inválidos. ");
	}
}
