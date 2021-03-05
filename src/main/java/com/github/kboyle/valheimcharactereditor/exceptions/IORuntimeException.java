package com.github.kboyle.valheimcharactereditor.exceptions;

import java.io.IOException;

public class IORuntimeException extends RuntimeException {
    public IORuntimeException(IOException exception) {
        super(exception);
    }
}
