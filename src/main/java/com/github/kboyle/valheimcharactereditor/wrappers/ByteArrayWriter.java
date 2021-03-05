package com.github.kboyle.valheimcharactereditor.wrappers;

import com.github.kboyle.valheimcharactereditor.entities.Writeable;
import com.github.kboyle.valheimcharactereditor.exceptions.IORuntimeException;
import com.google.common.hash.Hashing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Consumer;

public class ByteArrayWriter {
    private final ByteArrayOutputStream stream;

    public ByteArrayWriter() {
        this.stream = new ByteArrayOutputStream();
    }

    public void writeInt(int i) {
        write0(getBytes(i));
    }

    private byte[] getBytes(int i) {
        return new byte[]{ (byte) i, (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24) };
    }

    public void writeLong(long l) {
        write0(new byte[] {
            (byte) l,
            (byte) (l >> 8),
            (byte) (l >> 16),
            (byte) (l >> 24),
            (byte) (l >> 32),
            (byte) (l >> 40),
            (byte) (l >> 48),
            (byte) (l >> 56)
        });
    }

    private void write0(byte[] bytes) {
        try {
            if (bytes.length == 0) {
                return;
            }
            stream.write(bytes);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public void writeBoolean(boolean bool) {
        stream.write(bool ? 1 : 0);
    }

    public void writeFloat(float f) {
        int bits = Float.floatToIntBits(f);
        writeInt(bits);
    }

    public void write(Writeable writeable) {
        writeable.write(this);
    }

    public void write(Writeable[] writeables) {
        ByteArrayWriter writer = new ByteArrayWriter();
        for (Writeable writeable : writeables) {
            writer.write(writeable);
        }

        write(writer.stream.toByteArray());
    }

    public void writeWithLength(Consumer<ByteArrayWriter> writerConsumer) {
        ByteArrayWriter writer = new ByteArrayWriter();
        writerConsumer.accept(writer);
        write(writer.stream.toByteArray());
    }

    public void write(byte[] bytes) {
        writeInt(bytes.length);
        write0(bytes);
    }

    public void writeList(List<? extends Writeable> writeables) {
        writeInt(writeables.size());
        for (Writeable writeable : writeables) {
            write(writeable);
        }
    }

    public void writeStringList(List<String> writeables) {
        writeInt(writeables.size());
        for (String writeable : writeables) {
            writeString(writeable);
        }
    }

    public void writeString(String str) {
        stream.write((byte) str.length());
        write0(str.getBytes(StandardCharsets.UTF_8));
    }

    public void writeBooleanArray(boolean[] array) {
        for (boolean b : array) {
            writeBoolean(b);
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    public byte[] toBytes() {
        byte[] writtenData = stream.toByteArray();
        byte[] output = new byte[writtenData.length + 72];

        int dataLength = writtenData.length;
        output[0] = (byte) dataLength;
        output[1] = (byte) (dataLength >> 8);
        output[2] = (byte) (dataLength >> 16);
        output[3] = (byte) (dataLength >> 24);

        output[writtenData.length + 4] = 64;
        output[writtenData.length + 6] = 0;
        output[writtenData.length + 7] = 0;
        output[writtenData.length + 8] = 0;

        Hashing.sha512().hashBytes(writtenData).writeBytesTo(output, dataLength + 8, 64);
        System.arraycopy(writtenData, 0, output, 4, dataLength);

        return output;
    }
}
