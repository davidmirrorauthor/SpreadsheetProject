package upc.softarch.spreadsheetProject;

import parser.AbstractFactory;
import parser.AbstractNode;
import parser.ExpressionBuilder;

import java.util.List;

public class FormulaParser {

    public FormulaOperand parseFormula(String formula){
        System.out.println(formula);
        AbstractFactory factory;
        ExpressionBuilder builder;
        factory = new FormulaParserFactory();
        builder = new ExpressionBuilder(factory);
        builder.buildExpression(formula.replace("=", ""));
        AbstractNode expression = builder.getExpression();
        List<String> references = builder.getCellReferences();
        FormulaOperand operation_tree=(FormulaOperand) expression;
        return operation_tree;
    }
}
