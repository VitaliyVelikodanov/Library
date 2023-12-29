package book.service.rest;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import book.entity.DataWrapper;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.AbstractList;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class XMLItemMessageBodyWriter<T> implements MessageBodyWriter<T> {

    public XMLItemMessageBodyWriter() throws JAXBException {
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        System.out.println("Checking is writtable");
        Class<?> t = type;
        while (t.getSuperclass() != null) {
            System.out.println("Checking type " + t + " against " + AbstractList.class);
            if (AbstractList.class.equals(t)) {
                return false;
            }
            t = t.getSuperclass();
        }
        return true;
    }

    @Override
    public void writeTo(T target, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders, OutputStream outputStream) throws IOException {

        System.out.println("Using item writer");
        try {
            DataWrapper<T> wrapper = new DataWrapper<>(target);

            JAXBContext context = JAXBContext.newInstance(DataWrapper.class, type);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(wrapper, outputStream);
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
}
