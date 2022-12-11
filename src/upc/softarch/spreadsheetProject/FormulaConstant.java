package upc.softarch.spreadsheetProject;

public class FormulaConstant extends FormulaOperand {
    public FormulaConstant(Float value){
        this.value=value;
    };
    @Override
    public Float getValue() {
        return this.value;
    }
}
