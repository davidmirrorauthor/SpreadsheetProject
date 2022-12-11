package upc.softarch.spreadsheetProject;

public class FormulaFunctionMIN extends FormulaFunction {
    public FormulaFunctionMIN(String range) {
        super(range);
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
