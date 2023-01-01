package upc.softarch.spreadsheetProject.Formula;
import parser.AbstractFactory;
import parser.AbstractNode;
import parser.ExpressionBuilder;
import upc.softarch.spreadsheetProject.Cells.Cell;
import upc.softarch.spreadsheetProject.SpreadsheetManager;

import java.util.ArrayList;
import java.util.List;
public class FormulaParser {
    private ExpressionBuilder callBuilder(String formula){
        AbstractFactory factory;
        ExpressionBuilder builder;
        factory = new FormulaParserFactory();
        builder = new ExpressionBuilder(factory);
        builder.buildExpression(formula.replace("=", ""));
        return builder;
    }
    public FormulaOperand parseFormula(String formula){
        ExpressionBuilder builder=callBuilder(formula);
        AbstractNode expression = builder.getExpression();
        FormulaOperand operation_tree=(FormulaOperand) expression;
        return operation_tree;
    }
    public List<Cell> getDependenciesFromFormula(String formula){
        ExpressionBuilder builder = callBuilder(formula);
        List<String> references = builder.getCellReferences();
        List<Cell> dependencies = new ArrayList<>();
        if (references.size()>0){
            SpreadsheetManager spreadsheet_manager=SpreadsheetManager.getInstance();
            for (String reference:references){
                Cell cell=spreadsheet_manager.spreadsheet.searchCell(reference);
                dependencies.add(cell);
            }
        }
        return dependencies;
    }
}
