package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.List;
import java.util.Objects;

public final class CharacterSkills implements Writeable {
    public final int version;
    public final List<CharacterSkill> skills;

    public CharacterSkills(int version, List<CharacterSkill> skills) {
        this.version = version;
        this.skills = skills;
    }

    public static CharacterSkills read(ByteArrayReader buffer) {
        return new CharacterSkills(buffer.readInt(), buffer.readList(CharacterSkill::read));
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeInt(version);
        writer.writeList(skills);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CharacterSkills) obj;
        return this.version == that.version &&
            Objects.equals(this.skills, that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, skills);
    }

    @Override
    public String toString() {
        return "CharacterSkills[" +
            "version=" + version + ", " +
            "skills=" + skills + ']';
    }

}
