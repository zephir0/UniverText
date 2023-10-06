package com.univertext.menu;

import com.univertext.analyzer.CsvFileAnalyzer;
import com.univertext.analyzer.TextFileAnalyzer;
import com.univertext.converter.*;
import com.univertext.editor.CsvFileEditor;
import com.univertext.editor.TextFileEditor;
import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.menu.csv.CsvFileAnalyzerMenu;
import com.univertext.menu.csv.CsvFileEditorMenu;
import com.univertext.menu.csv.CsvFileMenu;
import com.univertext.menu.json.JsonFileMenu;
import com.univertext.menu.text.TextFileAnalyzerMenu;
import com.univertext.menu.text.TextFileEditorMenu;
import com.univertext.menu.text.TextFileMenu;
import com.univertext.menu.xml.XmlFileMenu;

public class MenuFactory {
    public static FileMenuInterface createTextFileMenu() {
        return new TextFileMenu(new UniversalFileWriter(),
                new TextFileAnalyzerMenu(new TextFileAnalyzer()),
                new TextFileEditorMenu(new TextFileEditor(), new UniversalFileWriter()),
                new TextToCsvConverter());
    }

    public static FileMenuInterface createCsvFileMenu() {
        return new CsvFileMenu(new UniversalFileWriter(),
                new CsvFileAnalyzerMenu(new CsvFileAnalyzer()),
                new CsvFileEditorMenu(new CsvFileEditor(), new UniversalFileWriter()),
                new CsvToTextConverter());
    }

    public static FileMenuInterface createJsonFileMenu() {
        return new JsonFileMenu(new UniversalFileWriter(), new JsonToXmlConverter(), new JsonToYamlConverter());
    }

    public static FileMenuInterface createXmlFileMenu() {
        return new XmlFileMenu(new XmlToJsonConverter(), new UniversalFileWriter());
    }
}
