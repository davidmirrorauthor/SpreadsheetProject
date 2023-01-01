package upc.softarch.spreadsheetProject.Formula;

import upc.softarch.spreadsheetProject.Formula.FormulaOperand;

public class FormulaError extends FormulaOperand {
    private String error;
    public FormulaError(String error){
        this.error=error;
    }
    @Override
    public Float getValue() {
        return null;
    }
}
