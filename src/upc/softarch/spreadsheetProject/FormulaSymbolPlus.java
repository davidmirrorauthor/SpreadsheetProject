package upc.softarch.spreadsheetProject;

public class FormulaSymbolPlus extends FormulaSymbol {
    public FormulaSymbolPlus(){};
    public FormulaSymbolPlus(FormulaOperand operand_1, FormulaOperand operand_2){
        this.children.add(operand_1);
        this.children.add(operand_2);
    }
    @Override
    public Float getValue() {
        Float result = this.children.get(0).getValue() + this.children.get(1).getValue();
        return result;
    }
}
