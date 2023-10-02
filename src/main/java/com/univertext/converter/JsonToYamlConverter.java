package com.univertext.converter;

import com.univertext.model.FileContent;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

public class JsonToYamlConverter implements FileConverter {
    @Override
    public FileContent convert(FileContent fileContent) {
        String jsonContent = String.join("\n", fileContent.getLines());
        JSONObject jsonObject = new JSONObject(jsonContent);
        Map<String, Object> jsonMap = jsonObject.toMap();

        Yaml yaml = new Yaml();
        String yamlContent = yaml.dumpAsMap(jsonMap);
        return new FileContent(fileContent.getPath(), List.of(yamlContent));
    }
}
