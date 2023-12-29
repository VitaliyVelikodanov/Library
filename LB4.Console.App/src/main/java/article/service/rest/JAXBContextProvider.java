package article.service.rest;

import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

@Provider
public class JAXBContextProvider implements ContextResolver<JAXBContext> {
    private JAXBContext context = null;

    public JAXBContext getContext(Class<?> type) {
        if (context == null) {
            try {
                context = JAXBContext.newInstance(app.entity.ObjectFactory.class);
            } catch (JAXBException e) {
                System.err.println(getClass().getName() + ": " + e.getMessage());
            }
        }
        return context;
    }
}
