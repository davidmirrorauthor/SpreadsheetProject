package upc.softarch.spreadsheetProject.Cells;

import upc.softarch.spreadsheetProject.SpreadsheetManager;

import java.util.List;

public class CellNumber extends Cell{
    public CellNumber(String content, List<Integer> location) {
        super(content, location);
        this.value = Float.parseFloat(content);
    }
}
