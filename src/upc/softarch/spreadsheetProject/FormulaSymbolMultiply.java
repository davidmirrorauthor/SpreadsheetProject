package upc.softarch.spreadsheetProject;

import parser.AbstractNode;

public class FormulaSymbolMultiply extends FormulaSymbol {
    public FormulaSymbolMultiply(){};
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
