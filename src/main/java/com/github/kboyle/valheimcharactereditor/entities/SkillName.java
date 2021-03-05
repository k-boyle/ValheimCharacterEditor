package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.exceptions.WritingUnknownException;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.HashMap;
import java.util.Map;

public enum SkillName implements Writeable {
    NONE(0),
    SWORDS,
    KNIVES,
    CLUBS,
    POLEARMS,
    SPEARS,
    BLOCKING,
    AXES,
    BOWS,
    FIRE_MAGIC,
    FROST_MAGIC,
    UNARMED,
    PICKAXES,
    WOOD_CUTTING,
    JUMP(100),
    SNEAK(101),
    RUN(102),
    SWIM(103),
    ALL(999),
    UNKNOWN(-1)
    ;

    private static final Map<Integer, SkillName> SKILL_MAP = new HashMap<>();

    static {
        for (SkillName value : values()) {
            SKILL_MAP.put(value.bit, value);
        }
    }

    private final int bit;

    SkillName() {
        this.bit = ordinal();
    }

    SkillName(int bit) {
        this.bit = bit;
    }

    public static SkillName read(ByteArrayReader buffer) {
        SkillName skill = SKILL_MAP.get(buffer.readInt());
        if (skill == null) {
            return UNKNOWN;
        }
        return skill;
    }

    @Override
    public void write(ByteArrayWriter writer) {
        if (this == UNKNOWN) {
            throw new WritingUnknownException();
        }
        writer.writeInt(bit);
    }
}
