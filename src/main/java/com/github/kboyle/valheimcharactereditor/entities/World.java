package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.Objects;

public final class World implements Writeable {
    public final long id;
    public final boolean customSpawn;
    public Position3 spawn;
    public final boolean hasLogout;
    public Position3 logout;
    public final boolean hasDeath;
    public Position3 death;
    public Position3 home;
    public final WorldData worldData;

    public World(
            long id,
            boolean customSpawn,
            Position3 spawn,
            boolean hasLogout,
            Position3 logout,
            boolean hasDeath,
            Position3 death,
            Position3 home,
            WorldData worldData) {
        this.id = id;
        this.customSpawn = customSpawn;
        this.spawn = spawn;
        this.hasLogout = hasLogout;
        this.logout = logout;
        this.hasDeath = hasDeath;
        this.death = death;
        this.home = home;
        this.worldData = worldData;
    }

    public static World read(ByteArrayReader buffer) {
        long id = buffer.readLong();
        boolean customSpawn = buffer.readBoolean();
        Position3 spawn = Position3.read(buffer);
        boolean hasLogout = buffer.readBoolean();
        Position3 logout = Position3.read(buffer);
        boolean hasDeath = buffer.readBoolean();
        Position3 death = Position3.read(buffer);
        Position3 home = Position3.read(buffer);

        WorldData worldData = null;
        if (buffer.readBoolean()) {
            buffer.readInt();
            worldData = WorldData.read(buffer);
        }

        return new World(
            id,
            customSpawn,
            spawn,
            hasLogout,
            logout,
            hasDeath,
            death,
            home,
            worldData
        );
    }

    @Override
    public void write(ByteArrayWriter writer) {
        writer.writeLong(id);
        writer.writeBoolean(customSpawn);
        writer.write(spawn);
        writer.writeBoolean(hasLogout);
        writer.write(logout);
        writer.writeBoolean(hasDeath);
        writer.write(death);
        writer.write(home);
        writer.writeBoolean(worldData != null);
        if (worldData != null) {
            writer.write(worldData);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (World) obj;
        return this.id == that.id &&
            this.customSpawn == that.customSpawn &&
            Objects.equals(this.spawn, that.spawn) &&
            this.hasLogout == that.hasLogout &&
            Objects.equals(this.logout, that.logout) &&
            this.hasDeath == that.hasDeath &&
            Objects.equals(this.death, that.death) &&
            Objects.equals(this.home, that.home) &&
            Objects.equals(this.worldData, that.worldData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customSpawn, spawn, hasLogout, logout, hasDeath, death, home, worldData);
    }

    @Override
    public String toString() {
        return "World[" +
            "id=" + id + ", " +
            "customSpawn=" + customSpawn + ", " +
            "spawn=" + spawn + ", " +
            "hasLogout=" + hasLogout + ", " +
            "logout=" + logout + ", " +
            "hasDeath=" + hasDeath + ", " +
            "death=" + death + ", " +
            "home=" + home + ", " +
            "worldData=" + worldData + ']';
    }

}
