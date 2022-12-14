package upc.softarch.spreadsheetProject;

import parser.AbstractNode;

import java.util.ArrayList;
import java.util.List;

abstract class FormulaOperand implements AbstractNode {
    protected List<FormulaOperand> children = new ArrayList<FormulaOperand>();
    public Float value;
    abstract Float getValue();
    public void addChildren(FormulaOperand new_children){this.children.add(new_children);
    };
}
