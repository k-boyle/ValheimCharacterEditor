package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.exceptions.NewCharacterException;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Character implements Writeable {
    public final long id;
    public String name;
    public final CharacterStats stats;
    public final List<World> worlds;
    public final String seed;
    public final CharacterData data;
    public final CharacterUnlocks unlocks;
    public final CharacterAppearance appearance;
    public final List<ConsumedFood> consumedFoods;
    public final CharacterSkills skills;

    public Character(
            long id,
            String name,
            CharacterStats stats,
            List<World> worlds,
            String seed,
            CharacterData data,
            CharacterUnlocks unlocks,
            CharacterAppearance appearance,
            List<ConsumedFood> consumedFoods,
            CharacterSkills skills) {
        this.id = id;
        this.name = name;
        this.stats = stats;
        this.worlds = worlds;
        this.seed = seed;
        this.data = data;
        this.unlocks = unlocks;
        this.appearance = appearance;
        this.consumedFoods = consumedFoods;
        this.skills = skills;
    }

    public static Character read(ByteArrayReader reader) {
        reader.readInt();
        CharacterStats stats = CharacterStats.read(reader);

        int numberOfWorlds = reader.readInt();
        List<World> worlds = new ArrayList<>(numberOfWorlds);
        for (int i = 0; i < numberOfWorlds; i++) {
            worlds.add(World.read(reader));
        }

        String name = reader.readUTF8ByteString();
        long id = reader.readLong();
        String seed = reader.readUTF8ByteString();

        if (!reader.readBoolean()) {
            throw new NewCharacterException(name);
        }

        CharacterData data = CharacterData.read(reader);
        CharacterUnlocks unlocks = CharacterUnlocks.read(reader);
        CharacterAppearance appearance = CharacterAppearance.read(reader);
        List<ConsumedFood> consumedFoods = reader.readList(ConsumedFood::read);
        CharacterSkills skills = CharacterSkills.read(reader);

        return new Character(
            id,
            name,
            stats,
            List.copyOf(worlds),
            seed,
            data,
            unlocks,
            appearance,
            List.copyOf(consumedFoods),
            skills
        );
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.write(stats);
        writer.writeList(worlds);
        writer.writeString(name);
        writer.writeLong(id);
        writer.writeString(seed);
        writer.writeBoolean(true);
        writer.writeWithLength(writer0 -> {
            writer0.write(data);
            writer0.write(unlocks);
            writer0.write(appearance);
            writer0.writeList(consumedFoods);
            writer0.write(skills);
        });
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Character) obj;
        return this.id == that.id &&
            Objects.equals(this.name, that.name) &&
            Objects.equals(this.stats, that.stats) &&
            Objects.equals(this.worlds, that.worlds) &&
            Objects.equals(this.seed, that.seed) &&
            Objects.equals(this.data, that.data) &&
            Objects.equals(this.unlocks, that.unlocks) &&
            Objects.equals(this.appearance, that.appearance) &&
            Objects.equals(this.consumedFoods, that.consumedFoods) &&
            Objects.equals(this.skills, that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stats, worlds, seed, data, unlocks, appearance, consumedFoods, skills);
    }

    @Override
    public String toString() {
        return "Character[" +
            "id=" + id + ", " +
            "name=" + name + ", " +
            "stats=" + stats + ", " +
            "worlds=" + worlds + ", " +
            "seed=" + seed + ", " +
            "data=" + data + ", " +
            "unlocks=" + unlocks + ", " +
            "appearance=" + appearance + ", " +
            "consumedFoods=" + consumedFoods + ", " +
            "skills=" + skills + ']';
    }
}
