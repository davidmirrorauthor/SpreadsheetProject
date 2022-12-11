package upc.softarch.spreadsheetProject;

import java.util.Collections;

public class FormulaFunctionMEDIAN extends FormulaFunction {
    public FormulaFunctionMEDIAN(String range) {
        super(range);
    }
    @Override
    public Float getValue() {
        if (this.list_of_values.size() == 1) {
            return this.list_of_values.get(0);
        }
        Collections.sort(this.list_of_values);
        int middle = this.list_of_values.size() / 2;
        if (this.list_of_values.size() % 2 == 1) {
            return this.list_of_values.get(middle);
        } else {
            Float first_value=this.list_of_values.get(middle - 1);
            Float second_value=this.list_of_values.get(middle);
            return ( first_value + second_value ) / 2;
        }
    }
}
