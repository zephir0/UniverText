package com.univertext;

import com.univertext.io.reader.*;
import com.univertext.menu.FileMenuInterface;
import com.univertext.menu.factory.MenuFactory;
import com.univertext.model.FileType;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<FileType, FileReaderInterface> fileReaders = Map.of(
                FileType.TXT, new TextFileReader(),
                FileType.CSV, new CsvFileReader(),
                FileType.JSON, new JsonFileReader(),
                FileType.XML, new XmlFileReader()
        );

        Map<FileType, FileMenuInterface> fileMenus = Map.of(
                FileType.TXT, MenuFactory.createTextFileMenu(),
                FileType.CSV, MenuFactory.createCsvFileMenu(),
                FileType.JSON, MenuFactory.createJsonFileMenu(),
                FileType.XML, MenuFactory.createXmlFileMenu()
        );
        UniverTextCLI cli = new UniverTextCLI(fileReaders, fileMenus);
        cli.start();
    }
}