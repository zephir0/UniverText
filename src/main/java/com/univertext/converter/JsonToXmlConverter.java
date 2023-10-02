package com.univertext.converter;

import com.univertext.model.FileContent;
import org.json.JSONObject;
import org.json.XML;

import java.util.List;

public class JsonToXmlConverter implements FileConverter {
    @Override
    public FileContent convert(FileContent fileContent) {
        String jsonContent = String.join("\n", fileContent.getLines());
        JSONObject jsonObject = new JSONObject(jsonContent);
        String xml = XML.toString(jsonObject);
        return new FileContent(fileContent.getPath(), List.of(xml));
    }
}
