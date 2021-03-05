package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

public record Power(String name, float cooldown) implements Writeable {
    public static Power read(ByteArrayReader buffer) {
        return new Power(buffer.readUTF8ByteString(), buffer.readFloat());
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeString(name);
        writer.writeFloat(cooldown);
    }
}
