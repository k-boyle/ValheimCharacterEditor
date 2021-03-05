package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.Objects;

public final class Position2 implements Writeable {
    public int x;
    public int y;

    public Position2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position2 read(ByteArrayReader buffer) {
        return new Position2(buffer.readInt(), buffer.readInt());
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeInt(x);
        writer.writeInt(y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Position2) obj;
        return this.x == that.x &&
            this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position2[" +
            "x=" + x + ", " +
            "y=" + y + ']';
    }

}
