package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Inventory implements Writeable {
    public final int version;
    public final List<InventoryItem> items;

    public Inventory(int version, List<InventoryItem> items) {
        this.version = version;
        this.items = items;
    }

    public static Inventory read(ByteArrayReader buffer) {
        int version = buffer.readInt();
        int numberOfItems = buffer.readInt();

        List<InventoryItem> items = new ArrayList<>(numberOfItems);
        for (int i = 0; i < numberOfItems; i++) {
            items.add(InventoryItem.read(buffer));
        }

        return new Inventory(version, List.copyOf(items));
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeInt(version);
        writer.writeList(items);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Inventory) obj;
        return this.version == that.version &&
            Objects.equals(this.items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, items);
    }

    @Override
    public String toString() {
        return "Inventory[" +
            "version=" + version + ", " +
            "items=" + items + ']';
    }

}
