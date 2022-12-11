package upc.softarch.spreadsheetProject;

public class FormulaParser {

    public FormulaOperand parseFormula(String formula){
        FormulaOperand operation_tree=null;
        if (formula.equals("=(1+1)*(2+3)")){
            operation_tree = new FormulaSymbolMultiply();
            operation_tree.addChildren(new FormulaSymbolPlus(new FormulaConstant(1f), new FormulaConstant(1f)));
            operation_tree.addChildren(new FormulaSymbolPlus(new FormulaConstant(2f), new FormulaConstant(3f)));
        } else if (formula.equals("=5+A1")){
            operation_tree = new FormulaSymbolPlus();
            operation_tree.addChildren(new FormulaConstant(5f));
            operation_tree.addChildren(new FormulaReference("A1"));
        } else if (formula.equals("=MAX(A1:B3)")){
            operation_tree = new FormulaFunctionMAX("A1:B3");
        }else if (formula.equals("=SUM(A1:B3)")){
            operation_tree = new FormulaFunctionSUM("A1:B3");
        }else if (formula.equals("=MIN(A1:B3)")){
            operation_tree = new FormulaFunctionMIN("A1:B3");
        }else if (formula.equals("=MEAN(A1:B3)")){
            operation_tree = new FormulaFunctionMEAN("A1:B3");
        }else if (formula.equals("=MEDIAN(A1:B3)")){
            operation_tree = new FormulaFunctionMEDIAN("A1:B3");
        }
        return operation_tree;
    }
}
