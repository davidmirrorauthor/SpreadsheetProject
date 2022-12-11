package upc.softarch.spreadsheetProject;

import java.util.ArrayList;
import java.util.List;

abstract class FormulaOperand {
    protected List<FormulaOperand> children = new ArrayList<FormulaOperand>();
    public Float value;
    abstract Float getValue();
    protected void addChildren(FormulaOperand new_children){this.children.add(new_children);
    };
}
