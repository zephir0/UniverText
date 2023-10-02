package com.univertext.menu.factory;

import com.univertext.analyzer.CsvDataAnalyzer;
import com.univertext.converter.*;
import com.univertext.editor.CsvFileEditor;
import com.univertext.editor.TextFileEditor;
import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.menu.*;

public class MenuFactory {
    public static FileMenuInterface createTextFileMenu() {
        return new TextFileMenu(new UniversalFileWriter(), new TextFileEditor(), new TextToCsvConverter());
    }

    public static FileMenuInterface createCsvFileMenu() {
        return new CsvFileMenu(new UniversalFileWriter(), new CsvFileEditor(), new CsvDataAnalyzer(), new CsvToTextConverter());
    }

    public static FileMenuInterface createJsonFileMenu() {
        return new JsonFileMenu(new UniversalFileWriter(), new JsonToXmlConverter(), new JsonToYamlConverter());
    }

    public static FileMenuInterface createXmlFileMenu() {
        return new XmlFileMenu(new XmlToJsonConverter(), new UniversalFileWriter());
    }
}
