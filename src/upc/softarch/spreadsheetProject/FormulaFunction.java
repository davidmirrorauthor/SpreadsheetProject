package upc.softarch.spreadsheetProject;

import java.util.ArrayList;
import java.util.List;

abstract class FormulaFunction extends FormulaOperand{
    public String range;
    public List<Float> list_of_values = new ArrayList<Float>();
    public FormulaFunction(List<FormulaOperand> list_of_operands){
        for(FormulaOperand operand:list_of_operands){
            this.list_of_values.add(operand.getValue());
        }
    }
    public FormulaFunction(String range){
        this.range=range;
        getOperandsFromRange();
    }
    protected void getOperandsFromRange(){
        SpreadsheetManager spreadsheet_manager = SpreadsheetManager.getInstance();
        if(this.range.contains(";")){
            List<String> cell_locations= List.of(this.range.split(";"));
            for (String cell_location:cell_locations){
                this.list_of_values.add(spreadsheet_manager.spreadsheet.getValue(cell_location));
            }
        } else if (this.range.contains(":")) {
            List<String> references = List.of(this.range.split(":"));
            String first_reference=references.get(0);
            List<Integer> first_location=spreadsheet_manager.spreadsheet.getCellLocationWithAlphanumericCode(first_reference);
            String second_reference=references.get(1);
            List<Integer> second_location=spreadsheet_manager.spreadsheet.getCellLocationWithAlphanumericCode(second_reference);
            for (int row=first_location.get(0);row<second_location.get(0)+1;row++){
                for (int column=first_location.get(1);column<second_location.get(1)+1;column++){
                    this.list_of_values.add(spreadsheet_manager.spreadsheet.getValue(row, column));
                }
            }
        }
    }
}
