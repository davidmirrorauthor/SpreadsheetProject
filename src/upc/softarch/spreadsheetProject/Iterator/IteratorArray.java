package upc.softarch.spreadsheetProject.Iterator;

public class IteratorArray <T> implements Iterator <T>{
    protected int i;
    protected T[] array;

    @Override
    public boolean hasNext() {
        return i<array.length;
    }

    @Override
    public void next() {
        i=i+1;
    }

    @Override
    public T getCurrent() {
        return array[i];
    }
}
