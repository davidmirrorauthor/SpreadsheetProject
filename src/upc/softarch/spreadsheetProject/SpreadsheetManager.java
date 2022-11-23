package upc.softarch.spreadsheetProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpreadsheetManager {
    private int num_default_rows = 10;
    private int num_default_columns = 10;
    private String current_directory = System.getProperty("user.dir");
    public List<String> files_available = new ArrayList();
    public Spreadsheet spreadsheet;
    public SpreadsheetManager(){
        this.files_available = getExistingSpreadsheets();
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
        for (String file_name : this.files_available){
            if (new_file_name.equals(file_name) || (new_file_name + ".txt").equals(file_name)){
                return false;
            }
        }
        return true;
    }

    public boolean createSpreadsheet(String file_name){
        if (! file_name.contains(".txt")){
            file_name=file_name+ ".txt";
        }
        boolean file_created = false;
        boolean is_name_available=checkIfSpreadsheetNameExists(file_name);
        if (is_name_available) {
            try {
                File file = new File(file_name);
                file_created = file.createNewFile();
                this.files_available.add(file_name);
                PrintWriter writer = new PrintWriter(file_name, "UTF-8");
                for(int row=0; row<this.num_default_rows;row++){
                    StringBuilder line= new StringBuilder();
                    for (int column = 0;column<this.num_default_columns;column++){
                        line.append(";");
                    }
                    writer.println(line);
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return file_created;
    }
    public boolean saveSpreadsheet(){
        return true;
    }

    public Spreadsheet loadSpreadsheet(String spreadsheet_name) {
        Spreadsheet spreadsheet = new Spreadsheet(spreadsheet_name);
        try{
            FileReader fr = new FileReader(spreadsheet_name);
            int i;
            StringBuilder string_builder = new StringBuilder();

            while ((i = fr.read()) != -1){

                string_builder.append((char)i);
            }
            String file_str=string_builder.toString();
            //System.out.println(file_str);
            List<Cell> row_vector = new ArrayList<>();
            String variable="";
            for (char character : file_str.toCharArray()){
                if (character==';'){
                    row_vector.add(variable);
                    variable="";
                } else if (character=='\r') {
                    spreadsheet.cells.add(row_vector);
                    row_vector = new ArrayList<>();
                }else if(character=='\n'){

                }else{
                    variable+=character;
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return spreadsheet;
    }
    public boolean deleteSpreadsheet(String file_name){
        File f= new File(file_name);
        if(f.delete()){
            return true;
        }else{
            return false;
        }
    }
    public boolean showSpreadsheet(){

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
    }
}
