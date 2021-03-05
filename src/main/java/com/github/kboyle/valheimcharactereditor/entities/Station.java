package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

public record Station(String name, int level) implements Writeable {
    public static Station read(ByteArrayReader buffer) {
        return new Station(buffer.readUTF8ByteString(), buffer.readInt());
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeString(name);
        writer.writeInt(level);
    }
}
