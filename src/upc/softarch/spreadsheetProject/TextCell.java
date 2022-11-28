package upc.softarch.spreadsheetProject;

public class TextCell extends Cell{
    public TextCell(String content){
        this.content=content;
    }
    @Override
    public String getCellContent() {
        return this.content;
    }
    @Override
    public Float getCellValue() {
        return this.value;
    }
    @Override
    public boolean setCell(String content) {
        this.content=content;
        return true;
    }
}
