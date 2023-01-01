package upc.softarch.spreadsheetProject.Formula;

import upc.softarch.spreadsheetProject.Cells.Cell;
import upc.softarch.spreadsheetProject.Cells.CellFormula;
import upc.softarch.spreadsheetProject.SpreadsheetManager;

public class FormulaReference extends FormulaOperand{
    protected String reference;
    protected Cell cell;
    public FormulaReference(String reference){
        this.reference=reference;
        SpreadsheetManager spreadsheet_manager=SpreadsheetManager.getInstance();
        this.cell= spreadsheet_manager.spreadsheet.searchCell(reference);
        if (this.cell instanceof CellFormula){
            ((CellFormula) cell).calculateValue();
        }else{
            this.value= cell.getValue();
        }
    }
    @Override
    public Float getValue() {
        return this.cell.getValue();
    }
}
