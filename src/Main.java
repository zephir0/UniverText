import menu.CsvFileMenu;
import menu.FileMenuInterface;
import menu.TextFileMenu;
import model.FileType;
import io.reader.CsvFileReader;
import io.reader.FileReaderInterface;
import io.reader.TextFileReader;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<FileType, FileReaderInterface> fileReaders = Map.of(
                FileType.TEXT, new TextFileReader(),
                FileType.CSV, new CsvFileReader()
        );

        Map<FileType, FileMenuInterface> fileMenus = Map.of(
                FileType.TEXT, new TextFileMenu(),
                FileType.CSV, new CsvFileMenu()
        );

        UniverTextCLI cli = new UniverTextCLI(fileReaders, fileMenus);
        cli.start();
    }
}
