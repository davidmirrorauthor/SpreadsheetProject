package upc.softarch.spreadsheetProject;
import upc.softarch.spreadsheetProject.Cells.Cell;
import upc.softarch.spreadsheetProject.Cells.CellFormula;
import upc.softarch.spreadsheetProject.Cells.CellNumber;
import upc.softarch.spreadsheetProject.Cells.CellText;
import java.util.ArrayList;
import java.util.List;
import static upc.softarch.spreadsheetProject.Utils.UtilsAlphabet.getAlphabet;
import static upc.softarch.spreadsheetProject.Utils.UtilsMath.isNumeric;

public class Spreadsheet {
    private List<List<Cell>> cells = new ArrayList<>();
    private String file_name;
    public Spreadsheet(String file_name){
        this.file_name=file_name;
    }
    public List<Integer> alphanumericCode2CellLocation(String alphanumeric_position){
        char[] alphabet=getAlphabet();
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
    public String cellLocation2AlphanumericCode(List<Integer> location){
        Integer row_number = location.get(1)+1;
        String row_str = row_number.toString();
        Integer column_number= location.get(0)+1;
        int num = column_number - 1;
        final StringBuilder sb = new StringBuilder();
        while (num >=  0) {
            int numChar = (num % 26)  + 65;
            sb.append((char)numChar);
            num = (num  / 26) - 1;
        }
        String column_str=sb.reverse().toString();
        String alphanumeric_location=column_str + row_str;
        return alphanumeric_location;
    }
    public Cell searchCell(String alphanumeric_position){
        Cell cell=null;
        List<Integer> location = alphanumericCode2CellLocation(alphanumeric_position);
        if (location.get(0)<this.cells.size() && location.get(1)<this.cells.get(0).size()){
            cell=this.cells.get(location.get(0)).get(location.get(1));
        }
        return cell;
    }
    public Float getValue(String alphanumeric_code){
        List<Integer> location= alphanumericCode2CellLocation(alphanumeric_code);
        return getValue(location.get(0),location.get(1));
    }

    public Float getValue(Integer row, Integer column){
        Cell cell=this.cells.get(row).get(column);
        return cell.getValue();
    }
    public List<List<Cell>> getCells(){return this.cells;}
    public Cell getCell(List<Integer> location){return this.cells.get(location.get(0)).get(location.get(1));}
    public String getFileName(){return this.file_name;}
    public static Cell createCellWithString(String content, List<Integer> location, boolean first_time){
        Cell cell;
        if (content.isEmpty()){
            cell = new CellText("",location);
        } else{
            if (content.charAt(0)=='='){
                cell = new CellFormula(content, location, first_time);
                for(Cell dependency:((CellFormula)cell).getDependencies()){
                    if(!dependency.getDependants().contains(cell)){
                        dependency.addDependant(((CellFormula) cell));
                    }
                }
            }else if(isNumeric(content)){
                cell = new CellNumber(content, location);
            }else if (content.equals("")){
                cell = new Cell(content, location);
            }else{
                cell = new CellText(content, location);
            }
        }
        return cell;
    }
}