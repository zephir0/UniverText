package editor;

import model.FileContent;

public interface EditorInterface {
    void sortColumn(FileContent fileContent,
                    int columnIndex);

    void removeDuplicates(FileContent fileContent);
}
