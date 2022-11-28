package upc.softarch.spreadsheetProject;

public abstract class Cell {
    protected String content = "";
    protected float value = 0.0f;
    abstract String getCellContent();
    abstract  Float getCellValue();
    abstract  boolean setCell(String content);

}
