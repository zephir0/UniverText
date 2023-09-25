package editor;

import model.FileContent;
import org.json.JSONArray;
import org.json.JSONObject;
import printer.ConsolePrinter;

import java.util.List;

public class JsonFileEditor extends AbstractFileEditor {
    public void formatJson(FileContent fileContent) {
        JSONArray jsonArray = new JSONArray(String.join("", fileContent.getLines()));
        String formattedJson = jsonArray.toString(4);
        fileContent.setLines(List.of(formattedJson.split("\n")));
        ConsolePrinter.print("JSON formatted.");
    }

    public void addJsonElement(FileContent fileContent,
                               String jsonElement) {
        JSONArray jsonArray = new JSONArray(String.join("", fileContent.getLines()));
        jsonArray.put(new JSONObject(jsonElement));
        String updatedJson = jsonArray.toString(4);
        fileContent.setLines(List.of(updatedJson.split("\n")));
        ConsolePrinter.print("JSON element added.");
    }
}