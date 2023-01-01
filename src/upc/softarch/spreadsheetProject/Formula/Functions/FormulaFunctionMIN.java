package upc.softarch.spreadsheetProject.Formula.Functions;

import upc.softarch.spreadsheetProject.Formula.FormulaOperand;

import java.util.List;

public class FormulaFunctionMIN extends FormulaFunction {
    public FormulaFunctionMIN(List<FormulaOperand> list_of_operands){
        super(list_of_operands);
    }
    @Override
    public Float getValue() {
        float min = Float.MAX_VALUE;
        for(int i=0; i<this.list_of_values.size(); i++){
            if(this.list_of_values.get(i) < min){
                min = this.list_of_values.get(i);
            }
        }
        return min;
    }
}
