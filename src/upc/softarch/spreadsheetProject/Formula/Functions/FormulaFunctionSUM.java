package upc.softarch.spreadsheetProject.Formula.Functions;

import upc.softarch.spreadsheetProject.Formula.FormulaOperand;

import java.util.List;

public class FormulaFunctionSUM extends FormulaFunction {
    public FormulaFunctionSUM(List<FormulaOperand> list_of_operands){
        super(list_of_operands);
    }
    @Override
    public Float getValue() {
        float sum=0f;
        for (Float value:this.list_of_values){
            sum+=value;
        }
        return sum;
    }
}
