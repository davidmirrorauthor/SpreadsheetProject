package upc.softarch.spreadsheetProject;

public class FormulaFunctionSUM extends FormulaFunction {
    public FormulaFunctionSUM(String range) {
        super(range);
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
