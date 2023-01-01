package upc.softarch.spreadsheetProject.Formula.Symbols;

import upc.softarch.spreadsheetProject.Formula.FormulaOperand;

public class FormulaSymbolMultiply extends FormulaSymbol {
    public FormulaSymbolMultiply(FormulaOperand operand_1, FormulaOperand operand_2){
        this.children.add(operand_1);
        this.children.add(operand_2);
    }
    @Override
    public Float getValue() {
        Float result = this.children.get(0).getValue() * this.children.get(1).getValue();
        return result;
    }
}
