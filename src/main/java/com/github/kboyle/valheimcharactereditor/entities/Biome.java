package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.exceptions.WritingUnknownException;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.HashMap;
import java.util.Map;

public enum Biome implements Writeable {
    NONE(0),
    MEADOWS,
    SWAMP,
    MOUNTAIN,
    BLACK_FOREST,
    PLAINS,
    ASH_LANDS,
    DEEP_NORTH,
    UNKNOWN0,
    OCEAN,
    MISTLANDS,
    BIOMES_MAX(513),
    UNKNOWN1(-1),
    ;

    private static final Map<Integer, Biome> BIOME_MAP = new HashMap<>();

    static {
        for (Biome value : values()) {
            BIOME_MAP.put(value.bit, value);
        }
    }

    private final int bit;

    Biome() {
        this.bit = 1 << (ordinal() - 1);
    }

    Biome(int bit) {
        this.bit = bit;
    }

    public static Biome read(ByteArrayReader reader) {
        Biome biome = BIOME_MAP.get(reader.readInt());
        if (biome == null) {
            return UNKNOWN1;
        }
        return biome;
    }

    @Override
    public void write(ByteArrayWriter writer) {
        if (this == UNKNOWN0 || this == UNKNOWN1) {
            throw new WritingUnknownException();
        }
        writer.writeInt(bit);
    }
}
