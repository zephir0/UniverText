package com.univertext.io.writer;

import java.io.IOException;
import java.io.OutputStream;

public interface FileOutputStreamProvider {
    OutputStream get(String path) throws IOException;
}
