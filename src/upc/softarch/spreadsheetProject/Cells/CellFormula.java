package upc.softarch.spreadsheetProject.Cells;
import upc.softarch.spreadsheetProject.Formula.FormulaOperand;
import upc.softarch.spreadsheetProject.Formula.FormulaParser;
import upc.softarch.spreadsheetProject.SpreadsheetManager;

import java.util.ArrayList;
import java.util.List;
public class CellFormula extends Cell {
    private FormulaOperand operation_tree;
    protected List<Cell> dependencies= new ArrayList<>(); //Dependant=Subscriber; Dependency=Publisher

    public CellFormula(String content, List<Integer> location, boolean first_time) {
        super(content, location);
        if(!first_time){
            FormulaParser parser = new FormulaParser();
            this.dependencies=parser.getDependenciesFromFormula(content);
            calculateValue();
        }
    }
    public List<Cell> getDependencies(){return this.dependencies;}
    public void calculateValue(){
        this.operation_tree=processFormula(this.content);
        this.value=executeFormula();
    }
    public FormulaOperand processFormula(String content){
        FormulaParser parser = new FormulaParser();
        return parser.parseFormula(content);
    }
    public Float executeFormula(){
        return operation_tree.getValue();
    }


}
