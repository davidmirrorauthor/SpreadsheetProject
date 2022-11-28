package upc.softarch.spreadsheetProject;

import java.util.ArrayList;
import java.util.List;

public class Operation implements OperationTree {
    private char operation_code;
    private List<OperationTree> children =new ArrayList<OperationTree>();
    private List<NumberConstant> operators = new ArrayList<NumberConstant>();
    public Operation(char operation_code){
        this.operation_code=operation_code;
    }
    public void addOperator(NumberConstant operator){
        this.operators.add(operator);
    }
    public float computeSUM() {
        return 0;
    }
    public float computeMIN() {
        return 0;
    }
    public float computeMAX() {
        return 0;
    }
    public float computeMEAN() {
        return 0;
    }

    @Override
    public float calculateOperation() {
        float first_value=0.0f;
        float second_value=0.0f;
        float result=0.0f;
        if(this.children.size()==2){
            first_value = this.children.get(0).calculateOperation();
            second_value = this.children.get(1).calculateOperation();
        }else if(this.children.size()==1 && this.operators.size()==1){
            first_value = this.children.get(0).calculateOperation();
            second_value = this.operators.get(0).getValue();
        }else{
            first_value = this.operators.get(0).getValue();
            second_value = this.operators.get(1).getValue();
        }
        if (operation_code=='+'){
            result= first_value + second_value;
        } else if(operation_code=='*'){
            result= first_value*second_value;
        } else if(operation_code=='-'){
            result= first_value-second_value;
        } else if (operation_code=='/'){
            result= first_value/second_value;
        }
        return result;
    }
    public void addChildren(OperationTree component){
        this.children.add(component);
    }

    public void removeChildren(OperationTree component){
        this.children.remove(component);
    }
    public List<OperationTree> getChildren(){
        return this.children;
    }
}
