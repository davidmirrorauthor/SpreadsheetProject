package upc.softarch.spreadsheetProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpreadsheetManager {
    private int num_default_rows = 10;
    private int num_default_columns = 10;
    public Spreadsheet spreadsheet;

    public SpreadsheetManager(){
    }
    public List<String> getExistingSpreadsheets(){
        String current_directory = System.getProperty("user.dir");
        File f = new File(current_directory);
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
        for (List<Cell> row: this.spreadsheet.cells){
            for (Cell cell: row){
                content.append(cell.content);
                content.append(";");
            }
            content.append("\n\r");
        }
        FileWriter file = new FileWriter(this.spreadsheet.file_name, false);
        file.write(content.toString());
        file.close();
        return true;
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public Cell getCellWithString(String content){
        Cell cell;
        if (content.isEmpty()){
            cell = new TextCell(content);
        } else{
            if (content.charAt(0)=='='){
                cell = new FormulaCell(content);
            }else if(isNumeric(content)){
                cell = new NumberCell(content);
            }else{
                cell = new TextCell(content);
            }
        }
        return cell;
    }
    public Spreadsheet loadSpreadsheet(String spreadsheet_name) throws IOException {
        Spreadsheet spreadsheet = new Spreadsheet(spreadsheet_name);
        FileReader fr = new FileReader(spreadsheet_name);
        int i;
        StringBuilder string_builder = new StringBuilder();
        while ((i = fr.read()) != -1){
            string_builder.append((char)i);
        }
        String file_str=string_builder.toString();
        List<Cell> row_vector = new ArrayList<>();
        StringBuilder variable= new StringBuilder();
        for (char character : file_str.toCharArray()){
            if (character==';'){
                Cell cell = getCellWithString(variable.toString());
                row_vector.add(cell);
                variable = new StringBuilder();
            } else if (character=='\r') {
                spreadsheet.cells.add(row_vector);
                row_vector = new ArrayList<>();
            }else if(character=='\n'){

            }else{
                variable.append(character);
            }
        }
        return spreadsheet;
    }
    public boolean deleteSpreadsheet(String file_name){
        if (this.spreadsheet!=null && file_name.equals(this.spreadsheet.file_name)){
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
            for (List<Cell> row_cell:this.spreadsheet.cells){
                List<String> contents = new ArrayList<>();
                for (Cell cell:row_cell){
                    if(cell.value== 0.0f){
                        contents.add(cell.content);
                    }else{
                        contents.add(String.valueOf(cell.value));
                    }

                }
                System.out.println(contents);
            }
            return true;
        }else{
            return false;
        }
    }
    public boolean editCell(String alphanumeric_location,String new_content){
        List<Integer> location=spreadsheet.getCellLocationWithAlphanumericCode(alphanumeric_location);
        Cell new_cell=getCellWithString(new_content);
        if (location.get(0)<spreadsheet.cells.size() && location.get(1)<spreadsheet.cells.get(0).size()) {
            spreadsheet.cells.get(location.get(0)).set(location.get(1), new_cell);
            return true;
        }else{
            return false;
        }
    }
}
