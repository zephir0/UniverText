package com.univertext.io.writer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DefaultFileOutputStreamProvider implements FileOutputStreamProvider {
    @Override
    public OutputStream get(String path) throws FileNotFoundException {
        return new FileOutputStream(path);
    }
}
