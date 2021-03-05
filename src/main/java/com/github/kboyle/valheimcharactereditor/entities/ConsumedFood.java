package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

public record ConsumedFood(String name, float remainingHp, float remainingStamina) implements Writeable {
    public static ConsumedFood read(ByteArrayReader buffer) {
        return new ConsumedFood(
            buffer.readUTF8ByteString(),
            buffer.readFloat(),
            buffer.readFloat()
        );
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeString(name);
        writer.writeFloat(remainingHp);
        writer.writeFloat(remainingStamina);
    }
}
