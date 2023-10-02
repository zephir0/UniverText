package com.univertext.converter;

import com.univertext.model.FileContent;
import org.json.XML;

import java.util.List;

public class XmlToJsonConverter implements FileConverter {
    @Override
    public FileContent convert(FileContent fileContent) {
        String xmlContent = String.join("\n", fileContent.getLines());
        String jsonObject = XML.toJSONObject(xmlContent).toString();
        return new FileContent(fileContent.getPath(), List.of(jsonObject));
    }
}
