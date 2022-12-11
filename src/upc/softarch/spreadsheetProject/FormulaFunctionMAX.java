package upc.softarch.spreadsheetProject;

public class FormulaFunctionMAX extends FormulaFunction {
    public FormulaFunctionMAX(String range) {
        super(range);
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
