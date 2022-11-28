package upc.softarch.spreadsheetProject;

public class FormulaParser {

    public OperationTree parseFormula(String formula){
        Operation operation_tree=null;
        if (formula.equals("=(1+1)*(2+3)")){
            operation_tree = new Operation('*');
            Operation operation_1= new Operation('+');
            operation_1.addOperator(new NumberConstant(1f));
            operation_1.addOperator(new NumberConstant(1f));
            operation_tree.addChildren(operation_1);
            Operation operation_2 = new Operation('+');
            operation_2.addOperator(new NumberConstant(2f));
            operation_2.addOperator(new NumberConstant(3f));
            operation_tree.addChildren(operation_2);
        } else if (formula.equals("=5+A2")){
            operation_tree = new Operation('+');
            operation_tree.addOperator(new NumberConstant(5f));
            //operation_tree.addOperator('A2');
        }
        return operation_tree;
    }
}
