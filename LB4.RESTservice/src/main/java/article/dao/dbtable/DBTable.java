package article.dao.dbtable;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;

public interface DBTable<T> {
    public BigInteger insert(T item);

    public T delete(BigInteger id) throws SQLException;

    public void delete(T item, Filter<T> filter);

    public boolean update(BigInteger id, T item) throws SQLException;


    public <K> Collection<T> filter(K pattern, Filter<T> filter);


    public Collection<T> selectAll();

    public T get(BigInteger id) throws SQLException;

    public void clear();

    public BigInteger size();

    @SuppressWarnings("unchecked")
    public BigInteger[] insert(T... item);


}
