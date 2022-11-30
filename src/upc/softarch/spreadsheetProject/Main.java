package upc.softarch.spreadsheetProject;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    SpreadsheetManager spreadsheet_manager = SpreadsheetManager.getInstance();
    Scanner scanner = new Scanner(System.in);
    public void menu(){
        System.out.println("------------------");
        System.out.println();
        if(spreadsheet_manager.spreadsheet==null){
            System.out.println("No spreadsheet loaded!");
        }else {
            System.out.println("Spreadsheet Loaded: "+spreadsheet_manager.spreadsheet.file_name);
        }
        System.out.println("Choose an option:");
        System.out.println();
        System.out.println("0. Exit");
        System.out.println("1. New Spreadsheet");
        System.out.println("2. Load Spreadsheet");
        System.out.println("3. Save Spreadsheet");
        System.out.println("4. Show Spreadsheet");
        System.out.println("5. Delete Spreadsheet");
        System.out.println("6. Edit Cell");
        System.out.println("------------------");
    }
    public void createSpreadsheet() throws IOException {
        System.out.println("New Name File? ");
        String new_file_name = scanner.nextLine();
        boolean is_created =this.spreadsheet_manager.createSpreadsheet(new_file_name);
        if (is_created){
            System.out.println(new_file_name+".txt has been created successfully");
        }else{
            System.out.println("The file name -"+new_file_name+".txt- already exists.");
        }
    };
    public void loadSpreadsheet() throws IOException {
        System.out.println("Select file: ");
        List<String> files_available = spreadsheet_manager.getExistingSpreadsheets();
        int i=1;
        for (String file_name:files_available){
            System.out.println(Integer.toString(i)+". "+file_name);
            i++;
        }
        int file_name_index = scanner.nextInt();
        String file_name= files_available.get(file_name_index - 1);
        spreadsheet_manager.spreadsheet=spreadsheet_manager.loadSpreadsheet(file_name);
        System.out.println(file_name+" has been successfully loaded!");
        System.out.println();
    };
    public void saveSpreadsheet() throws IOException {
        boolean is_saved=spreadsheet_manager.saveSpreadsheet();
        if (is_saved){
            System.out.println("File "+spreadsheet_manager.spreadsheet.file_name+" has been saved successfully;");
        }else{
            System.out.println("An error occured while saving "+ spreadsheet_manager.spreadsheet.file_name+" file.");
        }
    };
    public void showSpreadsheet(){
        boolean is_shown=spreadsheet_manager.showSpreadsheet();
        if (!is_shown){
            System.out.println("No Spreadsheet was selected first!");
        }
    };
    public void deleteSpreadsheet(){
        System.out.println("Select file: ");
        List<String> files_available = spreadsheet_manager.getExistingSpreadsheets();
        int i=1;
        for (String file_name:files_available){
            System.out.println(Integer.toString(i)+". "+file_name);
            i++;
        }
        int file_name_index = scanner.nextInt();
        String file_name= files_available.get(file_name_index - 1);
        boolean is_deleted=spreadsheet_manager.deleteSpreadsheet(file_name);
        if (is_deleted){
            System.out.println(file_name+" has been successfully deleted!");
        }else {
            System.out.println("An error ocurred when deleting "+file_name+"!");
        }
        System.out.println();
    };
    public void editCell(){
        if(spreadsheet_manager.spreadsheet!=null){
            spreadsheet_manager.showSpreadsheet();
            System.out.println("Cell?");
            String alphanumeric_location = scanner.nextLine();
            Cell cell = spreadsheet_manager.spreadsheet.searchCell(alphanumeric_location);
            if (cell!=null){
                System.out.println("Content: " + cell.content);
                if (!(cell instanceof  TextCell)){
                    System.out.println("Value: "+ cell.value);
                }
                System.out.println();
                System.out.println("New content?");
                String new_content=scanner.nextLine();
                boolean is_cell_edited=spreadsheet_manager.editCell(alphanumeric_location, new_content);
                if (is_cell_edited){
                    System.out.println("Cell "+alphanumeric_location+" has been successfully edited.");
                }else{
                    System.out.println("An error occured while editing cell "+alphanumeric_location+".");
                }
            }else{
                System.out.println("Cell "+alphanumeric_location+" out of range!");
            }
        }else{
            System.out.println("No spreadsheet loaded! Please, load first one spreadsheet.");
        }
    }
    public void run() throws IOException {
        boolean end = false;
        while (!end){
            menu();
            System.out.print(">");
            int option = scanner.nextInt();
            String dummy = scanner.nextLine();
            switch(option){
                case 0:
                    end=true;
                    break;
                case 1:
                    createSpreadsheet();
                    break;
                case 2:
                    loadSpreadsheet();
                    break;
                case 3:
                    saveSpreadsheet();
                    break;
                case 4:
                    showSpreadsheet();
                    break;
                case 5:
                    deleteSpreadsheet();
                case 6:
                    editCell();
            }
        }

    }
    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.run();
    }
}