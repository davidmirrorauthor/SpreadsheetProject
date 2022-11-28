package upc.softarch.spreadsheetProject;

public class FormulaCell extends Cell {
    OperationTree operation_tree=null;
    public FormulaCell(String content){
        this.content=content;
        this.operation_tree=processFormula(content);
        this.value=executeFormula();
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
    public OperationTree processFormula(String content){
        FormulaParser parser = new FormulaParser();
        return parser.parseFormula(content);
    }
    public Float executeFormula(){
        return operation_tree.calculateOperation();
    }
}
