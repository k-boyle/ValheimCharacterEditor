package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

public record Colour(float r, float g, float b) implements Writeable {
    public static Colour read(ByteArrayReader buffer) {
        return new Colour(buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeFloat(r);
        writer.writeFloat(g);
        writer.writeFloat(b);
    }
}
