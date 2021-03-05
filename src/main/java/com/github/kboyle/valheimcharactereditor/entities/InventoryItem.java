package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.Objects;

public final class InventoryItem implements Writeable {
    public final String name;
    public int quantity;
    public float durability;
    public Position2 position;
    public boolean equipped;
    public int quality;
    public int variant;
    public Crafter crafter;

    public InventoryItem(
            String name,
            int quantity,
            float durability,
            Position2 position,
            boolean equipped,
            int quality,
            int variant,
            Crafter crafter) {
        this.name = name;
        this.quantity = quantity;
        this.durability = durability;
        this.position = position;
        this.equipped = equipped;
        this.quality = quality;
        this.variant = variant;
        this.crafter = crafter;
    }

    public static InventoryItem read(ByteArrayReader buffer) {
        return new InventoryItem(
            buffer.readUTF8ByteString(),
            buffer.readInt(),
            buffer.readFloat(),
            Position2.read(buffer),
            buffer.readBoolean(),
            buffer.readInt(),
            buffer.readInt(),
            Crafter.read(buffer)
        );
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeString(name);
        writer.writeInt(quantity);
        writer.writeFloat(durability);
        writer.write(position);
        writer.writeBoolean(equipped);
        writer.writeInt(quality);
        writer.writeInt(variant);
        writer.write(crafter);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (InventoryItem) obj;
        return Objects.equals(this.name, that.name) &&
            this.quantity == that.quantity &&
            Float.floatToIntBits(this.durability) == Float.floatToIntBits(that.durability) &&
            Objects.equals(this.position, that.position) &&
            this.equipped == that.equipped &&
            this.quality == that.quality &&
            this.variant == that.variant &&
            Objects.equals(this.crafter, that.crafter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, durability, position, equipped, quality, variant, crafter);
    }

    @Override
    public String toString() {
        return "InventoryItem[" +
            "name=" + name + ", " +
            "stack=" + quantity + ", " +
            "durability=" + durability + ", " +
            "position=" + position + ", " +
            "equipped=" + equipped + ", " +
            "quality=" + quality + ", " +
            "variant=" + variant + ", " +
            "crafter=" + crafter + ']';
    }

}
