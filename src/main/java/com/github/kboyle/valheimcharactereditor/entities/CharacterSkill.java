package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.Objects;

public final class CharacterSkill implements Writeable {
    public final SkillName name;
    public float level;
    public float accumulator;

    public CharacterSkill(SkillName name, float level, float accumulator) {
        this.name = name;
        this.level = level;
        this.accumulator = accumulator;
    }

    public static CharacterSkill read(ByteArrayReader buffer) {
        return new CharacterSkill(SkillName.read(buffer), buffer.readFloat(), buffer.readFloat());
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.write(name);
        writer.writeFloat(level);
        writer.writeFloat(accumulator);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CharacterSkill) obj;
        return Objects.equals(this.name, that.name) &&
            Float.floatToIntBits(this.level) == Float.floatToIntBits(that.level) &&
            Float.floatToIntBits(this.accumulator) == Float.floatToIntBits(that.accumulator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, accumulator);
    }

    @Override
    public String toString() {
        return "CharacterSkill[" +
            "name=" + name + ", " +
            "level=" + level + ", " +
            "accumulator=" + accumulator + ']';
    }

}
