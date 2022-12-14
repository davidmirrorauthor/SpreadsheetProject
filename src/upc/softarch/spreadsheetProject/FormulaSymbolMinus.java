package upc.softarch.spreadsheetProject;

public class FormulaSymbolMinus extends FormulaSymbol {
    public FormulaSymbolMinus(){};
    public FormulaSymbolMinus(FormulaOperand operand_2){
        this.children.add(new FormulaConstantFloat(0f));
        this.children.add(operand_2);
    }
    public FormulaSymbolMinus(FormulaOperand operand_1, FormulaOperand operand_2){
        this.children.add(operand_1);
        this.children.add(operand_2);
    }
    @Override
    public Float getValue() {
        Float result = this.children.get(0).getValue() -this.children.get(1).getValue();
        return result;
    }
}
