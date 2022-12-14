package upc.softarch.spreadsheetProject;

import java.util.List;

public class FormulaFunctionMEAN extends FormulaFunction {
    public FormulaFunctionMEAN(List<FormulaOperand> list_of_operands){
        super(list_of_operands);
    }
    public FormulaFunctionMEAN(String range) {

        super(range);
    }
    @Override
    public Float getValue() {
        float sum=0f;
        for (Float value:this.list_of_values){
            sum+=value;
        }
        int count=this.list_of_values.size();
        return sum/count;
    }
}
