package article.dao.dbtable;

public abstract class DBTableFactory {
    private static DBTable<?> instance;

    protected DBTableFactory() {
        // let descendants use it
    }

    @SuppressWarnings("unchecked")
    public static <T> DBTable<T> instance() {
        if (instance == null) {
            instance = new DBTableInMemory<>();
        }
        return (DBTable<T>) instance;
    }
}
