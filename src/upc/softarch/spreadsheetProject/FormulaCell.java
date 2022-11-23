package upc.softarch.spreadsheetProject;

public class FormulaCell extends Cell {
    public FormulaCell(String content){
        this.content=content;
        //this.value = Float(content); //Aquí iría el parser y la generación de resultados
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
    }
    public float executeFormula(String content){
        return this.value;
    }
}
