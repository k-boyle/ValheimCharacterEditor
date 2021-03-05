package com.github.kboyle.valheimcharactereditor.entities;

import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayWriter;

import java.util.List;

public interface Writeable {
    void write(ByteArrayWriter writer);

    class WritableListWrapper<T extends Writeable> implements Writeable {
        private final List<T> list;

        public WritableListWrapper(List<T> list) {
            this.list = list;
        }

        @Override
        public void write(ByteArrayWriter writer) {
            writer.writeList(list);
        }
    }
}
