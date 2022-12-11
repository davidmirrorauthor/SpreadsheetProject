package upc.softarch.spreadsheetProject;

import java.io.IOException;

public class FormulaReference extends FormulaOperand{
    private String reference;
    private Cell cell;
    public FormulaReference(String reference){
        this.reference=reference;
        SpreadsheetManager spreadsheet_manager=SpreadsheetManager.getInstance();
        this.cell= spreadsheet_manager.spreadsheet.searchCell(reference);
    }
    public FormulaReference(Cell cell){
        this.cell=cell;
    }
    @Override
    public Float getValue() {
        return this.cell.getValue();
    }
}
