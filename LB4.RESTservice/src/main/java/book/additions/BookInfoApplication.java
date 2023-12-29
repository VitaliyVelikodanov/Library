package book.additions;

import book.service.BookServiceRestImpl;
import jakarta.ws.rs.core.Application;


import java.util.HashSet;
import java.util.Set;

public class BookInfoApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        classes.add(BookServiceRestImpl.class);

        return classes;
    }
}
