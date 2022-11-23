package upc.softarch.spreadsheetProject;

import java.util.Scanner;

public class Main {
    SpreadsheetManager spreadsheet_manager =new SpreadsheetManager();
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
    public void createSpreadsheet(){
        System.out.println("New Name File? ");
        String new_file_name = scanner.nextLine();
        boolean is_created =this.spreadsheet_manager.createSpreadsheet(new_file_name);
        if (is_created){
            System.out.println(new_file_name+".txt has been created successfully");
        }else{
            System.out.println("The file name -"+new_file_name+".txt- already exists.");
        }
    };
    public void loadSpreadsheet(){
        System.out.println("Select file: ");
        int i=1;
        for (String file_name:spreadsheet_manager.files_available){
            System.out.println(Integer.toString(i)+". "+file_name);
            i++;
        }
        int file_name_index = scanner.nextInt();
        String file_name= spreadsheet_manager.files_available.get(file_name_index - 1);
        spreadsheet_manager.spreadsheet=spreadsheet_manager.loadSpreadsheet(file_name);
        System.out.println(file_name+" has been successfully loaded!");
        System.out.println();
    };
    public void saveSpreadsheet(){};
    public void showSpreadsheet(){
        spreadsheet_manager.showSpreadsheet();
    };
    public void deleteSpreadsheet(){
        System.out.println("Select file: ");
        int i=1;
        for (String file_name:spreadsheet_manager.files_available){
            System.out.println(Integer.toString(i)+". "+file_name);
            i++;
        }
        int file_name_index = scanner.nextInt();
        String file_name= spreadsheet_manager.files_available.get(file_name_index - 1);
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
            String cell_location = scanner.nextLine();
            spreadsheet_manager.spreadsheet.editCell(cell_location);
        }else{
            System.out.println("No spreadsheet loaded! Please, load first one spreadsheet.");
        }
    }
    public void run(){
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
    public static void main(String[] args) {
        Main m = new Main();
        m.run();
    }
}