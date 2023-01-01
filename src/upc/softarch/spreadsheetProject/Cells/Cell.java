package upc.softarch.spreadsheetProject.Cells;

import upc.softarch.spreadsheetProject.SpreadsheetManager;

import java.util.ArrayList;
import java.util.List;

public class Cell{
    protected String content = "";
    protected Float value;
    protected List<Integer> location = new ArrayList<>();
    protected List<CellFormula> dependants = new ArrayList<>();
    protected String alphanumeric_location;

    public String getContent(){return this.content;}
    public  Float getValue(){return this.value;}
    public List<CellFormula> getDependants(){return this.dependants;} //Dependant=Subscriber; Dependency=Publisher
    public Cell(String content, List<Integer> location){
        this.content=content;
        this.location=location;
        SpreadsheetManager spreadsheet_manager= SpreadsheetManager.getInstance();
        this.alphanumeric_location=spreadsheet_manager.spreadsheet.cellLocation2AlphanumericCode(location);
    }
    public void addDependant(CellFormula dependant){this.dependants.add(dependant);}
    public void removeDependant(CellFormula dependant){this.dependants.remove(dependant);}
    public void updateDependants(){
        for(CellFormula dependant:this.dependants){
            dependant.calculateValue();
            dependant.updateDependants();
        }
    }
    public void notifyCircularDependency(){};
}
