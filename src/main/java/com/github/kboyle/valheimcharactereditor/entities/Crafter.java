package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.Objects;

public final class Crafter implements Writeable {
    public long id;
    public String name;

    public Crafter(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Crafter read(ByteArrayReader buffer) {
        return new Crafter(buffer.readLong(), buffer.readUTF8ByteString());
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeLong(id);
        writer.writeString(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Crafter) obj;
        return this.id == that.id &&
            Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Crafter[" +
            "id=" + id + ", " +
            "name=" + name + ']';
    }

}
