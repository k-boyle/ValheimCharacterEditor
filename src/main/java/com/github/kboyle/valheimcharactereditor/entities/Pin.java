package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.Objects;

public final class Pin implements Writeable {
    public String name;
    public Position3 position;
    public int type;
    public boolean checked;

    public Pin(String name, Position3 position, int type, boolean checked) {
        this.name = name;
        this.position = position;
        this.type = type;
        this.checked = checked;
    }

    public static Pin read(ByteArrayReader reader) {
        String name = reader.readUTF8ByteString();
        Position3 position = Position3.read(reader);
        int type = reader.readInt();
        boolean checked = reader.readBoolean();
        return new Pin(name, position, type, checked);
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeString(name);
        writer.write(position);
        writer.writeInt(type);
        writer.writeBoolean(checked);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Pin) obj;
        return Objects.equals(this.name, that.name) &&
            Objects.equals(this.position, that.position) &&
            this.type == that.type &&
            this.checked == that.checked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, type, checked);
    }

    @Override
    public String toString() {
        return "Pin[" +
            "name=" + name + ", " +
            "position=" + position + ", " +
            "type=" + type + ", " +
            "checked=" + checked + ']';
    }

}
