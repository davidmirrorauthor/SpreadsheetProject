package upc.softarch.spreadsheetProject;

public class FormulaError extends FormulaOperand {
    private String error;
    public FormulaError(String error){
        this.error=error;
    }
    @Override
    Float getValue() {
        return null;
    }
}
