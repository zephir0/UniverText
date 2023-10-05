package com.univertext.editor;

import com.univertext.model.FileContent;
import com.univertext.printer.ConsolePrinter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class JsonFileEditor extends AbstractFileEditor {
    public void formatJson(FileContent fileContent) {
        JSONArray jsonArray = new JSONArray(String.join("", fileContent.getLines()));
        String formattedJson = jsonArray.toString(4);
        fileContent.setLines(List.of(formattedJson.split("\n")));
        ConsolePrinter.printSuccess("JSON formatted.");
    }

    public void addJsonElement(FileContent fileContent,
                               String jsonElement) {
        JSONArray jsonArray = new JSONArray(String.join("", fileContent.getLines()));
        jsonArray.put(new JSONObject(jsonElement));
        String updatedJson = jsonArray.toString(4);
        fileContent.setLines(List.of(updatedJson.split("\n")));
        ConsolePrinter.printSuccess("JSON element added.");
    }
}