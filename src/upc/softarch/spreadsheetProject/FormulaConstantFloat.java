package upc.softarch.spreadsheetProject;

public class FormulaConstantFloat extends FormulaOperand {
    public FormulaConstantFloat(Float value){
        this.value=value;
    };
    @Override
    public Float getValue() {
        return this.value;
    }
}
