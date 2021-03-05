package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.List;
import java.util.Objects;

public final class WorldData implements Writeable {
    public final int version;
    public final int textureSize;
    public final boolean[] exploredSegments;
    public final List<Pin> pins;
    public final boolean publicReferencePosition;

    public WorldData(
            int version,
            int textureSize,
            boolean[] exploredSegments,
            List<Pin> pins,
            boolean publicReferencePosition) {
        this.version = version;
        this.textureSize = textureSize;
        this.exploredSegments = exploredSegments;
        this.pins = pins;
        this.publicReferencePosition = publicReferencePosition;
    }

    public static WorldData read(ByteArrayReader reader) {
        int version = reader.readInt();
        int textureSize = reader.readInt();
        boolean[] exploredSegments = reader.readBooleanArray(textureSize * textureSize);
        List<Pin> pins = reader.readList(Pin::read);
        boolean publicReferencePosition = reader.readBoolean();
        return new WorldData(
            version,
            textureSize,
            exploredSegments,
            pins,
            publicReferencePosition
        );
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeWithLength(writer0 -> {
            writer0.writeInt(version);
            writer0.writeInt(textureSize);
            writer0.writeBooleanArray(exploredSegments);
            writer0.writeList(pins);
            writer0.writeBoolean(publicReferencePosition);
        });
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WorldData) obj;
        return this.version == that.version &&
            this.textureSize == that.textureSize &&
            Objects.equals(this.exploredSegments, that.exploredSegments) &&
            Objects.equals(this.pins, that.pins) &&
            this.publicReferencePosition == that.publicReferencePosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, textureSize, exploredSegments, pins, publicReferencePosition);
    }

    @Override
    public String toString() {
        return "WorldData[" +
            "version=" + version + ", " +
            "textureSize=" + textureSize + ", " +
            "exploredSegments=" + exploredSegments + ", " +
            "pins=" + pins + ", " +
            "publicReferencePosition=" + publicReferencePosition + ']';
    }

}
