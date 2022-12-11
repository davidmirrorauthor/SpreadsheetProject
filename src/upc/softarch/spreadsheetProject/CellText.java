package upc.softarch.spreadsheetProject;

public class CellText extends Cell{
    public CellText(String content){
        this.content=content;
    }
    @Override
    public String getContent() {
        return this.content;
    }
    @Override
    public Float getValue() {
        return this.value;
    }
    @Override
    public boolean setCell(String content) {
        this.content=content;
        return true;
    }
}
