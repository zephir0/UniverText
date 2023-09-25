package model;

import java.util.List;

public class FileContent {
    private String path;
    private List<String> lines;

    public FileContent(String path,
                       List<String> lines) {
        this.path = path;
        this.lines = lines;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
