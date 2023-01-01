package upc.softarch.spreadsheetProject;
import upc.softarch.spreadsheetProject.Cells.Cell;
import upc.softarch.spreadsheetProject.Cells.CellFormula;
import upc.softarch.spreadsheetProject.Cells.CellText;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static upc.softarch.spreadsheetProject.Spreadsheet.createCellWithString;

public class SpreadsheetManager {
    private int num_default_rows = 10;
    private int num_default_columns = 10;
    private String current_directory = System.getProperty("user.dir");
    public static Spreadsheet spreadsheet;
    public static SpreadsheetManager instance;
    private SpreadsheetManager() {};
    public static SpreadsheetManager getInstance(){
        if (instance==null){
            instance = new SpreadsheetManager();
        }
        return instance;
    }
    public List<String> getExistingSpreadsheets(){
        File f = new File(this.current_directory);
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File f, String name) {
                return name.endsWith(".txt");
            }
        };
        List<String> file_list=new ArrayList<>();
        file_list.addAll(Arrays.asList(f.list(filter)));
        return file_list;
    }
    public boolean checkIfSpreadsheetNameExists(String new_file_name){
        List<String> files_available = getExistingSpreadsheets();
        for (String file_name : files_available){
            if (new_file_name.equals(file_name) || (new_file_name + ".txt").equals(file_name)){
                return false;
            }
        }
        return true;
    }
    public boolean createSpreadsheet (String file_name) throws IOException {
        if (! file_name.contains(".txt")){
            file_name=file_name+ ".txt";
        }
        boolean file_created = false;
        boolean is_name_available=checkIfSpreadsheetNameExists(file_name);
        if (is_name_available) {
            File file = new File(file_name);
            file_created = file.createNewFile();
            PrintWriter writer = new PrintWriter(file_name, "UTF-8");
            for(int row=0; row<this.num_default_rows;row++){
                StringBuilder line= new StringBuilder();
                for (int column = 0;column<this.num_default_columns;column++){
                    line.append(";");
                }
                writer.println(line);
            }
            writer.close();
            this.spreadsheet=loadSpreadsheet(file_name);
        }
        return file_created;
    }
    public boolean saveSpreadsheet() throws IOException {
        StringBuilder content = new StringBuilder();
        for (List<Cell> row: this.spreadsheet.getCells()){
            for (Cell cell: row){
                content.append(cell.getContent());
                content.append(";");
            }
            content.append("\n\r");
        }
        FileWriter file = new FileWriter(this.spreadsheet.getFileName(), false);
        file.write(content.toString());
        file.close();
        return true;
    }
    private void fillSpreadsheet(String file_str, boolean first_time){
        int aux_row=0;
        int aux_column=0;
        List<Cell> row_vector = new ArrayList<>();
        StringBuilder variable= new StringBuilder();
        for (char character : file_str.toCharArray()){
            if (character==';'){
                List<Integer> location= new ArrayList<>();
                location.add(aux_row);
                location.add(aux_column);
                if (first_time){
                    Cell cell=createCellWithString(variable.toString(),location, first_time);
                    row_vector.add(cell);
                }else {
                    editCell(location,variable.toString());
                }
                variable = new StringBuilder();
                aux_column+=1;
            } else if (character=='\r' || character=='\n') {
                if (first_time) {
                    this.spreadsheet.getCells().add(row_vector);
                }
                row_vector = new ArrayList<>();
                aux_column=0;
                aux_row+=1;
            }else if(character=='\n'){

            }else{
                variable.append(character);
            }
        }
    }
    public Spreadsheet loadSpreadsheet(String spreadsheet_name) throws IOException {
        this.spreadsheet = new Spreadsheet(spreadsheet_name);
        FileReader fr = new FileReader(spreadsheet_name);
        int i;
        StringBuilder string_builder = new StringBuilder();
        while ((i = fr.read()) != -1){
            string_builder.append((char)i);
        }
        String file_str=string_builder.toString();
        file_str=file_str.replace("\r","\n");
        file_str=file_str.replace("\n\n","\n");
        fillSpreadsheet(file_str,true);
        fillSpreadsheet(file_str,false);
        return spreadsheet;
    }
    public boolean deleteSpreadsheet(String file_name){
        if (this.spreadsheet!=null && file_name.equals(this.spreadsheet.getFileName())){
            this.spreadsheet=null;
        }
        File f= new File(file_name);
        if(f.exists()){
            if(f.delete()){
                return true;
            }
        }
        return false;
    }
    public boolean showSpreadsheet(){
        if (this.spreadsheet!=null){
            for (List<Cell> row_cell:this.spreadsheet.getCells()){
                List<String> contents = new ArrayList<>();
                for (Cell cell:row_cell){
                    if(cell.getValue()== null){
                        contents.add(cell.getContent());
                    }else{
                        contents.add(String.valueOf(cell.getValue()));
                    }
                }
                System.out.println(contents);
            }
            return true;
        }else{
            return false;
        }
    }
    public boolean editCell(List<Integer> location,String new_content){
        Cell new_cell= createCellWithString(new_content, location, false);
        if (location.get(0)<this.spreadsheet.getCells().size() && location.get(1)<this.spreadsheet.getCells().get(0).size()) {
            this.spreadsheet.getCells().get(location.get(0)).set(location.get(1), new_cell);
            return true;
        }else{
            return false;
        }
    }
    public boolean editCell(String alphanumeric_location,String new_content){
        List<Integer> location=this.spreadsheet.alphanumericCode2CellLocation(alphanumeric_location);
        Cell new_cell= createCellWithString(new_content, location, false);
        Cell old_cell=this.spreadsheet.getCell(location);
        if (location.get(0)<this.spreadsheet.getCells().size() && location.get(1)<this.spreadsheet.getCells().get(0).size()) {
            if(old_cell instanceof CellFormula && ((CellFormula) old_cell).getDependencies().size()>0){
                for (Cell old_dependency:((CellFormula) old_cell).getDependencies()){
                    if(!(new_cell instanceof CellFormula && ((CellFormula) new_cell).getDependencies().contains(old_dependency))){
                        old_dependency.removeDependant((CellFormula) old_cell);
                    }
                }
            }
            this.spreadsheet.getCells().get(location.get(0)).set(location.get(1), new_cell);
            Cell updated_cell=this.spreadsheet.getCell(location);
            updated_cell.updateDependants();
            return true;
        }else{
            return false;
        }
    }
}
