package app.additions;

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

    @Override
    public String toString() {
        return "ReturnsAtomic{" +
            "item=" + item +
            '}';
    }
}
