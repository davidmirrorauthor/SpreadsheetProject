package upc.softarch.spreadsheetProject;

public abstract class Cell {
    protected String content = "";
    protected float value = 0.0f;
    abstract String getCell();
    abstract  boolean setCell(String content);

}
