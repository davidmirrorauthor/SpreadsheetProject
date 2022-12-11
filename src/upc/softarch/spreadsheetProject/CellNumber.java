package upc.softarch.spreadsheetProject;

public class CellNumber extends Cell{

    public CellNumber(String content){
        this.content=content;
        this.value=Float.parseFloat(content);
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
        this.value=Float.parseFloat(content);
        return true;
    }
}
