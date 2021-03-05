package com.github.kboyle.valheimcharactereditor.wrappers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ByteArrayReader {
    private final ByteBuffer buffer;

    public ByteArrayReader(byte[] bytes) {
        this.buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
    }

    public int readInt() {
        System.out.println("Reading int at: " + buffer.position());
        return buffer.getInt();
    }

    public long readLong() {
        System.out.println("Reading long at: " + buffer.position());
        return buffer.getLong();
    }

    public boolean readBoolean() {
//        System.out.println("Reading boolean at: " + buffer.position());
        return buffer.get() != 0;
    }

    public float readFloat() {
        System.out.println("Reading float at: " + buffer.position());
        return buffer.getFloat();
    }

    public void read(byte[] destination, int length) {
        System.out.println("Reading " + length + " bytes at: " + buffer.position());
        buffer.get(destination, 0, length);
    }

    public String readUTF8ByteString() {
        int length = Byte.toUnsignedInt(buffer.get());
        System.out.println("Reading " + length + " string at: " + buffer.position());

        if (length == 0) {
            return "";
        }

        byte[] str = new byte[length];
        read(str, length);
        return new String(str, StandardCharsets.UTF_8);
    }

    public <T> List<T> readList(Function<ByteArrayReader, T> itemFunc) {
        int length = buffer.getInt();
        System.out.println("Reading " + length + " items at: " + buffer.position());

        List<T> items = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            items.add(itemFunc.apply(this));
        }

        return items;
    }

    public boolean[] readBooleanArray(int length) {
        boolean[] result = new boolean[length];
        for (int i = 0; i < length; i++) {
            result[i] = readBoolean();
        }

        return result;
    }
}
