package book.service;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import book.service.rest.JSONMessageBodyWriter;
import book.service.rest.ServiceExceptionMapper;
import book.service.rest.TextPlainMessageBodyWriter;
import book.service.rest.XMLItemMessageBodyWriter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath(Constants.APPLICATION_PATH)
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(BookServiceRestImpl.class);
        classes.add(XMLItemMessageBodyWriter.class);
        classes.add(TextPlainMessageBodyWriter.class);
        classes.add(JSONMessageBodyWriter.class);
        classes.add(ServiceExceptionMapper.class);
        return Collections.unmodifiableSet(classes);
    }
}
