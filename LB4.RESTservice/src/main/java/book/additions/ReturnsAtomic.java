package book.additions;

public class ReturnsAtomic<T> {
    private T item;

    public ReturnsAtomic() {
    }

    public ReturnsAtomic(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
