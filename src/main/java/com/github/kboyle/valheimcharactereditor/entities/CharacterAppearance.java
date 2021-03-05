package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

public record CharacterAppearance(String beard, String hair, Colour skinColour, Colour hairColour, int gender) implements Writeable {
    public static CharacterAppearance read(ByteArrayReader buffer) {
        return new CharacterAppearance(
            buffer.readUTF8ByteString(),
            buffer.readUTF8ByteString(),
            Colour.read(buffer),
            Colour.read(buffer),
            buffer.readInt()
        );
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeString(beard);
        writer.writeString(hair);
        writer.write(skinColour);
        writer.write(hairColour);
        writer.writeInt(gender);
    }
}
