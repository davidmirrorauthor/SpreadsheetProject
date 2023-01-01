package upc.softarch.spreadsheetProject.Formula;

import upc.softarch.spreadsheetProject.Formula.FormulaOperand;

public class FormulaConstantFloat extends FormulaOperand {
    public FormulaConstantFloat(Float value){
        this.value=value;
    };
    @Override
    public Float getValue() {
        return this.value;
    }
}
