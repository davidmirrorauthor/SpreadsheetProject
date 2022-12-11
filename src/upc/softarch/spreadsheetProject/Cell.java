package upc.softarch.spreadsheetProject;

public abstract class Cell{
    protected String content = "";
    protected Float value;
    abstract String getContent();
    abstract  Float getValue();
    abstract  boolean setCell(String content);

}
