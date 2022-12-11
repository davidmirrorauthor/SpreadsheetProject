package upc.softarch.spreadsheetProject;

public class FormulaFunctionMEAN extends FormulaFunction {
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
