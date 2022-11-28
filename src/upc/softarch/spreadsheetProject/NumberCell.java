package upc.softarch.spreadsheetProject;

public class NumberCell extends Cell{

    public NumberCell(String content){
        this.content=content;
        this.value=Float.parseFloat(content);
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
        this.value=Float.parseFloat(content);
        return true;
    }
    public float getValue(){
        return this.value;
    };
}
