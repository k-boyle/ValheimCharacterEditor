package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

public record Text(String label, String text) implements Writeable {
    public static Text read(ByteArrayReader buffer) {
        return new Text(buffer.readUTF8ByteString(), buffer.readUTF8ByteString());
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeString(label);
        writer.writeString(text);
    }
}
