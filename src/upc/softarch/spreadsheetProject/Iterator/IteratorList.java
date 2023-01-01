package upc.softarch.spreadsheetProject.Iterator;

public class IteratorList<T> implements Iterator<T>{
    protected Item<T> item;
    @Override
    public boolean hasNext() {
        return item!=null;
    }

    @Override
    public void next() {
        item=item.next;
    }

    @Override
    public T getCurrent() {
        return item.data;
    }
}
