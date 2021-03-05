package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.Objects;

public final class CharacterStats implements Writeable {
    public final int version;
    public int kills;
    public int deaths;
    public int crafts;
    public int builds;

    public CharacterStats(int version, int kills, int deaths, int crafts, int builds) {
        this.version = version;
        this.kills = kills;
        this.deaths = deaths;
        this.crafts = crafts;
        this.builds = builds;
    }

    public static CharacterStats read(ByteArrayReader buffer) {
        return new CharacterStats(
            buffer.readInt(),
            buffer.readInt(),
            buffer.readInt(),
            buffer.readInt(),
            buffer.readInt()
        );
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeInt(version);
        writer.writeInt(kills);
        writer.writeInt(deaths);
        writer.writeInt(crafts);
        writer.writeInt(builds);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CharacterStats) obj;
        return this.version == that.version &&
            this.kills == that.kills &&
            this.deaths == that.deaths &&
            this.crafts == that.crafts &&
            this.builds == that.builds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, kills, deaths, crafts, builds);
    }

    @Override
    public String toString() {
        return "CharacterStats[" +
            "version=" + version + ", " +
            "kills=" + kills + ", " +
            "deaths=" + deaths + ", " +
            "crafts=" + crafts + ", " +
            "builds=" + builds + ']';
    }

}
