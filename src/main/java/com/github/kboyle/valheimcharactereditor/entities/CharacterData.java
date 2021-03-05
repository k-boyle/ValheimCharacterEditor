package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.Objects;

public final class CharacterData implements Writeable {
    public final int version;
    public float maxHp;
    public float hp;
    public float stamina;
    public boolean firstSpawn;
    public float timeSinceDeath;
    public Power power;
    public final Inventory inventory;

    public CharacterData(
            int version,
            float maxHp,
            float hp,
            float stamina,
            boolean firstSpawn,
            float timeSinceDeath,
            Power power,
            Inventory inventory) {
        this.version = version;
        this.maxHp = maxHp;
        this.hp = hp;
        this.stamina = stamina;
        this.firstSpawn = firstSpawn;
        this.timeSinceDeath = timeSinceDeath;
        this.power = power;
        this.inventory = inventory;
    }

    public static CharacterData read(ByteArrayReader buffer) {
        buffer.readInt();

        int version = buffer.readInt();
        float maxHp = buffer.readFloat();
        float hp = buffer.readFloat();
        float stamina = buffer.readFloat();
        boolean firstSpawn = buffer.readBoolean();
        float timeSinceDeath = buffer.readFloat();
        Power power = Power.read(buffer);
        Inventory inventory = Inventory.read(buffer);

        return new CharacterData(
            version,
            maxHp,
            hp,
            stamina,
            firstSpawn,
            timeSinceDeath,
            power,
            inventory
        );
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeInt(version);
        writer.writeFloat(maxHp);
        writer.writeFloat(hp);
        writer.writeFloat(stamina);
        writer.writeBoolean(firstSpawn);
        writer.writeFloat(timeSinceDeath);
        writer.write(power);
        writer.write(inventory);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CharacterData) obj;
        return this.version == that.version &&
            Float.floatToIntBits(this.maxHp) == Float.floatToIntBits(that.maxHp) &&
            Float.floatToIntBits(this.hp) == Float.floatToIntBits(that.hp) &&
            Float.floatToIntBits(this.stamina) == Float.floatToIntBits(that.stamina) &&
            this.firstSpawn == that.firstSpawn &&
            Float.floatToIntBits(this.timeSinceDeath) == Float.floatToIntBits(that.timeSinceDeath) &&
            Objects.equals(this.power, that.power) &&
            Objects.equals(this.inventory, that.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, maxHp, hp, stamina, firstSpawn, timeSinceDeath, power, inventory);
    }

    @Override
    public String toString() {
        return "CharacterData[" +
            "version=" + version + ", " +
            "maxHp=" + maxHp + ", " +
            "hp=" + hp + ", " +
            "stamina=" + stamina + ", " +
            "firstSpawn=" + firstSpawn + ", " +
            "timeSinceDeath=" + timeSinceDeath + ", " +
            "power=" + power + ", " +
            "inventory=" + inventory + ']';
    }

}
