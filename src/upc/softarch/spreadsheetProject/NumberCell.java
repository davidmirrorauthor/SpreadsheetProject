package upc.softarch.spreadsheetProject;

public class NumberCell extends Cell{

    public NumberCell(String content){
        this.content=content;
        this.value=Float.parseFloat(content);
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
    public float getValue(){
        return this.value;
    };
}
