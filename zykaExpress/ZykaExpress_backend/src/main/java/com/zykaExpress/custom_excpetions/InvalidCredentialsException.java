package com.zykaExpress.custom_excpetions;

public class InvalidCredentialsException extends RuntimeException {
	public InvalidCredentialsException(String mesg) {
		super(mesg);
	}
}
