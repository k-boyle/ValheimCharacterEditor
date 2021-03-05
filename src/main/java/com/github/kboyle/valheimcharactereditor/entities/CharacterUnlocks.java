package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.List;
import java.util.Objects;

public final class CharacterUnlocks implements Writeable {
    public final List<String> recipes;
    public final List<Station> stations;
    public final List<String> materials;
    public final List<String> tutorials;
    public final List<String> uniques;
    public final List<String> trophies;
    public final List<Biome> biomes;
    public final List<Text> texts;

    public CharacterUnlocks(
            List<String> recipes,
            List<Station> stations,
            List<String> materials,
            List<String> tutorials,
            List<String> uniques,
            List<String> trophies,
            List<Biome> biomes,
            List<Text> texts) {
        this.recipes = recipes;
        this.stations = stations;
        this.materials = materials;
        this.tutorials = tutorials;
        this.uniques = uniques;
        this.trophies = trophies;
        this.biomes = biomes;
        this.texts = texts;
    }

    public static CharacterUnlocks read(ByteArrayReader buffer) {
        return new CharacterUnlocks(
            buffer.readList(ByteArrayReader::readUTF8ByteString),
            buffer.readList(Station::read),
            buffer.readList(ByteArrayReader::readUTF8ByteString),
            buffer.readList(ByteArrayReader::readUTF8ByteString),
            buffer.readList(ByteArrayReader::readUTF8ByteString),
            buffer.readList(ByteArrayReader::readUTF8ByteString),
            buffer.readList(Biome::read),
            buffer.readList(Text::read)
        );
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeStringList(recipes);
        writer.writeList(stations);
        writer.writeStringList(materials);
        writer.writeStringList(tutorials);
        writer.writeStringList(uniques);
        writer.writeStringList(trophies);
        writer.writeList(biomes);
        writer.writeList(texts);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CharacterUnlocks) obj;
        return Objects.equals(this.recipes, that.recipes) &&
            Objects.equals(this.stations, that.stations) &&
            Objects.equals(this.materials, that.materials) &&
            Objects.equals(this.tutorials, that.tutorials) &&
            Objects.equals(this.uniques, that.uniques) &&
            Objects.equals(this.trophies, that.trophies) &&
            Objects.equals(this.biomes, that.biomes) &&
            Objects.equals(this.texts, that.texts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipes, stations, materials, tutorials, uniques, trophies, biomes, texts);
    }

    @Override
    public String toString() {
        return "CharacterUnlocks[" +
            "recipes=" + recipes + ", " +
            "stations=" + stations + ", " +
            "materials=" + materials + ", " +
            "tutorials=" + tutorials + ", " +
            "uniques=" + uniques + ", " +
            "trophies=" + trophies + ", " +
            "biomes=" + biomes + ", " +
            "texts=" + texts + ']';
    }

}
