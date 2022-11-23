package upc.softarch.spreadsheetProject;

import java.util.ArrayList;
import java.util.List;

public class Spreadsheet {
    public List<List<Cell>> cells = new ArrayList<>();
    public String file_name;

    public Spreadsheet(String file_name){
        this.file_name=file_name;
    }

    public String searchCell(String alphanumeric_position){
        return alphanumeric_position;
    }
    public boolean editCell(String cell_location){

        return true;
    }

    public boolean updateDependencies() {
        return false;
    }

    public boolean isCircularDependency(String content){
        return false;
    }
}
