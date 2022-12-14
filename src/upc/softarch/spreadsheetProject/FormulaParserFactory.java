package upc.softarch.spreadsheetProject;

import parser.AbstractFactory;
import parser.AbstractNode;

import java.util.List;

public class FormulaParserFactory implements AbstractFactory {
    @Override
    public AbstractNode buildOperator(char op, AbstractNode left, AbstractNode right) {

        FormulaOperand opLeft = (FormulaOperand) left;
        FormulaOperand opRight = (FormulaOperand) right;

        if (op =='+'){
            return new FormulaSymbolPlus(opLeft, opRight);
        } else if (op=='-') {
            return new FormulaSymbolMinus(opLeft, opRight);
        }else if (op=='*'){
            return new FormulaSymbolMultiply(opLeft, opRight);
        }else if (op=='/'){
            return new FormulaSymbolDivide(opLeft, opRight);
        }
        return null;
    }

    @Override
    public AbstractNode buildFunction(String name, AbstractNode[] parameters) {
        FormulaOperand[] op_array = (FormulaOperand[]) parameters;
        List<FormulaOperand> op_list = List.of(op_array);
        if (name.equals("MAX")){
            return new FormulaFunctionMAX(op_list);
        }else if (name.equals("MIN")){
            return new FormulaFunctionMIN(op_list);
        } else if (name.equals("MEAN")){
            return new FormulaFunctionMEAN(op_list);
        } else if (name.equals("MEDIAN")){
            return new FormulaFunctionMEDIAN(op_list);
        } else if (name.equals("SUM")){
            return new FormulaFunctionSUM(op_list);
        }
        return null;
    }

    @Override
    public AbstractNode buildNumberConstant(double raw_number) {
        Float number = (float) raw_number;
        return new FormulaConstantFloat(number);
    }

    @Override
    public AbstractNode buildTextConstant(String text) {
        return new FormulaConstantText(text);
    }

    @Override
    public AbstractNode buildCellReference(String reference) {
        return new FormulaReference(reference);
    }

    @Override
    public AbstractNode buildError(String detail) {
        return new FormulaError(detail);
    }
}