package upc.softarch.spreadsheetProject;

public class FormulaConstantText extends FormulaOperand {
    public String text;

    public FormulaConstantText(String text){
        this.text=text;
    }

    @Override
    Float getValue() {
        return null;
    }
}
