package upc.softarch.spreadsheetProject.Formula.Functions;

import upc.softarch.spreadsheetProject.Formula.FormulaOperand;

import java.util.List;

public class FormulaFunctionMAX extends FormulaFunction {
    public FormulaFunctionMAX(List<FormulaOperand> list_of_operands){
        super(list_of_operands);
    }
    @Override
    public Float getValue() {
        float max = Float.MIN_VALUE;
        for(int i=0; i<this.list_of_values.size(); i++){
            if(this.list_of_values.get(i) > max){
                max = this.list_of_values.get(i);
            }
        }
        return max;
    }
}
