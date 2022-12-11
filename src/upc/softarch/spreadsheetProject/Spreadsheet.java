package upc.softarch.spreadsheetProject;

import java.util.ArrayList;
import java.util.List;

public class Spreadsheet {
    public List<List<Cell>> cells = new ArrayList<>();
    public String file_name;

    public Spreadsheet(String file_name){
        this.file_name=file_name;
    }
    public List<Integer> getCellLocationWithAlphanumericCode(String alphanumeric_position){
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
        String numerical_location_str = alphanumeric_position.replaceAll("[^0-9]", "");
        int index_row=Integer.parseInt(numerical_location_str)-1;
        String alphabet_location =alphanumeric_position.replace(numerical_location_str,"");
        int index_column=-1;
        int n_letter=1;
        int length_letters=alphabet_location.toCharArray().length;
        for (char letter: alphabet_location.toCharArray()){
            for (int i=1;  i<alphabet.length+1;i++) {
                char alphabet_letter = alphabet[i-1];
                if (letter == alphabet_letter) {
                    if (n_letter==length_letters){
                        index_column += i;
                    }else{
                        index_column += Math.pow(26, i);
                    }
                    break;
                }
            }
            n_letter+=1;
        }
        List<Integer> location = new ArrayList<Integer>();
        location.add(index_row);
        location.add(index_column);
        return location;
    }
    public Cell searchCell(String alphanumeric_position){
        Cell cell=null;
        List<Integer> location = getCellLocationWithAlphanumericCode(alphanumeric_position);
        if (location.get(0)<this.cells.size() && location.get(1)<this.cells.get(0).size()){
            cell=this.cells.get(location.get(0)).get(location.get(1));
        }
        return cell;
    }

    public boolean updateDependencies() {
        return false;
    }

    public boolean isCircularDependency(String content){
        return false;
    }
}
