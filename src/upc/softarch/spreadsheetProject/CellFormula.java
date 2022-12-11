package upc.softarch.spreadsheetProject;

public class CellFormula extends Cell {
    FormulaOperand operation_tree;
    public CellFormula(String content){
        this.content=content;
    }
    public void calculateValue(){
        this.operation_tree=processFormula(this.content);
        this.value=executeFormula();
    }
    @Override
    public String getContent() {return this.content;}
    @Override
    public Float getValue() {return this.value;}
    @Override
    public boolean setCell(String content) {
        this.content=content;
        return true;
    }
    public FormulaOperand processFormula(String content){
        FormulaParser parser = new FormulaParser();
        return parser.parseFormula(content);
    }
    public Float executeFormula(){
        return operation_tree.getValue();
    }

}
