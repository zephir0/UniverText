package com.univertext.io.reader;

import java.util.List;

public class XmlFileReader extends AbstractFileReader {

    @Override
    protected List<String> getExpectedFileTypes() {
        return List.of(".xml");
    }
}
