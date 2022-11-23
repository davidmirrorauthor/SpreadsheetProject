package upc.softarch.spreadsheetProject;

public class TextCell extends Cell{
    public TextCell(String content){
        this.content=content;
    }
    @Override
    public String getCell() {
        return this.content;
    }

    @Override
    public boolean setCell(String content) {
        this.content=content;
        return true;
    }
}
