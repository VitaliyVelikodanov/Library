package article.dao.dbtable;

public interface Filter<T> {

    <K> boolean accept(T item, K pattern);
}
