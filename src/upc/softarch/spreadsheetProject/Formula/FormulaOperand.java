package upc.softarch.spreadsheetProject.Formula;

import parser.AbstractNode;

import java.util.ArrayList;
import java.util.List;

public abstract class FormulaOperand implements AbstractNode {
    protected List<FormulaOperand> children = new ArrayList<FormulaOperand>();
    protected Float value=null;
    public abstract Float getValue();
    public void addChildren(FormulaOperand new_children){this.children.add(new_children);}
    public void removeChildren(FormulaOperand children){this.children.remove(children);}
}
