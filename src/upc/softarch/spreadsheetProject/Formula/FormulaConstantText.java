package upc.softarch.spreadsheetProject.Formula;

import upc.softarch.spreadsheetProject.Formula.FormulaOperand;

public class FormulaConstantText extends FormulaOperand {
    public String text;
    public FormulaConstantText(String text){
        this.text=text;
    }
    @Override
    public Float getValue() {return null;}
}
