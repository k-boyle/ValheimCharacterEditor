package com.github.kboyle.valheimcharactereditor.exceptions;

public class NewCharacterException extends RuntimeException {
    public NewCharacterException(String name) {
        super(String.format("%s is a new character, start a world first", name));
    }
}
