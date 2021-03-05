package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.Objects;

public final class Position3 implements Writeable {
    public float x;
    public float y;
    public float z;

    public Position3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Position3 read(ByteArrayReader buffer) {
        return new Position3(buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeFloat(x);
        writer.writeFloat(y);
        writer.writeFloat(z);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Position3) obj;
        return Float.floatToIntBits(this.x) == Float.floatToIntBits(that.x) &&
            Float.floatToIntBits(this.y) == Float.floatToIntBits(that.y) &&
            Float.floatToIntBits(this.z) == Float.floatToIntBits(that.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Position3[" +
            "x=" + x + ", " +
            "y=" + y + ", " +
            "z=" + z + ']';
    }

}
