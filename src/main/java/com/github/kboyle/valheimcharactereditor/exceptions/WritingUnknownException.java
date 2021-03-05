package com.github.kboyle.valheimcharactereditor.exceptions;

public class WritingUnknownException extends RuntimeException {
    public WritingUnknownException() {
        super("Writing an accumulator value is currently not supported");
    }
}
